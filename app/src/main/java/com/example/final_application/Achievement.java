package com.example.final_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Achievement extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView textAch1, textAch2, textAch3, textAch4, textAch5;
    ProgressBar progressAch1, progressAch2, progressAch3, progressAch4, progressAch5;
    MyDatabaseHelper myDatabaseHelper;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_achievement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myDatabaseHelper = new MyDatabaseHelper(this);
        textAch1 = findViewById(R.id.textAch1);
        textAch2 = findViewById(R.id.textAch2);
        textAch3 = findViewById(R.id.textAch3);
        textAch4 = findViewById(R.id.textAch4);
        textAch5 = findViewById(R.id.textAch5);
        progressAch1 = findViewById(R.id.progressAch1);
        progressAch2 = findViewById(R.id.progressAch2);
        progressAch3 = findViewById(R.id.progressAch3);
        progressAch4 = findViewById(R.id.progressAch4);
        progressAch5 = findViewById(R.id.progressAch5);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.action_lesson){
                            Intent goLesson = new Intent(Achievement.this, lesson.class);
                            startActivity(goLesson);
                        }
                        else if (item.getItemId() == R.id.action_member){
                            Intent goMember = new Intent(Achievement.this, member.class);
                            startActivity(goMember);
                        }
                        return true;
                    }
                }
        );
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, null);
        bottomNavigationView.setSelectedItemId(R.id.action_achievement);

        updateProgress(username);
    }

    private void updateProgress(String username) {
        String[] units = {"unit1", "unit2", "unit3", "unit4", "unit5"};
        ProgressBar[] progressBars = {progressAch1, progressAch2, progressAch3, progressAch4, progressAch5};
        TextView[] textViews = {textAch1, textAch2, textAch3, textAch4, textAch5};
        // setProgressBar
        for (int i = 0; i < units.length; i++) {
            int score = myDatabaseHelper.getUnitScore(username, units[i]);

            if (score == -1) {
                progressBars[i].setProgress(0);
            } else {
                progressBars[i].setProgress(score);
            }
        }
        // Set text
        for (int i = 0; i < textViews.length; i++) {
            int score = myDatabaseHelper.getUnitScore(username, units[i]);

            if (score == 10){
                textViews[i].setText("ผู้พิชิตบทที่ " + (i + 1));
            }
        }
    }
}