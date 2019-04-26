package com.jeugene.retrex;



import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("svc/search/v2/articlesearch.json?&api-key=api_key")
    Observable<Example> getnewlist();

}
