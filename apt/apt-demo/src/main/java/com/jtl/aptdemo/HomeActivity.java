package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jtl.router_annotation.BindView;
import com.jtl.router_annotation.Route;

@Route(path = "/demo/home")
public class HomeActivity extends AppCompatActivity {

    @BindView(id = R.id.constraint_home_layout)
    ConstraintLayout constraintLayout;

    @BindView(id = R.id.tv_home_content)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        com.jtl.router_api.bindview.BindView.getInstance().inject(this);

        Intent intent = getIntent();

        int id = intent.getIntExtra("imageId",0);
        textView.setText(intent.getIntExtra("int",0)+intent.getStringExtra("path"));
        constraintLayout.setBackground(getResources().getDrawable(id));
    }
}