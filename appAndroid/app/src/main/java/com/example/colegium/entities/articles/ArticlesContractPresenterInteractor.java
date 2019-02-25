package com.example.colegium.entities.articles;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractPresenterInteractor {

    interface Interactor{
        void getArticles();
        void deleteArticle(Article article);
    }

    interface Presenter{
        void returnArticles(ArrayList<Article> articles, String warning);
        void error(String error);
    }
}
