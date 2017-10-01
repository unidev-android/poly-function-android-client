package com.unidev.polyfunciton;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Function invocation service
 */
public interface PolyFunctionService {

    @POST("function")
    public Call<FunctionResponse> function(@Body FunctionRequest functionRequest);
}
