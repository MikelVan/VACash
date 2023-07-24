package com.vacash.android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.vacash.android.adapters.GameAdapter;
import com.vacash.android.interfaces.GameInterface;
import com.vacash.android.models.Game;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class GameTab extends Fragment implements GameInterface {
    private Integer tab_id;
    private String tab_title;

    ArrayList<Game> games= new ArrayList<>();

    public static GameTab newInstance(Integer tab_id, String tab_title) {
        GameTab fragment = new GameTab();
        Bundle args = new Bundle();
        args.putInt("tab_id", tab_id);
        args.putString("tab_title", tab_title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tab_id = getArguments().getInt("tab_id");
            tab_title = getArguments().getString("tab_title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_platform_tab, container, false);

        HorizontalScrollView scrollView = view.findViewById(R.id.scrollableView);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        switch (tab_id){
            case 1:
                games.add(new Game(R.drawable.genshin_impact, "Genshin Impact", "Hoyoverse"));
                games.add(new Game(R.drawable.honkai_star_rail, "Honkai Star Rail", "Hoyoverse"));
                games.add(new Game(R.drawable.mobile_legends, "Mobile Legends", "Moonton"));
                games.add(new Game(R.drawable.growtopia, "Growtopia", "Ubisoft"));
                games.add(new Game(R.drawable.stumble_guys, "Stumble Guys", "Scopely"));
                break;
            case 2:
                games.add(new Game(R.drawable.honkai_star_rail, "Honkai Star Rail", "Hoyoverse"));
                games.add(new Game(R.drawable.genshin_impact, "Genshin Impact", "Hoyoverse"));
                games.add(new Game(R.drawable.growtopia, "Growtopia", "Ubisoft"));
                games.add(new Game(R.drawable.hogwarts_legacy, "Hogwarts Legacy", "Portkey Games"));
                games.add(new Game(R.drawable.call_of_duty, "Call of Duty", "Infinity Ward"));
                break;
            case 3:
                games.add(new Game(R.drawable.animal_crossing, "Animal Crossing", "Nintendo"));
                games.add(new Game(R.drawable.fortnite, "Fortnite", "Farga Corporation"));
                games.add(new Game(R.drawable.genshin_impact, "Genshin Impact", "Hoyoverse"));
                games.add(new Game(R.drawable.hogwarts_legacy, "Hogwarts Legacy", "Portkey Games"));
                games.add(new Game(R.drawable.call_of_duty, "Call of Duty", "Infinity Ward"));
                break;
        }

        RecyclerView gameRecycleView = view.findViewById(R.id.gameRecycleView);
        gameRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        gameRecycleView.setAdapter(new GameAdapter(games, this));
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "Opening " + games.get(position).getGameTitle(), Toast.LENGTH_SHORT);
    }

}