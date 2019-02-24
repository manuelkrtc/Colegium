package com.example.colegium.entities.articles;


import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractInteractorRepository {

    interface Repository{
        void getApiArticles();
        void saveLocalAllArticles(ArrayList<Article> articles);
        void saveLocalDeletedArticles(ArrayList<Article> articles);

        ArrayList<Article> getLocalAllArticles();
        ArrayList<Article> getLocalDeletedArticles();
    }

    interface Interactor{
        void onSuccessGetApiArticles(ArrayList<Article> articles);
        void onErrorGetApiArticles(String error);
    }
}
