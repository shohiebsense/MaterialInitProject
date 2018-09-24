package com.shohiebsense.fintechtemplate.network.chucknorris;

import com.shohiebsense.fintechtemplate.model.ChuckNorris;
import com.shohiebsense.fintechtemplate.model.ChuckNorrisRequest;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GetChuckNorrisJokeTask {

    public interface GetChuckNorrisListener {
        void onSuccess(ChuckNorris chuckNorris);
    }

    public void getChuckNorrisJoke(final GetChuckNorrisListener listener){
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        Single<ChuckNorris> chuckNorrisSingle = Single.create(new SingleOnSubscribe<ChuckNorris>() {
            @Override
            public void subscribe(SingleEmitter<ChuckNorris> emitter) throws Exception {
                emitter.onSuccess(new OkHttpChuckNorrisService().getJoke(new ChuckNorrisRequest("religion")));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Consumer<ChuckNorris> chuckNorrisConsumer = new Consumer<ChuckNorris>() {
            @Override
            public void accept(ChuckNorris chuckNorris) throws Exception {
                listener.onSuccess(chuckNorris);
            }
        };
        chuckNorrisSingle.subscribe(chuckNorrisConsumer);
        Disposable disposable = chuckNorrisSingle.subscribe(chuckNorrisConsumer);
        compositeDisposable.add(disposable);

        compositeDisposable.dispose();
    }





}
