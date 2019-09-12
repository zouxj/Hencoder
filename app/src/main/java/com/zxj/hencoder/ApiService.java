package com.zxj.hencoder;

import io.reactivex.Single;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * function:
 *
 * <p>
 * Created by Zxj on 2019/8/29.
 */
public interface ApiService {
    @GET("/")
    Call<ResponseBody> getRetrofitTest();
    @GET("/")
    Single<Response> getRxJavaTest();
}
