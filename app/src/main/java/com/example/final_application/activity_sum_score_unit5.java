package com.example.final_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_sum_score_unit5 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest5,btnbacklesson5;
    TextView scoreView5, textExcellent5, textGood5, textBad5;
    ImageButton imageExcellent5, imageGood5 , imageBad5;
    String resultText = "";
    MyDatabaseHelper myDatabaseHelper;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnbacklesson5 = findViewById(R.id.btnbacklesson5);
        btnRetest5 = findViewById(R.id.btnRetest5);
        scoreView5 = findViewById(R.id.scoreView5);
        textBad5 = findViewById(R.id.textBad5);
        textGood5 = findViewById(R.id.textGood5);
        textExcellent5 = findViewById(R.id.textExcellent5);
        imageExcellent5 = findViewById(R.id.imageExcellent5);
        imageGood5 = findViewById(R.id.imageGood5);
        imageBad5 = findViewById(R.id.imageBad5);
        btnRetest5.setOnClickListener(this);
        btnbacklesson5.setOnClickListener(this);

        myDatabaseHelper = new MyDatabaseHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);
        String username = sharedPreferences.getString(KEY_USERNAME,null);

        resultText = finalScore + "/" + totalQuestion;
        scoreView5.setText(resultText);
        if (finalScore > 9) {
            textExcellent5.setVisibility(View.VISIBLE);
            imageExcellent5.setVisibility(View.VISIBLE);
        } else if (finalScore >= 5) {
            textGood5.setVisibility(View.VISIBLE);
            imageGood5.setVisibility(View.VISIBLE);
        } else {
            textBad5.setVisibility(View.VISIBLE);
            imageBad5.setVisibility(View.VISIBLE);
        }

        if (username != null) {
            boolean results = myDatabaseHelper.insertTestScore(username, "unit5", finalScore);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRetest5){
            Intent goTest5 = new Intent(activity_sum_score_unit5.this, TestUnit_5.class);
            startActivity(goTest5);
        }
        else if (view.getId() == R.id.btnbacklesson5) {
            Intent golesson5 = new Intent(activity_sum_score_unit5.this, lesson.class);
            startActivity(golesson5);
        }
    }


}