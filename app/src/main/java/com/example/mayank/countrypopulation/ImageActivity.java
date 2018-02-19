package com.example.mayank.countrypopulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String imageRes = getIntent().getStringExtra("image-res");
        ImageView imageView = findViewById(R.id.largeImage);
        Glide.with(imageView.getContext())
                .load(imageRes)
                .into(imageView);
    }
}
