package com.global.logic.android.challenge.presenters;

import com.global.logic.android.challenge.interfaces.TrackRowView;
import com.global.logic.android.challenge.models.Track;

import java.util.List;

public class TrackRowPresenter {

    // Attributes
    private List<Track> tracks;

    // Constructors
    public TrackRowPresenter(List<Track> tracks) {
        this.tracks = tracks;
    }

    // Public methods
    public void onBindTrackRowViewAtPosition(int position, TrackRowView trackRowView) {
        Track track = getCurrentTrack(position);
        trackRowView.setArtistName(track.getArtistName());
        trackRowView.setCollectionName(track.getCollectionName());
        trackRowView.setTrackName(track.getTrackName());
    }

    public int getTrackCount() {
        return tracks.size();
    }

    // Public methods
    public Track getCurrentTrack(Integer position) {
        return tracks.get(position);
    }

}