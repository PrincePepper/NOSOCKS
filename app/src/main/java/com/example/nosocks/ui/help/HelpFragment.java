package com.example.nosocks.ui.help;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nosocks.MainActivity;
import com.example.nosocks.R;

import java.util.Objects;

public class HelpFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences.Editor ed = MainActivity.sPref_fragment.edit();
        ed.putInt(MainActivity.KEY_FRAGMENT, 1);
        ed.apply();
        return inflater.inflate(R.layout.fragment_send, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton insta = Objects.requireNonNull(getActivity()).findViewById(R.id.insta);
        ImageView imageView=getActivity().findViewById(R.id.imageView2);



        insta.setOnClickListener(this);

    }

    // анализируем, какая кнопка была нажата. Всего один метод для всех кнопок
    @Override
    public void onClick(View v) {
        Intent browser_insta_nosocks = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/nosocks_gang/"));

        if (v.getId() == R.id.insta) {
            startActivity(browser_insta_nosocks);
        }
    }


}
