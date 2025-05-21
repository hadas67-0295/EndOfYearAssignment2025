package com.example.endofyearassignment2025;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GameResult.class}, version = 1, exportSchema = false)
public abstract class GameResultDatabase extends RoomDatabase {

    private static GameResultDatabase instance;
    public abstract GameResultDAO gameResultDao();

    public static synchronized GameResultDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GameResultDatabase.class,
                            "game_results.db"
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
