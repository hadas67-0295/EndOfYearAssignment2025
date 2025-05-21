package com.example.endofyearassignment2025;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;
@Dao
public interface GameResultDAO {
        @Insert
        void insert(GameResult gameResult);
        @Update
        void update(GameResult gameResult);
        @Delete
        void delete(GameResult gameResult);

        @Query("SELECT * FROM game_results")
        List<GameResult> getAllResults();
    }

