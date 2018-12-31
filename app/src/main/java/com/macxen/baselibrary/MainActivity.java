package com.macxen.baselibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.linhonghong.dilutions.annotations.ActivityProtocol;
import com.linhonghong.dilutions.annotations.CustomAnimation;
import com.linhonghong.dilutions.data.DilutionsData;
import com.linhonghong.dilutions.interfaces.DilutionsCallBack;
import com.macxen.baselibrary.constants.UriConstants;
import com.macxen.baselibrary.dilutions.DilutionUtil;

import java.util.HashMap;

/**
 * 在支持路由的页面上添加注解(必选)
 * 这里的路径需要注意的是至少需要有两级，/xx/xx
 */
@ActivityProtocol(UriConstants.MAINACT)
@CustomAnimation(enter = android.R.anim.fade_in, exit = android.R.anim.fade_out)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        Button test = findViewById(R.id.btn_test_method);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("userId", 1);
                dataMap.put("userName", "lainuo");
                dataMap.put("testObject", new TestObject(1, "laiNuo"));
//                Dilutions.create().formatProtocolService(UriConstants.baseUri + UriConstants.TESTACT
//                        , dataMap, null);
                DilutionUtil.getInstance().startAct(UriConstants.TESTACT, dataMap);
                /*Dilutions.create().setDilutionsPathInterceptor(UriConstants.TESTACT, new DilutionsPathInterceptor() {
                    @Override
                    public boolean interceptor(DilutionsData data) {
                        Uri uri = data.getUri();
                        Toast.makeText(MainActivity.this, uri.getPath(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                });*/
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("username", "lainuo");
                DilutionUtil.getInstance().invokeMethod("/method/test1", data, new DilutionsCallBack() {
                    @Override
                    public void onDilutions(DilutionsData data) {
                        Object result = data.getResult();
                        Toast.makeText(MainActivity.this, (int) result + "", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
