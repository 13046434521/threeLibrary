package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtl.router_annotation.BindView;
import com.jtl.router_annotation.Route;
import com.jtl.router_api.Router;
import com.jtl.router_api.Router$$BindView;

@Route(path = "qunima")
public class MainActivity extends AppCompatActivity {

    @BindView(id = R.id.tv_main_content)
    TextView textview;
    @BindView(id = R.id.iv_main_shazi)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Router.getInstance().inject(this);

        MainActivity$$BindView mainActivity$$BindView = new MainActivity$$BindView();
        mainActivity$$BindView.init(this);

        // 希望
        Router.getInstance().inject(this);

        textview.setText("可以类啊");
    }
}