package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText);
                ImageView imageView = findViewById(R.id.imageView);
                String imageUrl = editText.getText().toString().trim();
                if (!imageUrl.isEmpty()) {
                    new Thread(() -> {
                        try {
                            URL url = new URL(imageUrl);
                            InputStream inputStream = (InputStream) url.getContent();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            runOnUiThread(() -> imageView.setImageBitmap(bitmap));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
//                if (!imageUrl.isEmpty()) {
//                    Glide.with(v.getContext())
//                            .load(imageUrl)
//                            .into(imageView);
//                }
            }
        });
    }
}