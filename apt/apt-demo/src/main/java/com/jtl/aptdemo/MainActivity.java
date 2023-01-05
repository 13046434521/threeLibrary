package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtl.router_annotation.Route;
import com.jtl.router_api.BindView;
import com.jtl.router_api.Router;

@Route(path = "/apt/main")
public class MainActivity extends AppCompatActivity {

    @com.jtl.router_annotation.BindView(id = R.id.tv_main_content)
    TextView textview;
    @com.jtl.router_annotation.BindView(id = R.id.iv_main_shazi)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindView.getInstance().inject(this);

        Router.getInstance().navigation(this,"/demo/home");
        textview.setText("可以类啊");
    }
}