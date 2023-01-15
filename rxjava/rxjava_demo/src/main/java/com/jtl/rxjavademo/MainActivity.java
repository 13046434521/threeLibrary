package com.jtl.rxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AtomicInteger atomicInteger = new AtomicInteger(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("???");
                Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                                emitter.onNext("qunimade");
                                Log.w("qunimade","io/newThread:"+Thread.currentThread().getName()+atomicInteger.getAndIncrement());
                            }
                        }).observeOn(Schedulers.newThread())
                        .map(new Function<String, Object>() {
                            @Override
                            public Object apply(String s) throws Throwable {
                                Log.w("qunimade","newThread:"+Thread.currentThread().getName()+atomicInteger.getAndIncrement());
                                return new Object();
                            }
                        })
                        .observeOn(Schedulers.computation())
                        .observeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<Object, Integer>() {
                            @Override
                            public Integer apply(Object bitmap) throws Throwable {
                                Log.w("qunimade","mainThread:"+Thread.currentThread().getName()+atomicInteger.getAndIncrement());
                                return 123;
                            }
                        }).observeOn(Schedulers.single())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object integer) throws Throwable {
                                Log.w("qunimade","single:"+Thread.currentThread().getName()+atomicInteger.getAndIncrement());
                            }
                        });
            }
        }).start();
    }
}