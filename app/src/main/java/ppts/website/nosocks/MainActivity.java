package ppts.website.nosocks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;

import ppts.website.nosocks.ui.help.HelpFragment;
import ru.katso.livebutton.LiveButton;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static final String KEY_FRAGMENT = "fragment";
    public static SharedPreferences sPref_fragment;
    int key_fragment;

    public static SharedPreferences sPref;
    public static final String NAME = "ThemeColors", KEY = "color";

    @ColorInt
    static public int colortheme;

    int r, b, g;

    public View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("");
        ThemeColors();

        sPref = getPreferences(MODE_PRIVATE);
        sPref_fragment = getPreferences(MODE_PRIVATE);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            int menu_id = menuItem.getItemId();
            switch (menu_id) {
                case R.id.nav_home:
                    Intent SockIntent = new Intent(MainActivity.this, MainActivity.class);
                    MainActivity.this.startActivity(SockIntent);
                    break;
                case R.id.nav_send:
                    LiveButton liveButton = findViewById(R.id.newyear_button);
                    liveButton.setEnabled(false);
                    Fragment fragment = new HelpFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    //смена fragment'ов, а не перезапуск
                    ft.replace(R.id.nav_host_fragment, fragment);
                    ft.commit();

                    break;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        LiveButton liveButton = findViewById(R.id.newyear_button);
        liveButton.setBackgroundColor(colortheme);
        //Toast.makeText(MainActivity.this,stringColor,Toast.LENGTH_SHORT).show();

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_color:
                if ((key_fragment = sPref_fragment.getInt(KEY_FRAGMENT, 0)) == 0) {
                    //НАСТРОЙКА ФОНА ПРИЛОЖЕНИЯ
            /*//запись в booleanColor последнего использованного фона
            booleanColor = sPref.getBoolean(COLORKEY, true);
            if (color == booleanColor) {
                color = !color;
            }
            if (color) {

                //смена фона - красный
                setNewThemeColor(MainActivity.this, 216, 27, 96);
                //перезапись переменной SharedPreferences
                SharedPreferences.Editor ed = sPref.edit();
                ed.putBoolean(COLORKEY, true);
                ed.apply();
            } else {

                //смена фона - темный-серый
                setNewThemeColor(MainActivity.this, 46, 204, 113);
                //перезапись переменной SharedPreferences
                SharedPreferences.Editor ed = sPref.edit();
                ed.putBoolean(COLORKEY, false);
                ed.apply();
            }*/
                    //Create color picker view


                    @SuppressLint("InflateParams") View view = this.getLayoutInflater().inflate(R.layout.holocolorpicker, null);
                    if (view == null) return true;

                    //Config picker
                    final ColorPicker picker = view.findViewById(R.id.picker);
                    SVBar svBar = view.findViewById(R.id.svbar);

                    picker.addSVBar(svBar);
                    picker.setOldCenterColor(picker.getColor());

                    picker.setOnColorChangedListener(intColor -> {
                        String hexColor = Integer.toHexString(intColor).toUpperCase();

                        r = Integer.parseInt(hexColor.substring(2, 4), 16); // 16 for hex
                        g = Integer.parseInt(hexColor.substring(4, 6), 16); // 16 for hex
                        b = Integer.parseInt(hexColor.substring(6, 8), 16); // 16 for hex
                    });


                    //Config dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setView(view);
                    builder.setTitle("Выбери свой цвет");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Отмена", null);
                    builder.setPositiveButton("OK", (dialog, which) -> setNewThemeColor(MainActivity.this, r, g, b));
                    builder.show();

                } else
                    Toast.makeText(this, "Вернись на главный экран для смены фона", Toast.LENGTH_LONG).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ThemeColors() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String stringColor = sharedPreferences.getString(KEY, "D81B60");
        colortheme = Color.parseColor("#" + stringColor);

        if (isLightActionBar()) setTheme(R.style.AppTheme);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            setTheme(getResources().getIdentifier("T_" + stringColor, "style", getOpPackageName()));
        }
    }

    public static void setNewThemeColor(AppCompatActivity activity, int red, int green, int blue) {
        int colorStep = 15;
        red = Math.round(red / colorStep) * colorStep;
        green = Math.round(green / colorStep) * colorStep;
        blue = Math.round(blue / colorStep) * colorStep;

        String stringColor = Integer.toHexString(Color.rgb(red, green, blue)).substring(2);
        SharedPreferences.Editor editor = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY, stringColor);
        editor.apply();
        activity.recreate();
    }

    private boolean isLightActionBar() {// Checking if title text colortheme will be black
        int rgb = (Color.red(colortheme) + Color.green(colortheme) + Color.blue(colortheme)) / 3;
        return rgb > 210;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            openQuitDialog();
        }
    }

    //Вызов меню выхода из приложения
    private void openQuitDialog() {
        //Сообщение о выходе
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Вы уверены?");
        //Кнопка "ДА"
        quitDialog.setPositiveButton("ДА", (dialog, which) -> {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
        //Кнопка "НЕТ"
        quitDialog.setNegativeButton("НЕТ", (dialog, which) -> {
        });

        quitDialog.show();
    }

}
