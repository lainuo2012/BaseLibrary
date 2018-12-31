package com.macxen.baselibrary.dilutions;

import com.linhonghong.dilutions.interfaces.DilutionsCallBack;
import com.linhonghong.dilutions.interfaces.DilutionsInterceptor;

import java.util.HashMap;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary.dilutions
 * @time 2018/11/12 11:52
 * @describe describe
 */
public interface Idilution {

    void init(DilutionsInterceptor interceptor);

    void startAct(String path, HashMap data);

    void invokeMethod(String path, HashMap data, DilutionsCallBack callBack);
}
