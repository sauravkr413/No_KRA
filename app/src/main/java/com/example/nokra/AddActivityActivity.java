package com.example.nokra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivityActivity extends AppCompatActivity {

    private EditText activityNameEditText;
    private Button saveButton;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        activityNameEditText = findViewById(R.id.activity_name_edittext);
        saveButton = findViewById(R.id.save_button);

        calendar = Calendar.getInstance();

        saveButton.setOnClickListener(v -> saveActivity());
    }

    private void saveActivity() {
        String name = activityNameEditText.getText().toString().trim();
        if (name.isEmpty()) {
            activityNameEditText.setError("Activity name is required");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timestamp = sdf.format(new Date());

        ActivityDatabaseHelper dbHelper = new ActivityDatabaseHelper(this);
        dbHelper.addActivity(name, timestamp);

        Intent intent = new Intent(AddActivityActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
