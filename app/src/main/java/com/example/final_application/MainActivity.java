package com.example.final_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    TextView textRegister;
    TextInputEditText userInput, passwordInput;
    SharedPreferences sharedPreferences; // login tokens
    MyDatabaseHelper myDatabaseHelper;

    private static final String SHARED_PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnLogin);
        textRegister = findViewById(R.id.textRegister);
        userInput = findViewById(R.id.userInput);
        passwordInput = findViewById(R.id.passwordInput);

        btnLogin.setOnClickListener(this);
        textRegister.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        myDatabaseHelper = new MyDatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            String username = userInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (checkUsername(username) && checkPassword(password)){
                if (myDatabaseHelper.checkLogin(username,password)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME, username);
                    editor.apply();
                    Intent goLesson = new Intent(MainActivity.this, lesson.class);
                    startActivity(goLesson);
                } else {
                    Toast.makeText(this, "ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง !", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view.getId() == R.id.textRegister) {
            Intent goRegister = new Intent(MainActivity.this, Register.class);
            startActivity(goRegister);
        }
    }

    private boolean checkUsername(String username) {
        if(TextUtils.isEmpty(username)){
            userInput.setError("กรุณากรอกชื่อผู้ใช้");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        if(TextUtils.isEmpty(password)){
            passwordInput.setError("กรุณากรอกรหัสผ่าน");
            return false;
        }
        return true;
    }
}