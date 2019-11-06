package com.global.logic.android.challenge.models;

import com.google.gson.annotations.SerializedName;

public class Track {

    // Attributes
    @SerializedName("trackId")
    private Integer trackId;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("artworkUrl100")
    private String image100x100;

    @SerializedName("collectionName")
    private String collectionName;

    @SerializedName("trackName")
    private String trackName;

    // Getters
    public Integer getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getImage100x100() {
        return image100x100;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

}
