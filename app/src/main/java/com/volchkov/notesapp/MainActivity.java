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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;




public class MainActivity extends AppCompatActivity implements com.volchkov.notesapp.Adapter.ItemClickListener {
    Adapter adapter;




        @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    String date = sdf.format(Calendar.getInstance().getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);

        try {
            Thread.sleep(4000); //Приостанавливает поток на 4 секунды
        } catch (Exception e){}


        // data to populate the RecyclerView with
        ArrayList<String> Titles = new ArrayList<>();
        Titles.add("LongTitleLongTitleLongTitleLongTitleLongTitleLongTitle");
        Titles.add("Title2");
        Titles.add("Title3");
        Titles.add("Title4");
        Titles.add("дата "+date);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, Titles);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();




            if (hasConnection(this)) {
                Toast.makeText(this, "Active networks OK ", Toast.LENGTH_LONG).show();
            } else Toast.makeText(this, "No active networks... ", Toast.LENGTH_LONG).show();
        }

    private boolean hasConnection(MainActivity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNW = cm.getActiveNetworkInfo();
        if (activeNW != null && activeNW.isConnected()) {
            return true;
        }
        return false;}
}
