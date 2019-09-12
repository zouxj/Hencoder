package com.zxj.hencoder;

import android.annotation.TargetApi;
import android.icu.util.TimeUnit;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.io.IOException;
import java.time.Duration;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxJava();
    }

    public void retrofit(){
        Retrofit   retrofit   = new Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getRetrofitTest().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("retrofit", response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("retrofit", t.toString());
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void okhttp(){
        OkHttpClient  okHttpClient =new OkHttpClient();
        okHttpClient.newBuilder().readTimeout(Duration.ofMillis(1000)).cache(null).addInterceptor(null);
        final Request request      =new Request.Builder().url("https://www.baidu.com").tag("").build();
        okhttp3.Call  call         =okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                Log.i("okhttp",response.message());
            }
        });
        call.cancel();
    }
    public void rxJava(){
        Retrofit   retrofit   = new Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getRxJavaTest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new SingleObserver<okhttp3.Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(okhttp3.Response response) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

}
