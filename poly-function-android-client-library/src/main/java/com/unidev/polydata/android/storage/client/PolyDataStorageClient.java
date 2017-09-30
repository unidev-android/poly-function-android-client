package com.unidev.polydata.android.storage.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.unidev.core.okhttp.OkHttpUtils;
import com.unidev.polydata.PolyClientException;
import com.unidev.polydata.PolyQuery;
import com.unidev.polydata.domain.BasicPoly;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Storage client library
 * Initialize with url http://polydata2-storage.api.universal-development.com/api/v1
 */
public class PolyDataStorageClient {

    public static ObjectMapper POLY_OBJECT_MAPPER = new ObjectMapper() {{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }};

    public static final Integer REQUEST_TIMEOUT = 30 * 1000;

    private final String root;

    private final OkHttpClient httpClient;

    public PolyDataStorageClient(String root) {
        this(root, REQUEST_TIMEOUT);
    }


    public PolyDataStorageClient(String root, int requestTimeout) {
        this.root = root;

        httpClient = OkHttpUtils.fetchUnsafeOkHttpClient()
                .connectTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(requestTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * Fetch storage info page
     * @return
     */
    public PolyDataResponse fetchStorageInfo(String storage) throws PolyClientException {
        HttpUrl indexUrl = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).build();
        PolyDataResponse storageInfo = polyGetRequest(indexUrl, PolyDataResponse.class);

        if (storageInfo == null) {
            throw new PolyClientException();
        }
        return storageInfo;
    }

    /**
     * Fetch storage tags
     * @return
     * @throws PolyClientException
     */
    public PolyDataResponse fetchTags(String storage) throws PolyClientException {
        HttpUrl url = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).addPathSegment("tags").build();

        PolyDataResponse polyResponse = polyGetRequest(url, PolyDataResponse.class);
        if (polyResponse == null) {
            throw new PolyClientException();
        }
        return polyResponse;
    }

    public PolyDataResponse fetchPolyById(String storage, String id) throws PolyClientException {
        HttpUrl url = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).addPathSegment("poly").addPathSegment(id).build();

        PolyDataResponse polyResponse = polyGetRequest(url, PolyDataResponse.class);
        if (polyResponse == null) {
            throw new PolyClientException();
        }
        return polyResponse;
    }
    /**
     * Batch fetch polys by id
     * @param ids Ids collection
     * @return
     */
    public PolyDataResponse fetchPolys(String storage, Collection<String> ids) throws PolyClientException {

        HttpUrl url = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).addPathSegment("poly").build();

        PolyDataResponse polyResponse = postRequest(url, ids, PolyDataResponse.class);
        if (polyResponse == null) {
            throw new PolyClientException();
        }
        return polyResponse;
    }

    /**
     * Query storage for records.
     */
    public PolyDataListResponse queryPolys(String storage, PolyQuery polyQuery) throws PolyClientException {
        HttpUrl url = null;
        if (polyQuery.getTag() == null) {
            url = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).addPathSegment("query").build();
        } else {
            url = HttpUrl.parse(root).newBuilder().addPathSegment("storage").addPathSegment(storage).addPathSegment("tag").addPathSegment(polyQuery.getTag()).build();
        }
        PolyDataListResponse polyResponse = postRequest(url, polyQuery, PolyDataListResponse.class);
        if (polyResponse == null) {
            throw new PolyClientException();
        }
        return polyResponse;
    }

    /**
     * Fetch poly storage from path
     * @param url Link to storage poly
     * @return
     */
    public <T> T polyGetRequest(HttpUrl url, Class<? extends T> responseClass) {
        Request storageRequest = new Request.Builder().url(url).build();
        Response response;
        ResponseBody responseBody = null;
        try {
            response = httpClient.newCall(storageRequest).execute();
            responseBody = response.body();
            return (T) POLY_OBJECT_MAPPER.readValue(responseBody.byteStream(), responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
        return null;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Send generic post request with payload
     * @param url
     * @param payload
     * @param responseClass
     * @param <T>
     * @param <Payload>
     * @return
     */
    public <T, Payload> T postRequest(HttpUrl url, Payload payload, Class<? extends Serializable> responseClass) {
        ResponseBody responseBody = null;
        try {
            RequestBody body = RequestBody.create(JSON, POLY_OBJECT_MAPPER.writeValueAsString(payload));
            Request storageRequest = new Request.Builder().url(url).post(body).build();
            Response response;
            response = httpClient.newCall(storageRequest).execute();
            responseBody = response.body();
            return (T) POLY_OBJECT_MAPPER.readValue(responseBody.byteStream(), responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
        return null;
    }

    /**
     * Execute generic get request
     * @param url
     * @param responseClass
     * @param <T>
     * @param <Payload>
     * @return
     */
    public <T, Payload> T getRequest(HttpUrl url, Class<? extends PolyDataResponse> responseClass) {
        ResponseBody responseBody = null;
        try {
            Request storageRequest = new Request.Builder().url(url).get().build();
            Response response;
            response = httpClient.newCall(storageRequest).execute();
            responseBody = response.body();
            return (T) POLY_OBJECT_MAPPER.readValue(responseBody.byteStream(), responseClass);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (responseBody != null) {
                responseBody.close();
            }
        }
        return null;
    }


    public String getRoot() {
        return root;
    }
}
