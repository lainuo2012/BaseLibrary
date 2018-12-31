package com.macxen.baselibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.linhonghong.dilutions.Dilutions;
import com.linhonghong.dilutions.annotations.ActivityProtocol;
import com.linhonghong.dilutions.annotations.ActivityProtocolExtra;
import com.linhonghong.dilutions.annotations.CustomAnimation;
import com.linhonghong.dilutions.annotations.MethodParam;
import com.linhonghong.dilutions.annotations.MethodProtocol;
import com.macxen.baselibrary.constants.UriConstants;

/**
 * @author lainuo
 * @name BaseLibrary
 * @class name：com.macxen.baselibrary
 * @time 2018/11/9 16:21
 * @describe describe
 */
@ActivityProtocol(UriConstants.TESTACT)
@CustomAnimation(enter = android.R.anim.fade_in, exit = android.R.anim.fade_out)
public class TestActivity1 extends AppCompatActivity {
    @ActivityProtocolExtra("userId")
    int userId;
    @ActivityProtocolExtra("userName")
    String userName;
    @ActivityProtocolExtra("testObject")
    TestObject object;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test1);
        Dilutions.create().register(this);
        Toast.makeText(this, userId + userName, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, object.getUserId() + object.getUserName(), Toast.LENGTH_LONG).show();
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dilutions.create().formatProtocolService(UriConstants.baseUri + UriConstants.MAINACT);
            }
        });
    }

    @MethodProtocol("/method/test1")
    public int method(@MethodParam("username") String userName) {
        Toast.makeText(BaseApplication.context, userName, Toast.LENGTH_LONG).show();
        return 0;
    }
}
