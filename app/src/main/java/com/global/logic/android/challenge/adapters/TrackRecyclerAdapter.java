package com.global.logic.android.challenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.holders.TrackRowViewHolder;
import com.global.logic.android.challenge.presenters.TrackRowPresenter;

public class TrackRecyclerAdapter extends RecyclerView.Adapter<TrackRowViewHolder> {

    // Attributes
    private TrackRowPresenter trackRowPresenter;

    // Constructors
    public TrackRecyclerAdapter(TrackRowPresenter trackRowPresenter) {
        this.trackRowPresenter = trackRowPresenter;
    }

    @NonNull
    @Override
    public TrackRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrackRowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_track, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrackRowViewHolder trackRowViewHolder, int position) {
        trackRowPresenter.onBindTrackRowViewAtPosition(position, trackRowViewHolder);
    }

    @Override
    public int getItemCount() {
        return trackRowPresenter.getTrackCount();
    }

}
