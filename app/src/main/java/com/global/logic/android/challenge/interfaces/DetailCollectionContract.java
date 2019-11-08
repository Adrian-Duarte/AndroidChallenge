package com.global.logic.android.challenge.interfaces;

import com.global.logic.android.challenge.models.Track;

import java.util.List;

public interface DetailCollectionContract {

    interface View {
        void showTracks(List<Track> tracks);
        void showError();
    }

    interface Presenter {
        void lookup(int id);
    }

}