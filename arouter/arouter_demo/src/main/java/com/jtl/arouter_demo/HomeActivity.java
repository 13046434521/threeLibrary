package com.jtl.arouter_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jtl.base.Const;

@Route(path = Const.DEMO_HOME)
public class HomeActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = findViewById(R.id.btn_home_content);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.getIntent().getStringExtra("name"))
                .append("\n")
                .append(this.getIntent().getLongExtra("time",0));

        button.setText(stringBuffer.toString());
    }

    public void Login(View view) {
        ARouter.getInstance()
                .build(Const.LOGIN_LOGIN)
                .withString("name",this.getLocalClassName())
                .withLong("time",System.currentTimeMillis())
                .navigation();
    }
}