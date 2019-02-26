package com.example.colegium.entities.articles;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.colegium.model.Article;
import com.example.colegium.tools.ToolsApi;
import com.example.colegium.volley.MySingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticlesRepository implements ArticlesContractInteractorRepository.Repository {

    Activity ctx;
    ArticlesContractInteractorRepository.Interactor interactor;

    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorSharedPreferences;

    private String keyPreferenceAllArticles= "ALL_ARTICLES";
    private String keyPreferenceIdDeletedArticles= "ID_DELETED_ARTICLES";



    public ArticlesRepository(Activity ctx, ArticlesContractInteractorRepository.Interactor interactor) {
        this.ctx = ctx;
        this.interactor = interactor;

        gson = new Gson();
        sharedPreferences = ctx.getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        editorSharedPreferences = sharedPreferences.edit();
    }

    //-------------------------------------- Interactor --------------------------------------------
    @Override
    public void getApiArticles() {
        if (!ToolsApi.isConnected(ctx)){
            interactor.onErrorGetApiArticles("Check your internet connection.");
            return;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ToolsApi.getUrlgetArticlesForIos(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response", response.toString());
                        try {
                            Gson gson = new Gson();
                            ArrayList<Article> articles = gson.fromJson(response.getJSONArray("hits").toString(), new TypeToken<List<Article>>() {}.getType());
                            interactor.onSuccessGetApiArticles(articles);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                interactor.onErrorGetApiArticles(error.getMessage());
            }
        });

        MySingleton.getInsant(ctx).addTorequestQueue(jsonObjectRequest);
    }

    @Override
    public void saveLocalAllArticles(ArrayList<Article> articles) {
        String json = gson.toJson(articles);
        editorSharedPreferences.putString(keyPreferenceAllArticles, json);
        editorSharedPreferences.commit();
    }

    @Override
    public void saveLocalDeletedArticles(ArrayList<Article> articles) {
        String json = gson.toJson(articles);
        editorSharedPreferences.putString(keyPreferenceIdDeletedArticles, json);
        editorSharedPreferences.commit();
    }

    @Override
    public ArrayList<Article> getLocalAllArticles() {
        gson = new Gson();
        String articlesString = sharedPreferences.getString(keyPreferenceAllArticles , "[]");
        return gson.fromJson(articlesString, new TypeToken<List<Article>>() {}.getType());
    }

    @Override
    public ArrayList<Article> getLocalDeletedArticles() {
        gson = new Gson();
        String articlesString = sharedPreferences.getString(keyPreferenceIdDeletedArticles , "[]");
        return gson.fromJson(articlesString, new TypeToken<List<Article>>() {}.getType());
    }

}
