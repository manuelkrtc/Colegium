package com.example.colegium.entities.webpage;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.colegium.R;
import com.example.colegium.model.Article;

public class WebPageView extends Fragment{

    public static final String NAME = "ArticlesView";

    private static final String  KEY_ARTICLE  = "ARTICLE";

    Article article;
    WebView webView;
    ProgressDialog pd;

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
        pd = ProgressDialog.show(this.getActivity(), "", "Loading...",true);
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });
    }
}
