package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jtl.router_annotation.Route;
import com.jtl.router_api.bindview.BindView;
import com.jtl.router_api.router.Router;

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

        Router.getInstance().build("/demo/home")
                .withData("int",123)
                .withData("path","你好啊")
                .withData("imageId",R.mipmap.shazi)
                .navigation();

        textview.setText("可以类啊");
    }
}