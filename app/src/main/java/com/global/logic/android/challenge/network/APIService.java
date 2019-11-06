package com.global.logic.android.challenge.network;

import com.global.logic.android.challenge.models.TrackResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    Observable<TrackResponse> searchTracks(@Query("term") String query, @Query("mediaType") String mediaType, @Query("limit") int limit);

}