package com.unidev.polyfunciton;


import com.unidev.core.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PolyFunctionClient {

    private String rootUrl;

    public static final Integer REQUEST_TIMEOUT = 30 * 1000;

    private OkHttpClient httpClient;
    private Retrofit retrofit;
    private PolyFunctionService polyFunctionService;

    public PolyFunctionClient() {
        this("http://function.api.universal-development.com/api/v1/", REQUEST_TIMEOUT);
    }

    public PolyFunctionClient(String rootUrl, int requestTimeout) {
        this.rootUrl = rootUrl;
        httpClient = OkHttpUtils.fetchUnsafeOkHttpClient()
                .connectTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        polyFunctionService = retrofit.create(PolyFunctionService.class);
    }

    public FunctionResponse invokeFunction(FunctionRequest functionRequest) throws PolyFunctionException {
        Call<FunctionResponse> call = polyFunctionService.function(functionRequest);
        try {
            Response<FunctionResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new PolyFunctionException("Not successful function invocation");
            }
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
            throw new PolyFunctionException(e);
        }
    }

}
