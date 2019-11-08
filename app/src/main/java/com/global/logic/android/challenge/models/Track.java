package com.global.logic.android.challenge.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Track implements Parcelable {

    // Attributes
    @SerializedName("collectionId")
    private Integer collectionId;

    @SerializedName("artistName")
    private String artistName;

    @SerializedName("artworkUrl100")
    private String artworkUrl100;

    @SerializedName("collectionName")
    private String collectionName;

    @SerializedName("previewUrl")
    private String previewUrl;

    @SerializedName("trackName")
    private String trackName;

    public boolean isPlaying;

    protected Track(Parcel in) {
        if (in.readByte() == 0) {
            collectionId = null;
        } else {
            collectionId = in.readInt();
        }
        artistName = in.readString();
        artworkUrl100 = in.readString();
        collectionName = in.readString();
        previewUrl = in.readString();
        trackName = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    // Getters
    public Integer getCollectionId() {
        return collectionId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getTrackName() {
        return trackName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (collectionId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(collectionId);
        }
        parcel.writeString(artistName);
        parcel.writeString(artworkUrl100);
        parcel.writeString(collectionName);
        parcel.writeString(previewUrl);
        parcel.writeString(trackName);
    }
}
