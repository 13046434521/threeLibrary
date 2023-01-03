package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.jtl.router_annotation.BindView;

public class HomeActivity extends AppCompatActivity {

    @BindView(id = R.id.constraint_home_layout)
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}