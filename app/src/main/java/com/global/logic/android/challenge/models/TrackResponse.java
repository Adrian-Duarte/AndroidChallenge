package com.global.logic.android.challenge.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackResponse {

    // Attributes
    @SerializedName("results")
    private List<Track> tracks;

    // Getters && Setters
    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

}
