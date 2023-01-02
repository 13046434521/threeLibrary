package com.jtl.arouter_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jtl.base.Const;


@Route(path = Const.DEMO_MAIN)
public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_main_content);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.getIntent().getStringExtra("name"))
                .append("\n")
                .append(this.getIntent().getLongExtra("time",0));

        button.setText(stringBuffer.toString());
    }

    public void Main(View view) {
        ARouter.getInstance()
                .build(Const.DEMO_HOME)
                .withString("name",this.getLocalClassName())
                .withLong("time",System.currentTimeMillis())
                .navigation();
    }
}