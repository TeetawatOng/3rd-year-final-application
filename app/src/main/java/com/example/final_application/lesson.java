package com.example.final_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class lesson extends AppCompatActivity implements View.OnClickListener {
    Button btnUnit1, btnUnit2, btnUnit3, btnUnit4, btnUnit5;
    TextView textUser, textLogout;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUnit1 = findViewById(R.id.btnUnit1);
        btnUnit2 = findViewById(R.id.btnUnit2);
        btnUnit3 = findViewById(R.id.btnUnit3);
        btnUnit4 = findViewById(R.id.btnUnit4);
        btnUnit5 = findViewById(R.id.btnUnit5);
        textUser = findViewById(R.id.textUser);
        textLogout = findViewById(R.id.textLogout);
        btnUnit1.setOnClickListener(this);
        btnUnit2.setOnClickListener(this);
        btnUnit3.setOnClickListener(this);
        btnUnit4.setOnClickListener(this);
        btnUnit5.setOnClickListener(this);
        textLogout.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME,null);

        if(username != null){
            textUser.setText("สวัสดีผู้ใช้งาน " + username);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnUnit1){
            Intent goUnit1 = new Intent(lesson.this, unit1.class);
            startActivity(goUnit1);
        } else if (view.getId() == R.id.btnUnit2) {
            Intent goUnit2 = new Intent(lesson.this, unit2.class);
            startActivity(goUnit2);
        } else if (view.getId() == R.id.btnUnit3) {
            Intent goUnit3 = new Intent(lesson.this, unit3.class);
            startActivity(goUnit3);
        } else if (view.getId() == R.id.btnUnit4) {
            Intent goUnit4 = new Intent(lesson.this, unit4.class);
            startActivity(goUnit4);
        } else if (view.getId() == R.id.btnUnit5) {
            Intent goUnit5 = new Intent(lesson.this, unit5.class);
            startActivity(goUnit5);
        } else if (view.getId() == R.id.textLogout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            // Back to login Activity
            Intent goLogin = new Intent(lesson.this, MainActivity.class);
            startActivity(goLogin);
            finish();
        }
    }
}