package com.example.final_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_sum_score_unit3 extends AppCompatActivity implements View.OnClickListener{
    Button btnRetest3,btnbacklesson3;
    TextView scoreView3, textExcellent, textGood, textBad;
    ImageButton imageExcellent, imageGood, imageBad;
    String resultText = "";
    MyDatabaseHelper myDatabaseHelper;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnbacklesson3 = findViewById(R.id.btnbacklesson3);
        btnRetest3 = findViewById(R.id.btnRetest3);
        scoreView3 = findViewById(R.id.scoreView3);
        textBad = findViewById(R.id.textBad3);
        textGood = findViewById(R.id.textGood3);
        textExcellent = findViewById(R.id.textExcellent3);
        imageExcellent = findViewById(R.id.imageExcellent3);
        imageGood = findViewById(R.id.imageGood3);
        imageBad = findViewById(R.id.imageBad3);
        btnRetest3.setOnClickListener(this);
        btnbacklesson3.setOnClickListener(this);

        myDatabaseHelper = new MyDatabaseHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score", 0);
        int totalQuestion = getIntent().getIntExtra("total", 0);
        String username = sharedPreferences.getString(KEY_USERNAME,null);

        resultText = finalScore + "/" + totalQuestion;
        scoreView3.setText(resultText);
        if (finalScore > 9) {
            textExcellent.setVisibility(View.VISIBLE);
            imageExcellent.setVisibility(View.VISIBLE);
        } else if (finalScore >= 5) {
            textGood.setVisibility(View.VISIBLE);
            imageGood.setVisibility(View.VISIBLE);
        } else {
            textBad.setVisibility(View.VISIBLE);
            imageBad.setVisibility(View.VISIBLE);
        }
        if (username != null) {
            boolean results = myDatabaseHelper.insertTestScore(username, "unit3", finalScore);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRetest3){
            Intent goTest3 = new Intent(activity_sum_score_unit3.this, TestUnit_3.class);
            startActivity(goTest3);
        }
        else if (view.getId() == R.id.btnbacklesson3) {
            Intent golesson3 = new Intent(activity_sum_score_unit3.this, lesson.class);
            startActivity(golesson3);
        }
    }
}