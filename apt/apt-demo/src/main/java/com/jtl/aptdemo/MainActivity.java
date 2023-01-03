package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.jtl.router_annotation.BindView;
import com.jtl.router_api.Router;

public class MainActivity extends AppCompatActivity {

    @BindView(id = R.id.tv_main_content)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Router.getInstance().router$$BindView.idmap.put(textview, R.id.tv_main_content);
        Router.getInstance().inject(this);

        //textview.setText("可以类啊");

    }
}