package com.shohiebsense.constraintlayoutexample.network.getimages;


import com.shohiebsense.constraintlayoutexample.model.CommonImage;
import com.shohiebsense.constraintlayoutexample.model.CommonImageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GetImagesFromWebTask {

    public interface GetImagesListener {
        void onSuccess(List<CommonImage> commonImages);
        void onFailed();
    }


    public void getImages(final GetImagesListener listener){
        final List<CommonImageRequest> imageRequests = new ArrayList<CommonImageRequest>();
        imageRequests.add(new CommonImageRequest(400,300));
        imageRequests.add(new CommonImageRequest(400,300));
        imageRequests.add(new CommonImageRequest(400,300));

        Observable<List<CommonImage>> booksObservable =
                Observable.fromCallable(new Callable<List<CommonImage>>() {
                    @Override
                    public List<CommonImage> call() throws Exception {
                        return (new OkHttpImageService().getImages(imageRequests));
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List< CommonImage>> commonImageObserver = new Observer<List<CommonImage>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CommonImage> commonImages) {
                listener.onSuccess(commonImages);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailed();
            }

            @Override
            public void onComplete() {

            }
        };
        booksObservable.subscribe(commonImageObserver);
    }


}
