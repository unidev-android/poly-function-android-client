package com.unidev.polydata.android.storage.client;

import com.unidev.polydata.AbstractResponse;
import com.unidev.polydata.domain.BasicPoly;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class PolyDataListResponse extends AbstractResponse {

    private List<BasicPoly> payload;

    public List<BasicPoly> getPayload() {
        return payload;
    }

    public void setPayload(List<BasicPoly> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PolyDataListResponse{" +
                "payload=" + payload +
                '}';
    }
}
