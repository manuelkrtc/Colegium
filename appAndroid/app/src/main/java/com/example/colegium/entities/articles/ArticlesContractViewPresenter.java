package com.example.colegium.entities.articles;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractViewPresenter {

    interface Presenter{
        void onViewCreated();
        void onClickArticle();
        void onUpdate();
        void onDeleteArticle(String id);
    }

    interface View{
        void createList(ArrayList<Article> articles);
        void updateList(ArrayList<Article> articles);
        void showError(String error);
        void goActivityWebView(String url);
    }
}
