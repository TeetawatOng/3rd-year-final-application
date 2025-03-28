package com.example.final_application;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class unit4 extends AppCompatActivity implements View.OnClickListener {
    Button btnTest4;
    WebView web4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnTest4 = findViewById(R.id.btnTest4);
        btnTest4.setOnClickListener(this);
        web4 = findViewById(R.id.web4);
        web4.loadUrl("file:///android_asset/lessons/unit4.html");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTest4){
            Intent goTest4 = new Intent(unit4.this, TestUnit_4.class);
            startActivity(goTest4);
        }
    }
}