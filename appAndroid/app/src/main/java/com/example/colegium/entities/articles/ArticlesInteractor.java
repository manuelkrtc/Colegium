package com.example.colegium.entities.articles;

import android.app.Activity;
import android.util.Log;

import com.example.colegium.model.Article;

import java.util.ArrayList;

public class ArticlesInteractor implements ArticlesContractPresenterInteractor.Interactor, ArticlesContractInteractorRepository.Interactor {

    Activity ctx;
    ArticlesContractInteractorRepository.Repository repository;
    ArticlesContractPresenterInteractor.Presenter presenter;

    ArrayList<Article> articles;
    ArrayList<Article> deletedArticles;

    public ArticlesInteractor(Activity ctx, ArticlesContractPresenterInteractor.Presenter presenter) {
        this.ctx = ctx;
        this.presenter = presenter;
        this.repository = new ArticlesRepository(this.ctx, this);

        articles = repository.getLocalAllArticles();
        deletedArticles = repository.getLocalDeletedArticles();
    }

    //--------------------------------------- Presenter --------------------------------------------
    @Override
    public void getArticles() {
        repository.getApiArticles();
    }

    @Override
    public void deleteArticle(Article article) {
        deletedArticles.add(article);
        repository.saveLocalDeletedArticles(deletedArticles);
        returnArticles(articles, null);
    }

    //----------------------------------------- Repository -----------------------------------------
    @Override
    public void onSuccessGetApiArticles(ArrayList<Article> articles) {
        this.articles = articles;
        repository.saveLocalAllArticles(articles);
        returnArticles(articles,null);
    }

    @Override
    public void onErrorGetApiArticles(String error) {
        presenter.error(error);
        returnArticles(repository.getLocalAllArticles(),error);
    }

    //------------------------------------------ Methods -------------------------------------------
    void returnArticles(ArrayList<Article> articles, String warning){
        for (Article deletedArticle: deletedArticles){
            Log.i("mmp","1");
            Article articleToDelete = null;
            for (Article article: articles){
                if (article.getId().equals(deletedArticle.getId())){
                    articleToDelete = article;
                }    
            }
            articles.remove(articleToDelete);
        }
        presenter.returnArticles(articles,warning);
    }
}
