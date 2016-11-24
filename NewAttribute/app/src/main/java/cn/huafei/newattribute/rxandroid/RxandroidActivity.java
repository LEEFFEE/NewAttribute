package cn.huafei.newattribute.rxandroid;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Callable;

import cn.huafei.newattribute.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxandroidActivity extends AppCompatActivity {
    public static final String TAG = "RxandroidActivity";
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxandroid);
    }

    /**
     * 基本实现
     *
     * @param view
     */
    public void click(View view) {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");//只调用一次，与onError互斥
                    }

                    @Override
                    public void onError(Throwable e) {//只调用一次，onComplete
                        Log.e(TAG, "onError()", e);
                    }

                    @Override
                    public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");//可调用多次
                    }
                }));
    }

    /**
     * 变换1
     */
    public void changedClick1(View view) {
        Observable.just(2, 3).subscribe(getObserver());
    }

    @NonNull
    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe()");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext(" + value + ")");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError()", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete()");//只调用一次，与onError互斥
            }
        };
    }

    /**
     * 变换2
     */
    public void changedClick2(View view) {
        String[] str = {"dd", "ss"};
        //subscribe方法参数 onNext,onError,onComplete,onSubscribe
        Observable.fromArray(str).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: ", throwable);
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "Action run: ");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });

    }

    /**
     * 基本实现
     *
     * @param view
     */
    public void operator(View view) {
        //  操作符(Operators)<常用的）
        //        - map	转换对象
        //        - flatmap	平铺对象
        //         - filter	过滤
        //         -distinct	去重复（独特的）
        //        -take	从开始取出固定个数
        //         -doOnNext	输出元素之前的额外换怍
        //          -toList	打包对象为集合
        Observable.just(9).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer.toString() + "字符串";
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                //判断操作
                return true;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 基本实现
     *
     * @param view
     */
    public void scheduler(View view) {
        //  Scheduler 调度器，用于线程控制
        //        -Schedulers.immediate()   默认线程
        //        -Schedulers.newThread()    每次都创建新的线程   zhi
        //        -Schedulers.io()  包含线程池的机制，线程个数无限，可以复用空闲线程 zhi
        //        -Schedulers.computation()  CPU密集计算线程，线程池线程数和CPU数—致，处理圆形运算
        //        -AndroidSchedulers.mainThread()   Android更新界面的UI主线程
        //        subscribeOn	可执行多次，切换操作符的线程
        //        observeOn	    只需要执行一次，指定订阅者执行的线程
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(999);
                // e.onError(new Throwable("error"));
                e.onComplete();//
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return "九酒久";
            }
        }).subscribeOn(Schedulers.io())//订阅者执行在异步线程
                .observeOn(AndroidSchedulers.mainThread())//观察者执行在主线程
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(5000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }
}
