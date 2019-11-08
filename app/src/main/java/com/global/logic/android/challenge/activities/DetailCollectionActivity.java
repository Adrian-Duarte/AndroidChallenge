package com.global.logic.android.challenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.adapters.TrackDetailCollectionRecyclerAdapter;
import com.global.logic.android.challenge.interfaces.DetailCollectionContract;
import com.global.logic.android.challenge.models.Track;
import com.global.logic.android.challenge.presenters.DetailCollectionPresenter;
import com.global.logic.android.challenge.presenters.TrackDetailCollectionRowPresenter;

import java.util.List;

public class DetailCollectionActivity extends AppCompatActivity implements DetailCollectionContract.View {

    public static Intent getStartIntent(Context context, Track track) {
        Intent intent = new Intent(context, DetailCollectionActivity.class);
        intent.putExtra(EXTRA_TRACK, track);
        return intent;
    }

    // Constants
    public static final String EXTRA_TRACK = "extra_track";

    // Attributes
    private Track track;
    private RecyclerView rvTracks;
    private TrackDetailCollectionRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collection);

        getExtras();
        initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter!=null) adapter.onDestroy();
    }

    // Private methods
    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) track = bundle.getParcelable(EXTRA_TRACK);
    }

    private void initialize() {
        ((TextView) findViewById(R.id.tvArtistName)).setText(track.getArtistName());
        ((TextView) findViewById(R.id.tvCollectionName)).setText(track.getCollectionName());
        Glide.with(this).load(track.getArtworkUrl100()).into((ImageView) findViewById(R.id.ivImage));

        rvTracks = findViewById(R.id.rv_tracks);

        DetailCollectionContract.Presenter presenter = new DetailCollectionPresenter(this);
        presenter.lookup(track.getCollectionId());
    }

    @Override
    public void showTracks(List<Track> tracks) {
        TrackDetailCollectionRowPresenter presenter = new TrackDetailCollectionRowPresenter(this, tracks);
        adapter = new TrackDetailCollectionRecyclerAdapter(presenter);
        rvTracks.setLayoutManager(new LinearLayoutManager(this));
        rvTracks.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.general_error), Toast.LENGTH_LONG).show();
    }
}