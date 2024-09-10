package com.example.nokra;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmScheduler.scheduleAlarms(this);

        dbHelper = new ActivityDatabaseHelper(this);

        findViewById(R.id.add_activity_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskInfoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.view_report_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            startActivity(intent);
        });
    }
}
