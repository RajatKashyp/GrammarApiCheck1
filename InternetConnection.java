package com.demo.touchstone.grammarapicheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by touchstone on 4/14/2017.
 */

public class InternetConnection {

    public static boolean isDataConnectionAvailable(Context loginActivity){

        ConnectivityManager cm = (ConnectivityManager)loginActivity.getSystemService(loginActivity.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected ;

    }
}
