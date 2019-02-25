package com.example.colegium;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.colegium.entities.articles.ArticlesView;
import com.example.colegium.entities.webpage.WebPageView;
import com.example.colegium.model.Article;

public class MainActivity extends AppCompatActivity implements ArticlesView.Listener {

    ViewGroup fragment;

    //Fragment
    int refFragment = R.id.fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = findViewById(refFragment);

        goFragmentArticles();
    }

    //--------------------------------------------- goFragments ------------------------------------

    private void goFragmentArticles() {

        ArticlesView fragment = ArticlesView.newInstance();

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(refFragment, fragment);
        fragmentTransaction.addToBackStack(fragment.NAME);
        fragmentTransaction.commit();
    }

    private void goFragmentWebPage(Article article) {

        WebPageView fragment = WebPageView.newInstance(article);

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(refFragment, fragment);
        fragmentTransaction.addToBackStack(fragment.NAME);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickArticle(Article article) {
        goFragmentWebPage(article);
    }
}
