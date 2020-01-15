package com.example.nosocks.ui.home.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.nosocks.Algoritm;
import com.example.nosocks.MainActivity;
import com.example.nosocks.R;

import java.util.Objects;

import ru.katso.livebutton.LiveButton;

import static com.example.nosocks.MainActivity.colortheme;
import static com.example.nosocks.ui.home.quiz.QuizFragment.aaa;


public class Result extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences.Editor ed = MainActivity.sPref_fragment.edit();
        ed.putInt(MainActivity.KEY_FRAGMENT, 1);
        ed.apply();

        return inflater.inflate(R.layout.fragment_result, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        LiveButton resBtn = getActivity().findViewById(R.id.resBtn);

        resBtn.setOnClickListener(v -> {
            Intent StartIntent = new Intent(Result.this.getContext(), MainActivity.class);
            Result.this.startActivity(StartIntent);
            Result.this.onDestroy();
        });

            resBtn.setBackgroundColor(colortheme);


        ImageView imageView= Objects.requireNonNull(getView()).findViewById(R.id.imageView_res);
        Algoritm algoritm= new Algoritm();
        //Toast.makeText(getContext(), aaa, Toast.LENGTH_SHORT).show();
        String b = algoritm.algoritm(aaa);


        imageView.setImageDrawable(combination (b));

        new Handler().postDelayed(() -> {
            resBtn.setVisibility(View.VISIBLE);
            resBtn.setEnabled(true);

        }, 2000);

        new Handler().postDelayed(this::openDialog, 7000);
    }

    //Вызов меню выхода из приложения
    private void openDialog() {
        //Сообщение о выходе
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.alertdialog, null));

        builder.setPositiveButton("Перейти", (dialog, id) -> {
            Intent browser_insta_nosocks = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nosocks.su/"));
            startActivity(browser_insta_nosocks);
        });

        builder.setNegativeButton("НЕТ", (dialog, which) -> {
        });
        builder.show();
    }

    Drawable combination(String b){

        switch (Integer.valueOf(b)){
            case 1:return getResources().getDrawable(R.drawable.sock_1, null);
            case 2:return getResources().getDrawable(R.drawable.sock_2, null);
            case 3:return getResources().getDrawable(R.drawable.sock_3, null);
            case 4:return getResources().getDrawable(R.drawable.sock_4, null);
            case 5:return getResources().getDrawable(R.drawable.sock_5, null);
            case 6:return getResources().getDrawable(R.drawable.sock_6, null);
            case 7:return getResources().getDrawable(R.drawable.sock_7, null);
            case 8:return getResources().getDrawable(R.drawable.sock_8, null);
            case 9:return getResources().getDrawable(R.drawable.sock_9, null);
            case 10:return getResources().getDrawable(R.drawable.sock_10,null);
            case 11:return getResources().getDrawable(R.drawable.sock_11,null);
            case 12:return getResources().getDrawable(R.drawable.sock_12,null);
            case 13:return getResources().getDrawable(R.drawable.sock_13,null);
            case 14:return getResources().getDrawable(R.drawable.sock_14,null);
            case 15:return getResources().getDrawable(R.drawable.sock_15,null);
            case 16:return getResources().getDrawable(R.drawable.sock_16,null);
            case 17:return getResources().getDrawable(R.drawable.sock_17,null);
            case 18:return getResources().getDrawable(R.drawable.sock_18,null);
            case 19:return getResources().getDrawable(R.drawable.sock_19,null);
            case 20:return getResources().getDrawable(R.drawable.sock_20,null);
            case 21:return getResources().getDrawable(R.drawable.sock_21,null);
            case 22:return getResources().getDrawable(R.drawable.sock_22,null);
            case 23:return getResources().getDrawable(R.drawable.sock_23,null);
            case 24:return getResources().getDrawable(R.drawable.sock_24,null);
            case 25:return getResources().getDrawable(R.drawable.sock_25,null);
            case 26:return getResources().getDrawable(R.drawable.sock_26,null);
            case 27:return getResources().getDrawable(R.drawable.sock_27,null);
            case 28:return getResources().getDrawable(R.drawable.sock_28,null);
            case 29:return getResources().getDrawable(R.drawable.sock_29,null);
            case 30:return getResources().getDrawable(R.drawable.sock_30,null);
            case 31:return getResources().getDrawable(R.drawable.sock_31,null);
            case 32:return getResources().getDrawable(R.drawable.sock_32,null);
            case 33:return getResources().getDrawable(R.drawable.sock_33,null);
            case 34:return getResources().getDrawable(R.drawable.sock_34,null);
            case 35:return getResources().getDrawable(R.drawable.sock_35,null);
            case 36:return getResources().getDrawable(R.drawable.sock_36,null);
            case 37:return getResources().getDrawable(R.drawable.sock_37,null);
            case 38:return getResources().getDrawable(R.drawable.sock_38,null);
            case 39:return getResources().getDrawable(R.drawable.sock_39,null);
            case 40:return getResources().getDrawable(R.drawable.sock_40,null);
            case 41:return getResources().getDrawable(R.drawable.sock_41,null);
            case 42:return getResources().getDrawable(R.drawable.sock_42,null);
            case 43:return getResources().getDrawable(R.drawable.sock_43,null);
            case 44:return getResources().getDrawable(R.drawable.sock_44,null);
            case 45:return getResources().getDrawable(R.drawable.sock_45,null);
            case 46:return getResources().getDrawable(R.drawable.sock_46,null);
            case 47:return getResources().getDrawable(R.drawable.sock_47,null);
            case 48:return getResources().getDrawable(R.drawable.sock_48,null);
            default:return  getResources().getDrawable(R.drawable.nosocks,null);
        }


    }
}


