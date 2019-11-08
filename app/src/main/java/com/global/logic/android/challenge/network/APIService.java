package com.global.logic.android.challenge.network;

import com.global.logic.android.challenge.models.TrackResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    Observable<TrackResponse> search(@Query("term") String query, @Query("entity") String entity, @Query("limit") int limit);

    @GET("lookup")
    Observable<TrackResponse> lookup(@Query("id") int id, @Query("entity") String entity);

}