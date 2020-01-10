package com.example.myapplication.mvp;


import com.example.myapplication.api.ApiService;
import com.example.myapplication.entity.CarEntity;
import com.example.myapplication.utils.RetrofitUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model implements Contract.Model{
    private static final String TAG = "Model";
    @Override
    public void Show(ModelCallBack callBack) {
        RetrofitUtil.getInstance().createservice(ApiService.class)
                .getCartResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CarEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CarEntity carEntity) {
                        callBack.success(carEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    @Override
//    public void Xiangq(ModelCallBack callBack) {
//        RetrofitUtil.getInstance().createservice(ApiService.class)
//                .getXiangq()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<XiangQingEntity>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(XiangQingEntity xiangQingEntity) {
//                        Log.d(TAG, "onNext: "+xiangQingEntity);
//                        callBack.success(xiangQingEntity);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.failure(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
