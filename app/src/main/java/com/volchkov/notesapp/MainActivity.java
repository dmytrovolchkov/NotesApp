package com.volchkov.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


        //Главный поток
public class MainActivity extends AppCompatActivity implements com.volchkov.notesapp.Adapter.ItemClickListener {
    //Переменные
    Adapter adapter;
    FloatingActionButton f;
    TextView des;


                    //Связь с интернетом
            private boolean hasConnection(MainActivity mainActivity) {
                ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNW = cm.getActiveNetworkInfo();
                if (activeNW != null && activeNW.isConnected()) {
                    return true;
                }
                return false;}

        //Формат времени
        @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String date = sdf.format(Calendar.getInstance().getTime());


        //Выполняемая часть
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Проверка интернета
        if (hasConnection(this)) {
            Toast.makeText(this, "Active networks OK ", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "No active networks... ", Toast.LENGTH_LONG).show();

        //Прогресс Бар
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        //Задержка выполнения
        try {
            Thread.sleep(0*1000); // на 0 секунды
        } catch (Exception e){}


        // data to populate the RecyclerView with
        ArrayList<String> Titles = new ArrayList<>();
        ArrayList<String> Descs = new ArrayList<>();
        ArrayList<String> Dates = new ArrayList<>();
        Titles.add("LongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitleLongTitle");
        Descs.add("Line " + String.valueOf(Titles.size()));
        Dates.add("time " + date);


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, Titles, Descs, Dates);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);




        // найдем View-элементы
        des = (TextView) findViewById(R.id.description);
        f = (FloatingActionButton) findViewById(R.id.fab);


// создаем обработчик нажатия на кнопку
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Меняем текст в TextView (Titles)

                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String date = sdf.format(Calendar.getInstance().getTime());

                Titles.add(0,"LongTi");
                Descs.add(0, "Line " + String.valueOf(Titles.size()));
                Dates.add(0,"time " + date);
                recyclerView.smoothScrollToPosition(0);
                // set up the RecyclerView
                RecyclerView recyclerView = findViewById(R.id.rv);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new Adapter(MainActivity.this, Titles, Descs, Dates);
                adapter.setClickListener(MainActivity.this);
                recyclerView.setAdapter(adapter);

                //Всплывающее снизу окошко
                Snackbar snackbar = Snackbar.make(v, "New message created", Snackbar.LENGTH_LONG);
                snackbar.show();



            }
        };
// присвоим обработчик кнопке (btnOk)
        f.setOnClickListener(oclBtnOk);



        //Прогресс бар - невидимый ???
        //progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
        //Нажатие на каждую запись
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }



}