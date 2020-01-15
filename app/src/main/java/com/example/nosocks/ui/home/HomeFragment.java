package com.example.nosocks.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.nosocks.MainActivity;
import com.example.nosocks.R;
import com.example.nosocks.ui.home.quiz.QuizFragment;

import java.util.Objects;

import ru.katso.livebutton.LiveButton;

import static com.example.nosocks.ui.home.quiz.QuizFragment.aaa;

import static com.example.nosocks.MainActivity.colortheme;
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        aaa = "";

        SharedPreferences.Editor ed = MainActivity.sPref_fragment.edit();
        ed.putInt(MainActivity.KEY_FRAGMENT, 0);
        ed.apply();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LiveButton newyear = Objects.requireNonNull(getActivity()).findViewById(R.id.newyear_button);

            newyear.setBackgroundColor(colortheme);


        newyear.setOnClickListener(v -> {
            Fragment fragment = new QuizFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.nav_host_fragment, fragment);
            ft.commit();
            newyear.setEnabled(false);
        });

    }
}