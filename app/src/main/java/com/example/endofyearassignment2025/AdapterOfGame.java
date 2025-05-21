package com.example.endofyearassignment2025;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterOfGame extends RecyclerView.Adapter<AdapterOfGame.GameViewHolder> {
    private Context context;
    private List<GameResult> gameResults;

    public AdapterOfGame(Context context, List<GameResult> gameResults) {
        this.context = context;
        this.gameResults = gameResults;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        GameResult gameResult = gameResults.get(position);
        holder.usernameTextView.setText("Username: " + gameResult.getUsername());
        holder.scoreTextView.setText(gameResult.getScore() + "%");
        holder.motivationTextView.setText(gameResult.getMotivationSentence());
    }
    @Override
    public int getItemCount() {
        return gameResults.size();
    }
    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView scoreTextView;
        TextView motivationTextView;

        public GameViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.tvUserName);
            scoreTextView = itemView.findViewById(R.id.tvScore);
            motivationTextView = itemView.findViewById(R.id.tvMotivation);
        }
    }
    public void updateGameResults(List<GameResult> newGameResults) {
        this.gameResults = newGameResults;
        notifyDataSetChanged();
    }
}
