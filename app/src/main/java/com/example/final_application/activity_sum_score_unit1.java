package com.example.final_application;

import android.content.Intent;
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

public class activity_sum_score_unit1 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest1;
    TextView scoreView1, textExcellent, textGood, textBad;
    ImageButton imageExcellent, imageGood, imageBad;
    String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRetest1 = findViewById(R.id.btnRetest1);
        scoreView1 = findViewById(R.id.scoreView1);
        textBad = findViewById(R.id.textBad1);
        textGood = findViewById(R.id.textGood1);
        textExcellent = findViewById(R.id.textExcellent1);
        imageExcellent = findViewById(R.id.imageExcellent1);
        imageGood = findViewById(R.id.imageGood1);
        imageBad = findViewById(R.id.imageBad1);
        btnRetest1.setOnClickListener(this);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);

        resultText = finalScore + "/" + totalQuestion;
        scoreView1.setText(resultText);
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
        if (view.getId() == R.id.btnRetest1){
            Intent goTest1 = new Intent(activity_sum_score_unit1.this, TestUnit_1.class);
            startActivity(goTest1);
        }
    }
}