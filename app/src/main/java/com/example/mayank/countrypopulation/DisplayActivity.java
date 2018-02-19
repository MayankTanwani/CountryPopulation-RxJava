package com.example.mayank.countrypopulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DisplayActivity extends AppCompatActivity {

    private Subscription subscription;
    public static final String TAG = DisplayActivity.class.getSimpleName();
    CountryRecyclerView adapter;
    public ArrayList<Country> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new CountryRecyclerView(countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getPopulationData();
    }
    public void getPopulationData()
    {
        subscription = DataClient.getInstance()
                .getPopulation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Population>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError");
                    }

                    @Override
                    public void onNext(Population population) {
                        countries = population.getWorldpopulation();
                        Log.d(TAG,countries.size() + " ");
                        adapter.swapArray(population.getWorldpopulation());
                    }
                });
    }
}
