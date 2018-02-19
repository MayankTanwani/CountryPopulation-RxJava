package com.example.mayank.countrypopulation;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mayank on 2/19/18.
 */

public class DataClient {
    private static final String BASE_URL = "http://www.androidbegin.com/tutorial/";
    public static DataClient instance;
    private  DataApi dataApi;
    private DataClient()
    {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        dataApi = retrofit.create(DataApi.class);

    }
    public static DataClient getInstance() {
        if (instance == null) {
            instance = new DataClient();
        }
        return instance;
    }

    public Observable<Population> getPopulation() {
        return dataApi.getPopulation();
    }
}
