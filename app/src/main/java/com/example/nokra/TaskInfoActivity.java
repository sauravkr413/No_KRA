package com.example.nokra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TaskInfoActivity extends AppCompatActivity {

    private TextView timeSlotTextView;
    private EditText taskInfoEditText;
    private Button submitButton;
    private List<ReportTask> reporedReportTaskList = new ArrayList<>();
    public int rewardTime =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_task_info);
        // Create and show the dialog
//        showTaskInfoDialog();
//    }

//    private void showTaskInfoDialog() {
        timeSlotTextView = findViewById(R.id.textViewTimeSlot);
        taskInfoEditText = findViewById(R.id.editTextTaskInfo);
        submitButton = findViewById(R.id.buttonSubmit);

//        Bundle intent =getIntent().getExtras();
        LocalDateTime now = LocalDateTime.now();
        int h=now.getHour();;//intent.getInt("hour",5);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timestamp = sdf.format(new Date());
//        Log.d("Math hour","hour" + h);
        timeSlotTextView.setText(timestamp);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the submit action
//                EditText readingInput = dialogView.findViewById(R.id.editTextTaskInfo);
//                String reading = readingInput.getText().toString();
                checkAnswer(timestamp);
                // For now, just log the reading
                // Save to database or process the reading here

//                dialog.dismiss(); // Close the dialog
//                finish(); // Close the activity
            }
        });
        setFinishOnTouchOutside(false);
    }

    private void checkAnswer(String timestamp) {
        String userAnswerString = taskInfoEditText.getText().toString();
        if (!TextUtils.isEmpty(userAnswerString)) {
            ActivityDatabaseHelper dbHelper = new ActivityDatabaseHelper(this);
            dbHelper.addActivity(userAnswerString, timestamp);
            Intent intent = new Intent(TaskInfoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Empty answer, prompt the user to enter a value
            Toast.makeText(TaskInfoActivity.this, "Please enter your activity.", Toast.LENGTH_SHORT).show();
        }
    }

}