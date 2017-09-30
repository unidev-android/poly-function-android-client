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
package com.unidev.app.testapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.unidev.app.testapp.R;
import com.unidev.app.testapp.core.Core;
import com.unidev.polydata.PolyClientException;
import com.unidev.polydata.PolyQuery;
import com.unidev.polydata.android.storage.client.PolyDataListResponse;
import com.unidev.polydata.domain.BasicPoly;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main, container, false);

        ListView listView = (ListView) view.findViewById(R.id.list);


        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {


                        try {

                            PolyQuery polyQuery = new PolyQuery();
                            polyQuery.setPage(10L);
                            PolyDataListResponse comicsfun = Core.getInstance().polyDataStorageClient.queryPolys("comicsfun", polyQuery);
                            Log.d("comicsfun", comicsfun + "");

//                            String storage = "scenario-plot-generator";
//                            PolyDataResponse fetchStorageInfo = Core.getInstance().polyDataStorageClient.fetchStorageInfo(storage);
//                            Log.d("fetchStorageInfo", fetchStorageInfo.toString());
//
//                            PolyDataResponse poly = Core.getInstance().polyDataStorageClient.fetchPolyById(storage, "DRskJpJPCFt");
//                            Log.d("poly", poly.toString());
//
//
//                            PolyDataResponse fetchPolys = Core.getInstance().polyDataStorageClient.fetchPolys(storage, Arrays.asList("hNhmdocd", "FmeTRRkTef"));
//                            Log.d("fetchPolys", fetchPolys.toString());
                        } catch (PolyClientException e) {
                            e.printStackTrace();
                        }

//                        try {
//                            Map<String, BasicPoly> stringBasicPolyMap = Core.getInstance().polyHateoasClient.fetchPolys(Arrays.asList("ixsbd", "qwe"));
//                            Log.d("stringBasicPolyMap", stringBasicPolyMap + "");
//                        } catch (PolyClientException e) {
//                            e.printStackTrace();
//                        }
                    }
                }.start();
            }
        });

        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
//                        try {
//                            PolyQuery polyQuery = new PolyQuery();
//                            polyQuery.setRandomOrder(true);
//                            polyQuery.setItemPerPage(2L);
//                            polyQuery.setPage(0L);
//                            List<BasicPoly> randomList = Core.getInstance().polyHateoasClient.query(polyQuery);
//                            Log.d("randomList", randomList + "");
//                        } catch (PolyClientException e) {
//                            e.printStackTrace();
//                        }
                    }
                }.start();
            }
        });

        final List<BasicPoly> recordList = new ArrayList<>();


        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return recordList.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                if (view == null) {
                    view = inflater.inflate(R.layout.list_item, null);
                }

                final BasicPoly poly = recordList.get(position);

                TextView item = (TextView) view.findViewById(R.id.item);

                item.setText(poly + "");

                final Button button = (Button) view.findViewById(R.id.favs);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainFragment.this.getActivity());
                        builder.setTitle("Poly record");
                        builder.setMessage(poly + "");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                });

                return view;
            }
        });
        return view;
    }



}