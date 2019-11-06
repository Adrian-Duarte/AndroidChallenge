package com.global.logic.android.challenge.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.interfaces.TrackRowView;

public class TrackRowViewHolder extends RecyclerView.ViewHolder implements TrackRowView {

    // Constructors
    public TrackRowViewHolder(View view) {
        super(view);
    }

    @Override
    public void setArtistName(String artistName) {
        ((TextView) itemView.findViewById(R.id.tvArtistName)).setText(artistName);
    }

    @Override
    public void setCollectionName(String collectionName) {
        ((TextView) itemView.findViewById(R.id.tvCollectionName)).setText(collectionName);
    }

    @Override
    public void setTrackName(String trackName) {
        ((TextView) itemView.findViewById(R.id.tvTrackName)).setText(trackName);
    }



}