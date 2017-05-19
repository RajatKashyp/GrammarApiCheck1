package com.demo.touchstone.grammarapicheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by touchstone on 4/3/2017.
 */

public class HttpCall {
    public static GrammarResponse grammarResponse ;
    public static ProgressDialog progressDialog ;

    static String[] errors ;

    public static GrammarResponse homeWorkResponseCall(final MainActivity mainActivity,final String passage) {

        /*if (InternetConnection.isDataConnectionAvailable(mainActivity) == true) {*/
        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setMessage("Pull Successful");
        progressDialog.show();


            try {
                String url = "https://api.textgears.com/check.php?text=" + passage + "&key=Op5epGXLCpAdfF2U";

                final OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).build();
                final Request request = new Request.Builder().url(url).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {

                        if (progressDialog.isShowing() && progressDialog != null)
                            progressDialog.dismiss();
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mainActivity, "Server is not Responding, Try again after some time", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        GrammarResponse responseObject;
                        if (!response.isSuccessful()) {
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mainActivity, " Some internal error, please try again after some time", Toast.LENGTH_SHORT).show();
                                }
                            });
                            throw new IOException("Server Problem : " + response);
                        } else {
                            String serverResponse = response.body().string();
                            Log.e("response", serverResponse);
                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            Type listType = new TypeToken<List<GrammarResponse>>() {
                            }.getType();
                            // List<HomeWorkResponse> homeWorkResponseList = (List<HomeWorkResponse>) gson.fromJson(serverResponse, HomeWorkResponse.class);

                            grammarResponse = gson.fromJson(serverResponse, GrammarResponse.class);


                            if ((grammarResponse != null) || (grammarResponse.isResult())) {
                                ArrayList<String> errorList = new ArrayList<>();
                                ArrayList<String> correctionList = new ArrayList<>();
                                for (int i = 0; i < grammarResponse.getErrors().size(); i++) {
                                    //   errors[i] = grammarResponse.getErrors().get(i).getBad();

                                    errorList.add(grammarResponse.getErrors().get(i).getBad());

                                    int size_of_better = grammarResponse.getErrors().get(i).getBetter().size();
                                    for (int j = 0; j < size_of_better; j++) {
                                        correctionList.add(grammarResponse.getErrors().get(i).getBetter().get(j));
                                    }
                                }

                                Intent in = new Intent(mainActivity, PassageCorrectionActivity.class);
                                in.putExtra("passage", passage);
                                mainActivity.startActivity(in);


                            } else {
                                //make visible empty layout when there is no message for the user
                                Toast.makeText(mainActivity, "No Correction required", Toast.LENGTH_SHORT).show();
                            }
                            if (progressDialog.isShowing() && progressDialog != null)
                                progressDialog.dismiss();


                            // responseArray = enums.toArray(new GrammarResponse.ErrorsBean);
                            Log.e("array_response", grammarResponse.toString());

                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                //    Toast.makeText(forgotPasswordActivity, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();

            }

            return grammarResponse ;

    }


}
