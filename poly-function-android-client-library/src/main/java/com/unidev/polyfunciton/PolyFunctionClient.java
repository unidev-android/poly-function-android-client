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

    public static final String DEFAULT_FUNCTION_DOMAIN = "http://function.api.universal-development.com/api/v1/";
    public static final Integer REQUEST_TIMEOUT = 30 * 1000;

    private PolyFunctionService polyFunctionService;

    public PolyFunctionClient() {
        this(DEFAULT_FUNCTION_DOMAIN, REQUEST_TIMEOUT);
    }

    public PolyFunctionClient(String rootUrl, int requestTimeout) {
        OkHttpClient httpClient = OkHttpUtils.fetchUnsafeOkHttpClient()
                .connectTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
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
