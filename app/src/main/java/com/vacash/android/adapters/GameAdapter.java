package com.vacash.android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vacash.android.R;
import com.vacash.android.interfaces.GameInterface;
import com.vacash.android.models.Game;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private final GameInterface gameInterface;
    List<Game> games;

    public GameAdapter(List<Game> games, GameInterface gameInterface) {
        this.games = games;
        this.gameInterface = gameInterface;
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.game_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        holder.gameLogo.setImageResource(games.get(position).getGameLogo());
        holder.gameTitleView.setText(games.get(position).getGameTitle());
        holder.gameDeveloperView.setText(games.get(position).getGameDeveloper());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView gameLogo;
        TextView gameTitleView, gameDeveloperView;

        public ViewHolder(@NonNull View gameView) {
            super(gameView);

            gameLogo = gameView.findViewById(R.id.gameLogo);
            gameTitleView = gameView.findViewById(R.id.gameTitle);
            gameDeveloperView = gameView.findViewById(R.id.gameDeveloper);

            gameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(gameInterface != null){
                        int pos = getBindingAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            gameInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }

    }

}
