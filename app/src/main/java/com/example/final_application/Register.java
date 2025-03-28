package com.example.final_application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    TextView textLogin;
    TextInputEditText textEmail, textRegisUser, textRegisPassword, textRegisConfirm;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegister = findViewById(R.id.btnRegister);
        textLogin = findViewById(R.id.textLogin);
        textEmail = findViewById(R.id.textEmail);
        textRegisUser = findViewById(R.id.textRegisUser);
        textRegisPassword = findViewById(R.id.textRegisPassword);
        textRegisConfirm = findViewById(R.id.textRegisConfirm);

        btnRegister.setOnClickListener(this);
        textLogin.setOnClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegister){
            Registration();
        } else if (view.getId() == R.id.textLogin) {
            Intent goLogin = new Intent(Register.this, MainActivity.class);
            startActivity(goLogin);
        }
    }

    private void Registration() {
        String email = textEmail.getText().toString().trim();
        String username = textRegisUser.getText().toString().trim();
        String password = textRegisPassword.getText().toString().trim();
        String confirmPassword = textRegisConfirm.getText().toString().trim();

        // input validation
        if (checkEmail(email)) return;
        if (checkUsername(username)) return;
        if (checkPassword(password)) return;
        if (checkConfirm(confirmPassword)) return;
        if (checkPasswordEquals(password, confirmPassword)) return;

        if (myDatabaseHelper.checkUserExists(username,email)){
            Toast.makeText(this, "ชื่อผู้ใช้หรืออีเมลนี้ถูกใช้งานแล้ว!", Toast.LENGTH_SHORT).show();
        } else {
            boolean results = myDatabaseHelper.insertUserData(email,username,password);
            if (results) {
                Toast.makeText(this, "ลงทะเบียนสำเร็จ!", Toast.LENGTH_SHORT).show();
                goLogin();
            }
        }
    }

    private void goLogin() {
        Intent goLesson = new Intent(Register.this, MainActivity.class);
        startActivity(goLesson);
        finish();
    }

    private boolean checkPasswordEquals(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            textRegisConfirm.setError("รหัสผ่านไม่ตรงกัน");
            return true;
        }
        return false;
    }

    private boolean checkConfirm(String confirmPassword) {
        if (TextUtils.isEmpty(confirmPassword)) {
            textRegisConfirm.setError("กรุณากรอกยืนยันรหัสผ่าน");
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            textRegisPassword.setError("กรุณากรอกรหัสผ่าน");
            return true;
        }
        return false;
    }

    private boolean checkUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            textRegisUser.setError("กรุณากรอกชื่อผู้ใช้");
            return true;
        }
        return false;
    }

    private boolean checkEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            textEmail.setError("กรุณากรอกอีเมล");
            return true;
        }
        return false;
    }
}