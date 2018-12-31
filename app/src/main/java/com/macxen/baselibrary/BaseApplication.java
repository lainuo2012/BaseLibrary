package com.macxen.baselibrary;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.linhonghong.dilutions.data.DilutionsData;
import com.linhonghong.dilutions.interfaces.DilutionsInterceptor;
import com.macxen.baselibrary.dilutions.DilutionUtil;
import com.macxen.baselibrary.http.HttpManager;


/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary
 * @time 2018/11/9 16:11
 * @describe describe
 */
public class BaseApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DilutionUtil.getInstance().init(new DilutionsInterceptor() {
            @Override
            public boolean interceptor(DilutionsData data) {
                Uri uri = data.getUri();
                Toast.makeText(context, uri.getPath(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
