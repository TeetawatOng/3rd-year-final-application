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

public class TestUnit_2 extends AppCompatActivity implements View.OnClickListener{
    Button btnNext2, btnSubmit2, btnBack2;
    TextView questionView2;
    RadioButton unit2c1, unit2c2, unit2c3, unit2c4;
    RadioGroup radioGroup2;

    int totalQuestion = QuestionManager.question2.length;
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1; // default not select answer
    int finalScore = 0;
    int[] previousAnswers; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_unit2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousAnswers = new int[totalQuestion]; // adding size to previousAnswer
        for (int i = 0; i < totalQuestion; i++) {
            previousAnswers[i] = -1;
        }

        questionView2 = findViewById(R.id.questionView2);
        radioGroup2 = findViewById(R.id.radioGroup2);
        unit2c1 = findViewById(R.id.unit2c1);
        unit2c2 = findViewById(R.id.unit2c2);
        unit2c3 = findViewById(R.id.unit2c3);
        unit2c4 = findViewById(R.id.unit2c4);
        btnNext2 = findViewById(R.id.btnNext2);
        btnSubmit2 = findViewById(R.id.btnSubmit2);
        btnBack2 = findViewById(R.id.btnBack2);
        btnNext2.setOnClickListener(this);
        btnSubmit2.setOnClickListener(this);
        btnBack2.setOnClickListener(this);

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit2c1) {
                    selectedAnswerIndex = 0;
                } else if (checkedId == R.id.unit2c2) {
                    selectedAnswerIndex = 1;
                } else if (checkedId == R.id.unit2c3) {
                    selectedAnswerIndex = 2;
                } else if (checkedId == R.id.unit2c4) {
                    selectedAnswerIndex = 3;
                }
            }
        });

        loadQuestion();
    }

    private void setButtonVisibility() {
        if (currentQuestionIndex >= 1) {
            btnBack2.setVisibility(View.VISIBLE);
        } else {
            btnBack2.setVisibility(View.INVISIBLE);
        }

        if (currentQuestionIndex < totalQuestion - 1) {
            // If 0-8 < 9 (not on the last question),
            btnNext2.setVisibility(View.VISIBLE);
            btnSubmit2.setVisibility(View.GONE);
        } else if (currentQuestionIndex == totalQuestion - 1) {
            // If 9 == 9 (on the last question)
            btnNext2.setVisibility(View.GONE);
            btnSubmit2.setVisibility(View.VISIBLE);
        }
    }

    public void loadQuestion(){
        questionView2.setText(QuestionManager.question2[currentQuestionIndex]);
        unit2c1.setText(QuestionManager.choices2[currentQuestionIndex][0]);
        unit2c2.setText(QuestionManager.choices2[currentQuestionIndex][1]);
        unit2c3.setText(QuestionManager.choices2[currentQuestionIndex][2]);
        unit2c4.setText(QuestionManager.choices2[currentQuestionIndex][3]);

        if (previousAnswers[currentQuestionIndex] != -1) {
            loadPrevAnswer();  // if already answer then load PrevAnswer
        } else {
            radioGroup2.clearCheck();
            selectedAnswerIndex = -1;
        }
    }

    public void loadPrevAnswer() {
        selectedAnswerIndex = previousAnswers[currentQuestionIndex];
        if (previousAnswers[currentQuestionIndex] == 0) unit2c1.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 1) unit2c2.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 2) unit2c3.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 3) unit2c4.setChecked(true);
    }

    public int checkAnswer() {
        int score = 0;
        for (int i = 0; i < totalQuestion; i++) {
            if (previousAnswers[i] == QuestionManager.answer2[i]) {
                score++; // if right score++
            }
        }
        return score;
    }
        @Override
    public void onClick(View view) {
            if (view.getId() == R.id.btnNext2){
                if (selectedAnswerIndex == -1){
                    Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                previousAnswers[currentQuestionIndex] = selectedAnswerIndex; // store current question answer
                currentQuestionIndex++;
                loadQuestion();
                setButtonVisibility();
            } else if (view.getId() == R.id.btnBack2) {
                previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
                currentQuestionIndex--;
                loadQuestion();
                setButtonVisibility();
            } else if (view.getId() == R.id.btnSubmit2) {
                if (selectedAnswerIndex == -1){
                    Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                    return;
                }
                previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
                finalScore = checkAnswer();
                Intent intent = new Intent(TestUnit_2.this, activity_sum_score_unit2.class);
                intent.putExtra("score",finalScore);
                intent.putExtra("total",totalQuestion);
                startActivity(intent);
                finish();
            }
    }
}