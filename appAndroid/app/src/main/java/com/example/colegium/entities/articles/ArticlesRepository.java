package com.example.colegium.entities.articles;

import android.app.Activity;
import android.app.Application;
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
    Application app;
    ArticlesContractInteractorRepository.Interactor interactor;

    public ArticlesRepository(Activity ctx, ArticlesContractInteractorRepository.Interactor interactor) {
        this.ctx = ctx;
        this.app = this.ctx.getApplication();
        this.interactor = interactor;
    }

    //-------------------------------------- Interactor --------------------------------------------
    @Override
    public void getApiArticles() {
        if (!ToolsApi.isConnected(ctx)){
            interactor.onErrorGetApiArticles("No tiene conexion a internet");
            return;
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ToolsApi.getUrlgetArticlesForAndroid(), null,
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

    }

    @Override
    public void saveLocalDeletedArticles(ArrayList<Article> articles) {

    }

    @Override
    public ArrayList<Article> getLocalAllArticles() {
        return null;
    }

    @Override
    public ArrayList<Article> getLocalDeletedArticles() {
        return null;
    }

}
