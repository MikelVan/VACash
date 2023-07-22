package com.vacash.android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.vacash.android.adapters.GameAdapter;
import com.vacash.android.adapters.PurchaseHistoryAdapter;
import com.vacash.android.models.Game;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MobileTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileTab extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MobileTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MobileTab.
     */
    // TODO: Rename and change types and number of parameters
    public static MobileTab newInstance(String param1, String param2) {
        MobileTab fragment = new MobileTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mobile_tab, container, false);

        HorizontalScrollView scrollView = view.findViewById(R.id.scrollableView);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);

        ArrayList<Game> games= new ArrayList<>();
        games.add(new Game(R.drawable.genshin_impact, "Genshin Impact", "Hoyoverse"));
        games.add(new Game(R.drawable.honkai_star_rail, "Honkai Star Rail", "Hoyoverse"));
        games.add(new Game(R.drawable.mobile_legends, "Mobile Legends", "Moonton"));
        games.add(new Game(R.drawable.growtopia, "Growtopia", "Ubisoft"));
        games.add(new Game(R.drawable.stumble_guys, "Stumble Guys", "Scopely"));

        RecyclerView gameRecycleView = view.findViewById(R.id.gameRecycleView);
        gameRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        gameRecycleView.setAdapter(new GameAdapter(games));
        // Inflate the layout for this fragment
        return view;
    }
}