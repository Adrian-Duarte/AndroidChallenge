package com.global.logic.android.challenge.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.interfaces.TrackDetailCollectionRowView;
import com.global.logic.android.challenge.presenters.TrackDetailCollectionRowPresenter;

public class TrackDetailCollectionRowViewHolder extends RecyclerView.ViewHolder implements TrackDetailCollectionRowView {

    // Attributes
    private TrackDetailCollectionRowPresenter presenter;

    // Constructors
    public TrackDetailCollectionRowViewHolder(View view, TrackDetailCollectionRowPresenter presenter) {
        super(view);
        this.presenter = presenter;
    }

    @Override
    public void setTrackName(String trackName) {
        ((TextView) itemView.findViewById(R.id.tvTrackName)).setText(trackName);
    }

    @Override
    public void setProgress(int progress) {
        ((ProgressBar) itemView.findViewById(R.id.pbProgress)).setProgress(progress);
    }

    @Override
    public void setPlayListener() {
        itemView.findViewById(R.id.ivPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.play(getAdapterPosition(), TrackDetailCollectionRowViewHolder.this);
            }
        });
    }

    @Override
    public void setStopListener() {
        itemView.findViewById(R.id.ivStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.stop(getAdapterPosition(), TrackDetailCollectionRowViewHolder.this);
            }
        });
    }

}