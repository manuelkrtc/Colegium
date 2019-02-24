package com.example.colegium.entities.articles;

import android.app.Activity;

import com.example.colegium.model.Article;

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
    public void onClickArticle() {

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onDeleteArticle(String id) {

    }

    //-------------------------------------- Interactor --------------------------------------------
    @Override
    public void onSuccessGetArticles(ArrayList<Article> articles) {
        view.createList(articles);
    }

    @Override
    public void onErrorGetArticles(String error) {
        view.showError(error);
    }

}
