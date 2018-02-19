package com.example.mayank.countrypopulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;


import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DisplayActivity extends AppCompatActivity implements CountryRecyclerView.ListImageClick {

    private Subscription subscription;
    public static final String TAG = DisplayActivity.class.getSimpleName();
    CountryRecyclerView adapter;
    public ArrayList<Country> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new CountryRecyclerView(countries,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getPopulationData();
    }
    public void getPopulationData()
    {
        DataClient.getInstance()
                .getPopulation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Population>() {

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Population population) {
                        countries = population.getWorldpopulation();
                        Log.d(TAG,countries.size() + " ");
                        adapter.swapArray(population.getWorldpopulation());
                    }
                });
    }

    @Override
    public void onListImageClick(String imageRes) {
        Intent i = new Intent(DisplayActivity.this, ImageActivity.class);
        i.putExtra("image-res",imageRes);
        startActivity(i);
    }
}
