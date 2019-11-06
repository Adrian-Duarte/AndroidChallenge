package com.global.logic.android.challenge.presenters;

import com.global.logic.android.challenge.interfaces.ListTrackContract;
import com.global.logic.android.challenge.models.TrackResponse;
import com.global.logic.android.challenge.network.APIService;
import com.global.logic.android.challenge.network.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListTrackPresenter implements ListTrackContract.Presenter {

    // Constants
    private static final String TRACK_ENTITY = "song";
    private static final int TRACK_LIMIT = 20;

    // Attributes
    private APIService apiService;
    private ListTrackContract.View view;

    // Constructors
    public ListTrackPresenter(ListTrackContract.View view) {
        apiService = RetrofitClient.getInstance().create(APIService.class);
        this.view = view;
    }

    @Override
    public void search(String query) {
        apiService
            .searchTracks(query, TRACK_ENTITY, TRACK_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TrackResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }
                @Override
                public void onNext(TrackResponse value) {
                    view.showTracks(value.getTracks());
                }
                @Override
                public void onError(Throwable e) {
                    view.showError();
                }
                @Override
                public void onComplete() {
                }
            })
        ;
    }

}