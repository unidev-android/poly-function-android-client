package com.unidev.polyfunciton;

import com.unidev.polydata.domain.BasicPoly;

import java.util.Map;

/**
 * Function invocation response.
 */
public class FunctionResponse extends BasicPoly {

    public FunctionResponse() {
    }

    public FunctionResponse(Map<String, Object> data) {
        putAll(data);
    }
}
