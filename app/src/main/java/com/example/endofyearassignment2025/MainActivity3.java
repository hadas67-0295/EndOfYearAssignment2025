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
            while (cursor.moveToNext()) {
                String resultUsername = cursor.getString(cursor.getColumnIndex("username"));
                if (resultUsername.equals(userName)) {
                    int score = cursor.getInt(cursor.getColumnIndex("score"));
                    tvUserName.setText("Current username: " + resultUsername);
                    tvScoreShow.setText("Current score: " + score + "%");
                    break; // Only show the first/latest match
                }
            }
        }
        displayLast10Games();

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

    private void displayLast10Games() {
        DatabaseGameResult db = new DatabaseGameResult(this);
        Cursor cursor = db.getAllResults();

        List<GameResult> gameResults = new ArrayList<GameResult>();
        int count = 0;

        if (cursor != null) {
            while (cursor.moveToNext() && count < 10) {
                String resultUsername = cursor.getString(cursor.getColumnIndex("username"));
                int score = cursor.getInt(cursor.getColumnIndex("score"));
                String motivation = cursor.getString(cursor.getColumnIndex("motivation"));

                gameResults.add(new GameResult(resultUsername, score, motivation));
                count++;
            }
            gameAdapter.updateGameResults(gameResults); // Refresh adapter data
        }
    }
}