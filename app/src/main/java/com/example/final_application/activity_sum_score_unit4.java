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

public class activity_sum_score_unit4 extends AppCompatActivity implements View.OnClickListener {
    Button btnRetest4;
    TextView scoreView4, textExcellent, textGood, textBad;
    ImageButton imageExcellent, imageGood, imageBad;
    String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sum_score_unit4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRetest4 = findViewById(R.id.btnRetest4);
        scoreView4 = findViewById(R.id.scoreView4);
        textBad = findViewById(R.id.textBad4);
        textGood = findViewById(R.id.textGood4);
        textExcellent = findViewById(R.id.textExcellent4);
        imageExcellent = findViewById(R.id.imageExcellent4);
        imageGood = findViewById(R.id.imageGood4);
        imageBad = findViewById(R.id.imageBad4);
        btnRetest4.setOnClickListener(this);

        showScore();
    }

    private void showScore() {
        int finalScore = getIntent().getIntExtra("score",0);
        int totalQuestion = getIntent().getIntExtra("total",0);

        resultText = finalScore + "/" + totalQuestion;
        scoreView4.setText(resultText);
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
        if (view.getId() == R.id.btnRetest4){
            Intent goTest4 = new Intent(activity_sum_score_unit4.this, TestUnit_4.class);
            startActivity(goTest4);
        }
    }
}