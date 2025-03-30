package com.example.final_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class unit2 extends AppCompatActivity implements View.OnClickListener {
    Button btnTest2;
    WebView web2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnTest2 = findViewById(R.id.btnTest2);
        btnTest2.setOnClickListener(this);
        web2 = findViewById(R.id.web2);
        web2.loadUrl("file:///android_asset/lessons/unit2.html");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTest2){
            Intent goTest2 = new Intent(unit2.this, TestUnit_2.class);
            startActivity(goTest2);
        }
    }
}