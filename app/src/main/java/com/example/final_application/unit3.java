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

public class unit3 extends AppCompatActivity implements View.OnClickListener {
    Button btnTest3;
    WebView web3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unit3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnTest3 = findViewById(R.id.btnTest3);
        btnTest3.setOnClickListener(this);
        web3 = findViewById(R.id.web3);
        web3.loadUrl("file:///android_asset/lessons/unit3.html");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnTest3){
            Intent goTest3 = new Intent(unit3.this, TestUnit_3.class);
            startActivity(goTest3);
        }
    }
}