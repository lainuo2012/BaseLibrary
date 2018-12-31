package com.macxen.baselibrary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.macxen.baselibrary.R;
import com.macxen.baselibrary.http.HttpManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary.test
 * @time 2018/11/13 20:30
 * @describe describe
 */
public class TestActNet extends AppCompatActivity {

    private HttpManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test1);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = manager.getRetrofit().create(WeatherService.class).getCityWeather("php","鹅鹅鹅");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            ResponseBody body = response.body();
                        try {
                            String result = body.string();
                            Log.d("dddd",result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("ddd",t.getMessage());
                    }
                });
            }
        });
        manager = new  HttpManager.Builder().setBaseUrl("http://api.qingyunke.com/").setHttps(true).setReadTimeOut(15*1000).setWriteTimeOut(20*1000).setConnectTimeOut(20*1000)
                .build();
    }
}
