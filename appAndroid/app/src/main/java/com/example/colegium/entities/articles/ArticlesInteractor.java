package com.example.colegium.entities.articles;

import android.app.Activity;

import com.example.colegium.model.Article;

import java.util.ArrayList;
import java.util.HashSet;

public class ArticlesInteractor implements ArticlesContractPresenterInteractor.Interactor, ArticlesContractInteractorRepository.Interactor {

    Activity ctx;
    ArticlesContractInteractorRepository.Repository repository;
    ArticlesContractPresenterInteractor.Presenter presenter;

    HashSet<String> _userList = new HashSet<>();

    public ArticlesInteractor(Activity ctx, ArticlesContractPresenterInteractor.Presenter presenter) {
        this.ctx = ctx;
        this.presenter = presenter;
        this.repository = new ArticlesRepository(this.ctx, this);
    }

    //--------------------------------------- Presenter --------------------------------------------
    @Override
    public void getArticles() {
        repository.getApiArticles();
    }

    //----------------------------------------- Repository -----------------------------------------
    @Override
    public void onSuccessGetApiArticles(ArrayList<Article> articles) {
        presenter.onSuccessGetArticles(articles);
    }

    @Override
    public void onErrorGetApiArticles(String error) {
        presenter.onErrorGetArticles(error);
    }
}
