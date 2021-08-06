package com.xyc.apt_javapoet_autoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.xyc.annotationlib.MyAnnotation;
import com.xyc.annotationlib.DIActivity;
import com.xyc.annotationlib.DIView;

@DIActivity
public class MainActivity extends AppCompatActivity {
    @DIView(R.id.tv)
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @MyAnnotation(action = 2)
    public void test(){

    }
}