package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.jtl.router_annotation.BindView;
import com.jtl.router_annotation.Route;

@Route(path = "/demo/home")
public class HomeActivity extends AppCompatActivity {

    @BindView(id = R.id.constraint_home_layout)
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}