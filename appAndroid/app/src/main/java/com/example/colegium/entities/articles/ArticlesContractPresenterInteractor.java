package com.example.colegium.entities.articles;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractPresenterInteractor {

    interface Interactor{
        void getArticles();
    }

    interface Presenter{
        void onSuccessGetArticles(ArrayList<Article> articles, String warning);
        void onErrorGetArticles(String error);
    }
}
