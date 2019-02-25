package com.example.colegium.entities.webpage;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.colegium.R;
import com.example.colegium.model.Article;

public class WebPageView extends Fragment {

    public static final String NAME = "ArticlesView";

    private static final String  KEY_ARTICLE  = "ARTICLE";

    WebView webView;
    Article article;

    public static WebPageView newInstance(Article article) {
        WebPageView fragment = new WebPageView();
        Bundle arguments = new Bundle();
        arguments.putParcelable(KEY_ARTICLE, article);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        article = getArguments().getParcelable(KEY_ARTICLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_page, container, false);
        webView = view.findViewById(R.id.webView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView.loadUrl(article.getUrl());
    }
}
