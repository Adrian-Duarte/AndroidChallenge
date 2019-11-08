package com.global.logic.android.challenge.presenters;

import com.global.logic.android.challenge.interfaces.DetailCollectionContract;
import com.global.logic.android.challenge.models.Track;
import com.global.logic.android.challenge.models.TrackResponse;
import com.global.logic.android.challenge.network.APIService;
import com.global.logic.android.challenge.network.RetrofitClient;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailCollectionPresenter implements DetailCollectionContract.Presenter {

    // Constants
    private static final String LOOKUP_ENTITY = "song";

    // Attributes
    private APIService apiService;
    private DetailCollectionContract.View view;

    // Constructors
    public DetailCollectionPresenter(DetailCollectionContract.View view) {
        apiService = RetrofitClient.getInstance().create(APIService.class);
        this.view = view;
    }

    @Override
    public void lookup(int id) {
        apiService
            .lookup(id, LOOKUP_ENTITY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<TrackResponse>() {
                @Override
                public void onSubscribe(Disposable d) {
                }
                @Override
                public void onNext(TrackResponse value) {
                    List<Track> tracks = value.getTracks();
                    tracks.remove(0);
                    view.showTracks(tracks);
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