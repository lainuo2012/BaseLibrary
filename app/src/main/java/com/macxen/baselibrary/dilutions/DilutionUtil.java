package com.macxen.baselibrary.dilutions;

import com.alibaba.fastjson.JSON;
import com.linhonghong.dilutions.Dilutions;
import com.linhonghong.dilutions.data.DilutionsConfig;
import com.linhonghong.dilutions.data.DilutionsConfigFactory;
import com.linhonghong.dilutions.interfaces.DilutionsCallBack;
import com.linhonghong.dilutions.interfaces.DilutionsInterceptor;
import com.linhonghong.dilutions.utils.DilutionsUriBuilder;
import com.macxen.baselibrary.BaseApplication;
import com.macxen.baselibrary.constants.UriConstants;

import java.util.HashMap;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary.dilutions
 * @time 2018/11/12 11:50
 * @describe describe
 */
public class DilutionUtil implements Idilution {
    private static volatile DilutionUtil instance;
    private DilutionsInterceptor interceptor;

    private DilutionUtil() {

    }

    public static DilutionUtil getInstance() {
        if (instance == null) {
            synchronized (DilutionUtil.class) {
                if (instance == null) {
                    instance = new DilutionUtil();
                }
            }
        }
        return instance;
    }

    @Override
    public void init(DilutionsInterceptor interceptor) {
        Dilutions.init(BaseApplication.context);
        this.interceptor = interceptor;
    }

    @Override
    public void startAct(String path, HashMap data) {
        String uri;
        if (data != null) {
            uri = DilutionsUriBuilder.buildUri(UriConstants.baseUri, path, JSON.toJSONString(data));
        } else {
            uri = DilutionsUriBuilder.buildUri(UriConstants.baseUri, path, "");
        }
        Dilutions.create().formatProtocolService(uri, getConfig(interceptor));

    }

    @Override
    public void invokeMethod(String path, HashMap data, DilutionsCallBack callBack) {
        String uri;
        if (data != null) {
            uri = DilutionsUriBuilder.buildUri(UriConstants.baseUri, path, JSON.toJSONString(data));
        } else {
            uri = DilutionsUriBuilder.buildUri(UriConstants.baseUri, path, "");
        }
        Dilutions.create().formatProtocolServiceWithCallback(uri, callBack);
    }

    private DilutionsConfig getConfig(DilutionsInterceptor interceptor) {
        DilutionsConfig.Builder builder =
                DilutionsConfigFactory.newBuilder(null, interceptor, "");
        return builder.build();
    }

}
