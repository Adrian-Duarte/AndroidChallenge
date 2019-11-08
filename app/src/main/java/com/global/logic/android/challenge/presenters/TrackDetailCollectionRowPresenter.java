package com.global.logic.android.challenge.presenters;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.global.logic.android.challenge.holders.TrackDetailCollectionRowViewHolder;
import com.global.logic.android.challenge.interfaces.TrackDetailCollectionRowView;
import com.global.logic.android.challenge.models.Track;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TrackDetailCollectionRowPresenter {

    // Attributes
    private AppCompatActivity context;
    private List<Track> tracks;
    private MediaPlayer mediaPlayer;
    private Timer timer;

    // Constructors
    public TrackDetailCollectionRowPresenter(AppCompatActivity context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
        mediaPlayer = new MediaPlayer();
    }

    // Public methods
    public void onBindTrackRowViewAtPosition(int position, TrackDetailCollectionRowView rowView) {
        Track track = getCurrentTrack(position);
        rowView.setTrackName(track.getTrackName());
        rowView.setPlayListener();
        rowView.setStopListener();
    }

    public int getTrackCount() {
        return tracks.size();
    }

    public Track getCurrentTrack(Integer position) {
        return tracks.get(position);
    }

    public void play(int position, final TrackDetailCollectionRowViewHolder viewHolder) {
        final Track track = getCurrentTrack(position);
        if (!track.isPlaying && !mediaPlayer.isPlaying()) {
            try {
                mediaPlayer.setDataSource(track.getPreviewUrl());
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        track.isPlaying = true;
                    }
                });
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        track.isPlaying = false;
                        reset();
                    }
                });


                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        float progress = ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100;
                        viewHolder.setProgress((int) progress);
                    }
                }, 1, 500);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(int position, TrackDetailCollectionRowViewHolder viewHolder) {
        Track track = getCurrentTrack(position);
        if (track.isPlaying && mediaPlayer.isPlaying()) {
            reset();
            track.isPlaying = false;
            viewHolder.setProgress(0);
        }
    }

    public void reset() {
        if (timer!=null) timer.cancel();
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = new MediaPlayer();
        }
    }

    public void onDestroy() {
        reset();
    }

}