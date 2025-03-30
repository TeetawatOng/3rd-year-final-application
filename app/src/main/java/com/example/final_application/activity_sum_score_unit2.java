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

public class activity_sum_score_unit2 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest2;
    TextView scoreView2, textExcellent, textGood, textBad;
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
        setContentView(R.layout.activity_sum_score_unit2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRetest2 = findViewById(R.id.btnRetest2);
        scoreView2 = findViewById(R.id.scoreView2);
        textBad = findViewById(R.id.textBad2);
        textGood = findViewById(R.id.textGood2);
        textExcellent = findViewById(R.id.textExcellent2);
        imageExcellent = findViewById(R.id.imageExcellent2);
        imageGood = findViewById(R.id.imageGood2);
        imageBad = findViewById(R.id.imageBad2);
        btnRetest2.setOnClickListener(this);

        myDatabaseHelper = new MyDatabaseHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);

        resultText = finalScore + "/" + totalQuestion;
        scoreView2.setText(resultText);
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
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRetest2){
            Intent goTest2 = new Intent(activity_sum_score_unit2.this, TestUnit_2.class);
            startActivity(goTest2);
        }
    }
}