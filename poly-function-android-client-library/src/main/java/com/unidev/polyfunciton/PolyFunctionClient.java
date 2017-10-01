package com.unidev.polyfunciton;


import com.unidev.core.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class PolyFunctionClient {

    private String rootUrl;

    public static final Integer REQUEST_TIMEOUT = 30 * 1000;

    private OkHttpClient httpClient;
    private Retrofit retrofit;
    private PolyFunctionService polyFunctionService;

    public PolyFunctionClient() {
        this("http://function.api.universal-development.com/api/v1", REQUEST_TIMEOUT);
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
                .build();
        polyFunctionService = retrofit.create(PolyFunctionService.class);
    }

    public FunctionResponse invokeFunction(FunctionRequest functionRequest) {
        return polyFunctionService.function(functionRequest);
    }

}
