package com.example.endofyearassignment2025;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    TextView tvUserName, tvScoreShow;
    RecyclerView recyclerViewGame;
    AdapterOfGame gameAdapter;
    Button btnBackHome;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvUserName = findViewById(R.id.tvUserName);
        tvScoreShow = findViewById(R.id.tvScoreShow);
        recyclerViewGame = findViewById(R.id.recyclerViewGame);
        btnBackHome = findViewById(R.id.btnBackHome);

        userName = getIntent().getStringExtra("username");

        recyclerViewGame.setLayoutManager(new LinearLayoutManager(this));
        gameAdapter = new AdapterOfGame(this, new ArrayList<GameResult>());
        recyclerViewGame.setAdapter(gameAdapter);

        DatabaseGameResult databaseGameResult= new DatabaseGameResult(this);
        Cursor cursor = databaseGameResult.getAllResults();

        if (cursor != null) {
            // השגת אינדקסים של העמודות
            int usernameIndex = cursor.getColumnIndex("username");
            int scoreIndex = cursor.getColumnIndex("score");

            // בדיקה שהאינדקסים נכונים – אם אחד מהם שווה ל־-1, מדובר בטעות במסד הנתונים
            if (usernameIndex == -1 || scoreIndex == -1) {
                // ניתן להוסיף טיפול מתאים, למשל, להציג הודעת שגיאה או ללוג
                cursor.close();
                return;
            }

            while (cursor.moveToNext()) {
                String resultUsername = cursor.getString(usernameIndex);
                // מומלץ לבדוק גם null
                if (resultUsername != null && resultUsername.equals(userName)) {
                    int score = cursor.getInt(scoreIndex);
                    tvUserName.setText("Current username: " + resultUsername);
                    tvScoreShow.setText("Current score: " + score + "%");
                    break; // מציגים את התוצאה הראשונה שנמצאה
                }
            }
            cursor.close();
        }

        displayUserResults();

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayUserResults() {
        DatabaseGameResult db = new DatabaseGameResult(this);
        Cursor cursor = db.getAllResults();

        List<GameResult> userGameResults = new ArrayList<>();

        if (cursor != null) {
            // קבלת אינדקסים של העמודות, ובדיקה שהם חוקיים.
            int usernameIndex = cursor.getColumnIndex("username");
            int scoreIndex = cursor.getColumnIndex("score");
            int motivationIndex = cursor.getColumnIndex("motivation");

            if (usernameIndex == -1 || scoreIndex == -1 || motivationIndex == -1) {
                // טיפול במקרה של בעיה - לדוגמה, לוג או הודעה מתאימה.
                cursor.close();
                return;
            }

            while (cursor.moveToNext()) {
                String resultUsername = cursor.getString(usernameIndex);
                if (resultUsername != null && resultUsername.equals(userName)) {
                    int score = cursor.getInt(scoreIndex);
                    String motivation = cursor.getString(motivationIndex);

                    userGameResults.add(new GameResult(resultUsername, score, motivation));
                }
            }
            cursor.close();
        }
        // עדכון ה־Adapter עם הרשימה החדשה
        gameAdapter.updateGameResults(userGameResults);
    }
}