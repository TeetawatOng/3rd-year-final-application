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

public class unit1 extends AppCompatActivity implements View.OnClickListener {
    Button btnTest1;
    WebView web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnTest1 = findViewById(R.id.btnTest1);
        btnTest1.setOnClickListener(this);
        web1 = findViewById(R.id.web1);
        web1.loadUrl("file:///android_asset/lessons/unit1.html");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTest1){
            Intent goTest1 = new Intent(unit1.this, TestUnit_1.class);
            startActivity(goTest1);
        }
    }
}