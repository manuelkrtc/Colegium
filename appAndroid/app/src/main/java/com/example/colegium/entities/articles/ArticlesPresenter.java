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
    public void onSuccessGetArticles(ArrayList<Article> articles, String warning) {
        view.createList(articles);
        if (warning != null){
            view.showToast(warning);
        }
    }

    @Override
    public void onErrorGetArticles(String error) {
        view.showToast(error);
    }

}
