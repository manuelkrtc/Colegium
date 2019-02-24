package com.example.colegium.entities.articles;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractViewPresenter {

    interface Presenter{
        void onViewCreated();
        void onClickArticle();
        void onRefresh();
        void onDeleteArticle(String id);
    }

    interface View{
        void createList(ArrayList<Article> articles);
        void updateList(ArrayList<Article> articles);
        void showToast(String msg);
        void goneSwipeRefresh();
        void goActivityWebView(String url);
    }
}
