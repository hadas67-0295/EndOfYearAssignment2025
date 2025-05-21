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

        GameResultDatabase databaseGameResult = GameResultDatabase.getInstance(MainActivity3.this);
        List<GameResult> gameResults =databaseGameResult.gameResultDao().getAllResults();

        AdapterOfGame gameAdapter = new AdapterOfGame(MainActivity3.this ,new ArrayList<>(gameResults));
        recyclerViewGame.setAdapter(gameAdapter);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                intent.putExtra("username",userName);
                startActivity(intent);
                finish();
            }
        });


    }

}