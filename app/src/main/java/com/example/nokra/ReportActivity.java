package com.example.nokra;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private ActivityDatabaseHelper dbHelper;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        dbHelper = new ActivityDatabaseHelper(this);
        tableLayout = findViewById(R.id.table_layout);

        displayReport();
    }

    private void displayReport() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM activities ORDER BY timestamp DESC", null);

        // Adding column headings
        TableRow headerRow = new TableRow(this);
        TextView timestampHeader = new TextView(this);
        timestampHeader.setText("Timestamp");
        timestampHeader.setPadding(16, 16, 16, 16);
        timestampHeader.setTextSize(18);
        headerRow.addView(timestampHeader);

        TextView activityHeader = new TextView(this);
        activityHeader.setText("Activity");
        activityHeader.setPadding(16, 16, 16, 16);
        activityHeader.setTextSize(18);
        headerRow.addView(activityHeader);

        tableLayout.addView(headerRow);

        // Adding data rows
        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);

            TextView timestampTextView = new TextView(this);
            timestampTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("timestamp")));
            timestampTextView.setPadding(16, 16, 16, 16);
            row.addView(timestampTextView);

            TextView activityTextView = new TextView(this);
            activityTextView.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            activityTextView.setPadding(16, 16, 16, 16);
            row.addView(activityTextView);

            tableLayout.addView(row);
        }

        cursor.close();
    }
}
