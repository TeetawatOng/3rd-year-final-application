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

public class TestUnit_3 extends AppCompatActivity implements View.OnClickListener{
    Button btnNext3, btnSubmit3, btnBack3;
    TextView questionView3;
    RadioButton unit3c1, unit3c2, unit3c3, unit3c4;
    RadioGroup radioGroup3;

    int totalQuestion = QuestionManager.question3.length;
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1; // default not select answer
    int finalScore = 0;
    int[] previousAnswers; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_unit3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousAnswers = new int[totalQuestion]; // adding size to previousAnswer
        for (int i = 0; i < totalQuestion; i++) {
            previousAnswers[i] = -1;
        }

        questionView3 = findViewById(R.id.questionView3);
        radioGroup3 = findViewById(R.id.radioGroup3);
        unit3c1 = findViewById(R.id.unit3c1);
        unit3c2 = findViewById(R.id.unit3c2);
        unit3c3 = findViewById(R.id.unit3c3);
        unit3c4 = findViewById(R.id.unit3c4);
        btnNext3 = findViewById(R.id.btnNext3);
        btnSubmit3 = findViewById(R.id.btnSubmit3);
        btnBack3 = findViewById(R.id.btnBack3);
        btnNext3.setOnClickListener(this);
        btnSubmit3.setOnClickListener(this);
        btnBack3.setOnClickListener(this);

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit3c1) {
                    selectedAnswerIndex = 0;
                } else if (checkedId == R.id.unit3c2) {
                    selectedAnswerIndex = 1;
                } else if (checkedId == R.id.unit3c3) {
                    selectedAnswerIndex = 2;
                } else if (checkedId == R.id.unit3c4) {
                    selectedAnswerIndex = 3;
                }
            }
        });

        loadQuestion();
    }

    private void setButtonVisibility() {
        if (currentQuestionIndex >= 1) {
            btnBack3.setVisibility(View.VISIBLE);
        } else {
            btnBack3.setVisibility(View.INVISIBLE);
        }

        if (currentQuestionIndex < totalQuestion - 1) {
            // If 0-8 < 9 (not on the last question),
            btnNext3.setVisibility(View.VISIBLE);
            btnSubmit3.setVisibility(View.GONE);
        } else if (currentQuestionIndex == totalQuestion - 1) {
            // If 9 == 9 (on the last question)
            btnNext3.setVisibility(View.GONE);
            btnSubmit3.setVisibility(View.VISIBLE);
        }
    }

    public void loadQuestion(){
        questionView3.setText(QuestionManager.question3[currentQuestionIndex]);
        unit3c1.setText(QuestionManager.choices3[currentQuestionIndex][0]);
        unit3c2.setText(QuestionManager.choices3[currentQuestionIndex][1]);
        unit3c3.setText(QuestionManager.choices3[currentQuestionIndex][2]);
        unit3c4.setText(QuestionManager.choices3[currentQuestionIndex][3]);

        if (previousAnswers[currentQuestionIndex] != -1) {
            loadPrevAnswer();  // if already answer then load PrevAnswer
        } else {
            radioGroup3.clearCheck();
            selectedAnswerIndex = -1;
        }
    }

    public void loadPrevAnswer() {
        selectedAnswerIndex = previousAnswers[currentQuestionIndex];
        if (previousAnswers[currentQuestionIndex] == 0) unit3c1.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 1) unit3c2.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 2) unit3c3.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 3) unit3c4.setChecked(true);
    }

    public int checkAnswer() {
        int score = 0;
        for (int i = 0; i < totalQuestion; i++) {
            if (previousAnswers[i] == QuestionManager.answer3[i]) {
                score++; // if right score++
            }
        }
        return score;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNext3){
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex; // store current question answer
            currentQuestionIndex++;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnBack3) {
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            currentQuestionIndex--;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnSubmit3) {
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            finalScore = checkAnswer();
            Intent intent = new Intent(TestUnit_3.this, activity_sum_score_unit3.class);
            intent.putExtra("score",finalScore);
            intent.putExtra("total",totalQuestion);
            startActivity(intent);
            finish();
        }
    }
}