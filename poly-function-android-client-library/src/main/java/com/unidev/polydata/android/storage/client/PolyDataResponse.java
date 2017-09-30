package com.unidev.polydata.android.storage.client;

import com.unidev.polydata.AbstractResponse;
import com.unidev.polydata.domain.BasicPoly;


public class PolyDataResponse extends AbstractResponse {

    private BasicPoly payload;

    public BasicPoly getPayload() {
        return payload;
    }

    public void setPayload(BasicPoly payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "PolyDataResponse{" +
                "payload=" + payload +
                '}';
    }
}
