package ppts.website.nosocks.ui.home.quiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ppts.website.nosocks.MainActivity;
import ppts.website.nosocks.R;

import java.util.Objects;

import ru.katso.livebutton.LiveButton;

import static ppts.website.nosocks.MainActivity.colortheme;

public class QuizFragment extends Fragment {
    private LiveButton firstBtn;
    private LiveButton secondBtn;
    private TextView numQuiz;
    private TextView quiz;
    private int a = 2;
    public static String aaa = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences.Editor ed = MainActivity.sPref_fragment.edit();
        ed.putInt(MainActivity.KEY_FRAGMENT, 1);
        ed.apply();

        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstBtn = Objects.requireNonNull(getActivity()).findViewById(R.id.firstBtn);
        secondBtn = Objects.requireNonNull(getActivity()).findViewById(R.id.secondBtn);

        numQuiz = getActivity().findViewById(R.id.numQuiz);
        quiz = getActivity().findViewById(R.id.quiz);


            firstBtn.setBackgroundColor(colortheme);
            secondBtn.setBackgroundColor(colortheme);



        firstBtn.setOnClickListener(v -> {
            switchec();
            aaa += "1";
            a++;

        });
        secondBtn.setOnClickListener(v -> {
            switchec();
            aaa += "0";
            a++;
        });
    }

    private void second_quiz() {

        numQuiz.setText("Второй вопрос:");
        quiz.setText("Как хочешь провести вечер?");
        firstBtn.setText("лампово");
        secondBtn.setText("улётно");
    }

    private void third_quiz() {

        numQuiz.setText("Третий вопрос:");
        quiz.setText("Тематические носки?");
        firstBtn.setText("конечно");
        secondBtn.setText("без этого");
    }

    private void four_quiz() {

        numQuiz.setText("Четвертый вопрос:");
        quiz.setText("Ты хочешь чтобы носки были");
        firstBtn.setText("светлые");
        secondBtn.setText("темные");
    }

    void switchec() {
        switch (a) {
            case 2:
                second_quiz();
                break;
            case 3:
                third_quiz();
                break;
            case 4:
                four_quiz();
                firstBtn.setOnClickListener(v -> {
                    aaa += "1";
                    a++;

                    Fragment fragment = new Result();
                    FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                    FalseAll();
                    onDestroy();

                });
                secondBtn.setOnClickListener(v -> {
                    aaa += "0";
                    a++;
                    Fragment fragment = new Result();
                    FragmentManager fragmentManager = getFragmentManager();
                    assert fragmentManager != null;
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();
                    FalseAll();
                    onDestroy();
                });

                break;
        }
    }
    void FalseAll(){
        firstBtn.setEnabled(false);
        secondBtn.setEnabled(false);
        numQuiz.setEnabled(false);
        quiz.setEnabled(false);
    }
}
