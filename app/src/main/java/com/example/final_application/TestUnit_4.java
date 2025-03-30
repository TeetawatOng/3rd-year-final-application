package com.example.final_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestUnit_4 extends AppCompatActivity implements View.OnClickListener {
    Button btnNext4, btnSubmit4, btnBack4;
    TextView questionView4;
    RadioButton unit4c1, unit4c2, unit4c3, unit4c4;
    RadioGroup radioGroup4;

    int totalQuestion = QuestionManager.question4.length;
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1; // default not select answer
    int finalScore = 0;
    int[] previousAnswers; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_unit4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousAnswers = new int[totalQuestion]; // adding size to previousAnswer
        for (int i = 0; i < totalQuestion; i++) {
            previousAnswers[i] = -1;
        }

        questionView4 = findViewById(R.id.questionView4);
        radioGroup4 = findViewById(R.id.radioGroup4);
        unit4c1 = findViewById(R.id.unit4c1);
        unit4c2 = findViewById(R.id.unit4c2);
        unit4c3 = findViewById(R.id.unit4c3);
        unit4c4 = findViewById(R.id.unit4c4);
        btnNext4 = findViewById(R.id.btnNext4);
        btnSubmit4 = findViewById(R.id.btnSubmit4);
        btnBack4 = findViewById(R.id.btnBack4);
        btnNext4.setOnClickListener(this);
        btnSubmit4.setOnClickListener(this);
        btnBack4.setOnClickListener(this);

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit4c1) {
                    selectedAnswerIndex = 0;
                } else if (checkedId == R.id.unit4c2) {
                    selectedAnswerIndex = 1;
                } else if (checkedId == R.id.unit4c3) {
                    selectedAnswerIndex = 2;
                } else if (checkedId == R.id.unit4c4) {
                    selectedAnswerIndex = 3;
                }
            }
        });

        loadQuestion();
    }

    private void setButtonVisibility() {
        if (currentQuestionIndex >= 1) {
            btnBack4.setVisibility(View.VISIBLE);
        } else {
            btnBack4.setVisibility(View.INVISIBLE);
        }

        if (currentQuestionIndex < totalQuestion - 1) {
            // If 0-8 < 9 (not on the last question),
            btnNext4.setVisibility(View.VISIBLE);
            btnSubmit4.setVisibility(View.GONE);
        } else if (currentQuestionIndex == totalQuestion - 1) {
            // If 9 == 9 (on the last question)
            btnNext4.setVisibility(View.GONE);
            btnSubmit4.setVisibility(View.VISIBLE);
        }
    }

    public void loadQuestion(){
        questionView4.setText(QuestionManager.question4[currentQuestionIndex]);
        unit4c1.setText(QuestionManager.choices4[currentQuestionIndex][0]);
        unit4c2.setText(QuestionManager.choices4[currentQuestionIndex][1]);
        unit4c3.setText(QuestionManager.choices4[currentQuestionIndex][2]);
        unit4c4.setText(QuestionManager.choices4[currentQuestionIndex][3]);

        if (previousAnswers[currentQuestionIndex] != -1) {
            loadPrevAnswer();  // if already answer then load PrevAnswer
        } else {
            radioGroup4.clearCheck();
            selectedAnswerIndex = -1;
        }
    }

    public void loadPrevAnswer() {
        selectedAnswerIndex = previousAnswers[currentQuestionIndex];
        if (previousAnswers[currentQuestionIndex] == 0) unit4c1.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 1) unit4c2.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 2) unit4c3.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 3) unit4c4.setChecked(true);
    }

    public int checkAnswer() {
        int score = 0;
        for (int i = 0; i < totalQuestion; i++) {
            if (previousAnswers[i] == QuestionManager.answer4[i]) {
                score++; // if right score++
            }
        }
        return score;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNext4){
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex; // store current question answer
            currentQuestionIndex++;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnBack4) {
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            currentQuestionIndex--;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnSubmit4){
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            finalScore = checkAnswer();
            Intent intent = new Intent(TestUnit_4.this, activity_sum_score_unit4.class);
            intent.putExtra("score",finalScore);
            intent.putExtra("total",totalQuestion);
            startActivity(intent);
            finish();
        }
    }
}