package com.global.logic.android.challenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.holders.TrackRowViewHolder;
import com.global.logic.android.challenge.models.Track;
import com.global.logic.android.challenge.presenters.TrackRowPresenter;

public class TrackRecyclerAdapter extends RecyclerView.Adapter<TrackRowViewHolder> {

    // Interfaces
    public interface OnItemClickListener {
        void onItemClick(Track track);
    }

    // Attributes
    private TrackRowPresenter trackRowPresenter;
    private OnItemClickListener onItemClickListener;

    // Constructors
    public TrackRecyclerAdapter(TrackRowPresenter trackRowPresenter, OnItemClickListener onItemClickListener) {
        this.trackRowPresenter = trackRowPresenter;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TrackRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrackRowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_track, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrackRowViewHolder trackRowViewHolder, final int position) {
        trackRowPresenter.onBindTrackRowViewAtPosition(position, trackRowViewHolder);
        trackRowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(trackRowPresenter.getCurrentTrack(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackRowPresenter.getTrackCount();
    }

}
