package com.global.logic.android.challenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.holders.TrackDetailCollectionRowViewHolder;
import com.global.logic.android.challenge.presenters.TrackDetailCollectionRowPresenter;

public class TrackDetailCollectionRecyclerAdapter extends RecyclerView.Adapter<TrackDetailCollectionRowViewHolder> {

    // Attributes
    private TrackDetailCollectionRowPresenter trackRowPresenter;

    // Constructors
    public TrackDetailCollectionRecyclerAdapter(TrackDetailCollectionRowPresenter trackRowPresenter) {
        this.trackRowPresenter = trackRowPresenter;
    }

    @NonNull
    @Override
    public TrackDetailCollectionRowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrackDetailCollectionRowViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_detail_collection, viewGroup, false),
                trackRowPresenter);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackDetailCollectionRowViewHolder viewHolder, int position) {
        trackRowPresenter.onBindTrackRowViewAtPosition(position, viewHolder);
    }

    @Override
    public int getItemCount() {
        return trackRowPresenter.getTrackCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Public methods
    public void onDestroy() {
        trackRowPresenter.onDestroy();
    }

}
