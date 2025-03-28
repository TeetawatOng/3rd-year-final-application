package com.example.final_application;

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

public class TestUnit_1 extends AppCompatActivity implements View.OnClickListener {
    Button btnNext1, btnSubmit1, btnBack1;
    TextView questionView1;
    RadioButton unit1c1, unit1c2, unit1c3, unit1c4;
    RadioGroup radioGroup1;

    int totalQuestion = QuestionManager.question1.length;
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1; // default not select answer
    int finalScore = 0;
    int[] previousAnswers; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_unit1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        previousAnswers = new int[totalQuestion]; // adding size to previousAnswer
        for (int i = 0; i < totalQuestion; i++) {
            previousAnswers[i] = -1;
        }

        questionView1 = findViewById(R.id.questionView1);
        radioGroup1 = findViewById(R.id.radioGroup1);
        unit1c1 = findViewById(R.id.unit1c1);
        unit1c2 = findViewById(R.id.unit1c2);
        unit1c3 = findViewById(R.id.unit1c3);
        unit1c4 = findViewById(R.id.unit1c4);
        btnNext1 = findViewById(R.id.btnNext1);
        btnSubmit1 = findViewById(R.id.btnSubmit1);
        btnBack1 = findViewById(R.id.btnBack1);
        btnNext1.setOnClickListener(this);
        btnSubmit1.setOnClickListener(this);
        btnBack1.setOnClickListener(this);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.unit1c1) {
                    selectedAnswerIndex = 0;
                } else if (checkedId == R.id.unit1c2) {
                    selectedAnswerIndex = 1;
                } else if (checkedId == R.id.unit1c3) {
                    selectedAnswerIndex = 2;
                } else if (checkedId == R.id.unit1c4) {
                    selectedAnswerIndex = 3;
                }
            }
        });

        loadQuestion();
    }

    private void setButtonVisibility() {
        if (currentQuestionIndex >= 1) {
            btnBack1.setVisibility(View.VISIBLE);
        } else {
            btnBack1.setVisibility(View.INVISIBLE);
        }

        if (currentQuestionIndex < totalQuestion - 1) {
            // If 0-8 < 9 (not on the last question),
            btnNext1.setVisibility(View.VISIBLE);
            btnSubmit1.setVisibility(View.GONE);
        } else if (currentQuestionIndex == totalQuestion - 1) {
            // If 9 == 9 (on the last question)
            btnNext1.setVisibility(View.GONE);
            btnSubmit1.setVisibility(View.VISIBLE);
        }
    }

    public void loadQuestion(){
        questionView1.setText(QuestionManager.question1[currentQuestionIndex]);
        unit1c1.setText(QuestionManager.choices1[currentQuestionIndex][0]);
        unit1c2.setText(QuestionManager.choices1[currentQuestionIndex][1]);
        unit1c3.setText(QuestionManager.choices1[currentQuestionIndex][2]);
        unit1c4.setText(QuestionManager.choices1[currentQuestionIndex][3]);

        if (previousAnswers[currentQuestionIndex] != -1) {
            loadPrevAnswer();  // if already answer then load PrevAnswer
        } else {
            radioGroup1.clearCheck();
            selectedAnswerIndex = -1;
        }
    }

    public void loadPrevAnswer() {
        selectedAnswerIndex = previousAnswers[currentQuestionIndex];
        if (previousAnswers[currentQuestionIndex] == 0) unit1c1.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 1) unit1c2.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 2) unit1c3.setChecked(true);
        else if (previousAnswers[currentQuestionIndex] == 3) unit1c4.setChecked(true);
    }

    public int checkAnswer() {
        int score = 0;
        for (int i = 0; i < totalQuestion; i++) {
            if (previousAnswers[i] == QuestionManager.answer1[i]) {
                score++; // if right score++
            }
        }
        return score;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNext1){
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex; // store current question answer
            currentQuestionIndex++;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnBack1) {
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            currentQuestionIndex--;
            loadQuestion();
            setButtonVisibility();
        } else if (view.getId() == R.id.btnSubmit1) {
            String strScore;
            if (selectedAnswerIndex == -1){
                Toast.makeText(this,"กรุณาเลือกคำตอบ", Toast.LENGTH_SHORT).show();
                return;
            }
            previousAnswers[currentQuestionIndex] = selectedAnswerIndex;
            finalScore = checkAnswer();
            strScore = "คุณได้คะแนน: " + finalScore + "/" + totalQuestion;
            Toast.makeText(this,strScore,Toast.LENGTH_SHORT).show();
        }
    }
}