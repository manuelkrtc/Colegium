package com.example.colegium.tools;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ToolsApi {

    //---------------------------------- URL BASE --------------------------------------------------
    static final String urlBase = "https://hn.algolia.com/";
    static final String urlApi = urlBase + "/api/v1/";
    static final String urlgetArticlesForIos = urlApi + "search_by_date?query=ios";
    static final String urlgetArticlesForAndroid = urlApi + "search_by_date?query=android";

    //--------------------------------    GETTERS    -----------------------------------------------
    public static String getUrlgetArticlesForIos() {
        return urlgetArticlesForIos;
    }

    public static String getUrlgetArticlesForAndroid() {
        return urlgetArticlesForAndroid;
    }

    //------------------------------      Methods     ----------------------------------------------
    public static boolean isConnected(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivity != null ){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info != null){
                if (info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

}



