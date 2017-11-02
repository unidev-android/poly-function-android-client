/**
 * Copyright (c) 2015,2016 Denis O <denis@universal-development.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unidev.app.testapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unidev.app.testapp.R;
import com.unidev.polyfunciton.FunctionRequest;
import com.unidev.polyfunciton.FunctionResponse;
import com.unidev.polyfunciton.PolyFunctionClient;


public class MainFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main, container, false);

        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {


                        try {
                            Log.i("functionRequest", "Function request");
                            PolyFunctionClient polyFunctionClient = new PolyFunctionClient();
                            FunctionRequest functionRequest = new FunctionRequest();
                            functionRequest.script("test-hello-world.groovy");

                            FunctionResponse functionResponse = polyFunctionClient.invokeFunction(functionRequest);
                            Log.i("functionResponse", functionResponse + "");

                        } catch (Throwable e) {
                            e.printStackTrace();
                        }


                    }
                }.start();
            }
        });

        return view;
    }


}
