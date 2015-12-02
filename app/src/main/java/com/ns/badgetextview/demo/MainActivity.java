package com.ns.badgetextview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ns.badgetextview.BadgeTextView;

public class MainActivity extends AppCompatActivity {

    private BadgeTextView tvText, tvImage, tvImageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (BadgeTextView) findViewById(R.id.tv_text);
        tvText.setCount(8);

        tvImage = (BadgeTextView) findViewById(R.id.tv_image);
        tvImage.setCount(8);

        tvImageText = (BadgeTextView) findViewById(R.id.tv_image_text);
        tvImageText.setCount(8);
    }
}
