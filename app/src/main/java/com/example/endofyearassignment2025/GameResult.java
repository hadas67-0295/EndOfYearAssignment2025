package com.example.endofyearassignment2025;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "game_results")
    public class GameResult {
        @PrimaryKey(autoGenerate = true)
        private int id;

        private String username;
        private int percent;
        private String motivation;

        public GameResult(String username, int percent, String motivation) {
            this.username = username;
            this.percent = percent;
            this.motivation = motivation;
        }

        public int getId() {
            return this.id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getPercent() {
            return this.percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getMotivation() {
            return this.motivation;
        }

        public void setMotivation(String motivation) {
            this.motivation = motivation;
        }
    }
