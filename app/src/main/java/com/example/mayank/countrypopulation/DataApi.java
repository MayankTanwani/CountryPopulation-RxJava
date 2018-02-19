package com.example.mayank.countrypopulation;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by mayank on 2/19/18.
 */

public interface DataApi {
    public static final String link = "jsonparsetutorial.txt";
    @GET(link)
    Observable<Population> getPopulation();
}

