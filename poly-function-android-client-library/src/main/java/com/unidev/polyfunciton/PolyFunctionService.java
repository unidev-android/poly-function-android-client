package com.unidev.polyfunciton;


import retrofit2.http.POST;

/**
 * Function invocation service
 */
public interface PolyFunctionService {

    @POST("function")
    public FunctionResponse function(FunctionRequest functionRequest);
}
