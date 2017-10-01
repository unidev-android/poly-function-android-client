package com.unidev.polyfunciton;

import com.unidev.polydata.domain.BasicPoly;

import java.util.Map;

/**
 * Poly function request object.
 */
public class FunctionRequest extends BasicPoly {

    public static final String SCRIPT_KEY = "script";
    public static final String PARAMETERS_KEY = "parameters";

    public FunctionRequest() {}

    public FunctionRequest(Map<String, Object> data) {
        putAll(data);
    }

    public String fetchScript() {
        return fetch(SCRIPT_KEY);
    }

    public FunctionRequest script(String scriptName) {
        put(SCRIPT_KEY, scriptName);
        return this;
    }

    public Map<Object, Object> fetchParameters() {
        return fetch(PARAMETERS_KEY);
    }

    public FunctionRequest parameters(Map<Object, Object> parameters) {
        put(PARAMETERS_KEY, parameters);
        return this;
    }

}
