package com.jtl.arouter_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jtl.base.Const;

@Route(path = Const.LOGIN_LOGIN)
public class LoginActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.btn_login_login);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.getIntent().getStringExtra("name"))
                .append("\n")
                .append(this.getIntent().getLongExtra("time",0));

        button.setText(stringBuffer.toString());
    }

    public void login(View view) {
        ARouter.getInstance().build(Const.DEMO_MAIN).withString("name","登陆模块login").withLong("time",System.currentTimeMillis()).navigation();
    }
}