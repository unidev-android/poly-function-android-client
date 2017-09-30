/**
 * Copyright (c) 2015,2016 Denis O <denis@universal-development.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unidev.app.testapp.core;

import com.unidev.core.di.AppContext;
import com.unidev.polydata.android.storage.client.PolyDataStorageClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application custom backend logic
 */
public class Core {

    public static final String INSTANCE_NAME = "core";

    public static Core getInstance() {
        return AppContext.getInstance(INSTANCE_NAME, Core.class);
    }

    public ExecutorService executorService = Executors.newFixedThreadPool(1);

    public PolyDataStorageClient polyDataStorageClient;

//    public PolyDataStorageClient polyHateoasClient;
//
//    List<BasicPoly> tags;
//    List<BasicPoly> recordList;

    public void load() {

        polyDataStorageClient = new PolyDataStorageClient("http://polydata2-storage.api.universal-development.com/api/v1");
//        polyHateoasClient = new PolyDataStorageClient("http://polydata-storage.api.universal-development.com", "striptease");
//        try {
//            polyHateoasClient.fetchIndex();
//            tags = polyHateoasClient.fetchTags();
//
//            recordList = polyHateoasClient.query(new PolyQuery());
//
//            Log.d("tags", tags + "");
//            Log.d("recordList", recordList + "");
//        } catch (PolyClientException e) {
//            e.printStackTrace();
//        }

        // polyHateoasClient.fetchCurrentPage();
    }

//    public List<BasicPoly> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<BasicPoly> tags) {
//        this.tags = tags;
//    }
//
//    public List<BasicPoly> getRecordList() {
//        return recordList;
//    }
//
//    public void setRecordList(List<BasicPoly> recordList) {
//        this.recordList = recordList;
//    }
//
    //
//    public FlatFileStorage currentPage() {
//        return polyHateoasClient.fetchCurrentPage();
//    }
//
//    public FlatFileStorage fetchPolyInfo(BasicPoly basicPoly) {
//        return polyHateoasClient.fetchStorage(basicPoly.link());
//    }

    /*public FlatFileURLStorage flatFileURLStorage() {
        return flatFileURLStorage;
    }*/

}
