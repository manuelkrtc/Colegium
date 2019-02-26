package com.example.colegium.entities.articles;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public interface ArticlesContractViewPresenter {

    interface Presenter{
        void onViewCreated();
        void onClickArticle(Article article);
        void onRefresh();
        void onDeleteArticle(Article article);
    }

    interface View{
        void createList(ArrayList<Article> articles);
        void updateList(ArrayList<Article> articles);
        boolean wasCreatedList();
        void showToast(String msg);
        void goneSwipeRefresh();
        void notifyClickArticle(Article article);
    }
}
