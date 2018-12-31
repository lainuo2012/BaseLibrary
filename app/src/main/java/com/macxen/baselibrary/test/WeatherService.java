package com.macxen.baselibrary.test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary.test
 * @time 2018/11/13 20:23
 * @describe describe
 */
public interface WeatherService {

    @GET("api.{php}?key=free&appid=0")
    Call<ResponseBody> getCityWeather(@Path("php") String p, @Query("msg") String cityName);

}
