package com.global.logic.android.challenge.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.global.logic.android.challenge.R;
import com.global.logic.android.challenge.adapters.TrackRecyclerAdapter;
import com.global.logic.android.challenge.interfaces.ListTrackContract;
import com.global.logic.android.challenge.models.Track;
import com.global.logic.android.challenge.presenters.ListTrackPresenter;
import com.global.logic.android.challenge.presenters.TrackRowPresenter;

import java.util.List;
import java.util.Random;

public class ListTrackActivity extends AppCompatActivity implements ListTrackContract.View {

    // Constants
    private static final String[] RANDOM_TRACKS = {"nirvana", "bruno mars", "usher", "Say Something"};

    // Attributes
    private RecyclerView rvTracks;
    private ListTrackContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_track);

        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setInputType(
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS |
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        );
        if (searchManager != null) searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                presenter.search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    // Private methods
    private void initialize() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        rvTracks = findViewById(R.id.rv_tracks);

        presenter = new ListTrackPresenter(this);
        int randomNumber = new Random().nextInt(RANDOM_TRACKS.length);
        presenter.search(RANDOM_TRACKS[randomNumber]);
    }

    @Override
    public void showTracks(List<Track> tracks) {
        TrackRowPresenter presenter = new TrackRowPresenter(tracks);
        TrackRecyclerAdapter adapter = new TrackRecyclerAdapter(presenter);
        rvTracks.setLayoutManager(new LinearLayoutManager(this));
        rvTracks.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.general_error), Toast.LENGTH_LONG).show();
    }

}