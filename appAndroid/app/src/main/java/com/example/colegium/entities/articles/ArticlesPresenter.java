package com.example.colegium.entities.articles;

import android.app.Activity;

import com.example.colegium.model.Article;
import com.example.colegium.tools.ToolsApi;

import java.util.ArrayList;

public class ArticlesPresenter implements ArticlesContractViewPresenter.Presenter, ArticlesContractPresenterInteractor.Presenter{

    Activity ctx;
    ArticlesContractViewPresenter.View view;
    ArticlesContractPresenterInteractor.Interactor interactor;

    public ArticlesPresenter(Activity ctx, ArticlesContractViewPresenter.View view) {
        this.ctx = ctx;
        this.view = view;
        this.interactor = new ArticlesInteractor(this.ctx, this);
    }

    //------------------------------------------- View ---------------------------------------------

    @Override
    public void onViewCreated() {
        interactor.getArticles();
    }

    @Override
    public void onClickArticle(Article article) {
        if(!ToolsApi.isConnected(ctx)){
            view.showToast("Check your internet connection.");
            return;
        }
        view.notifyClickArticle(article);
    }

    @Override
    public void onRefresh() {
        interactor.getArticles();
    }

    @Override
    public void onDeleteArticle(Article article) {
        interactor.deleteArticle(article);
    }

    //-------------------------------------- Interactor --------------------------------------------
    @Override
    public void returnArticles(ArrayList<Article> articles, String warning) {
        view.goneSwipeRefresh();

        if (view.wasCreatedList()) {
            view.updateList(articles);
        } else {
            view.createList(articles);
        }

        if (warning != null){
            view.showToast(warning);
        }
    }

    @Override
    public void error(String error) {
        view.goneSwipeRefresh();
        view.showToast(error);
    }

}
