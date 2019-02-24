package com.example.colegium.entities.articles;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colegium.R;
import com.example.colegium.model.Article;

import java.util.ArrayList;

public class ArticlesView extends Fragment {

    public static final String NAME = "ArticlesView";

    MyAdapter myAdapter;
    RecyclerView recycler;


    public static ArticlesView newInstance() {
        ArticlesView fragment = new ArticlesView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        recycler = view.findViewById(R.id.recycler);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("fddfdf","Su madre","yo","weereee",1223));
        articles.add(new Article("fddfdf dsd sdd","Su maddsd re","ysdo","weere  ee",1223));
        articles.add(new Article("fddfdf s","Su madre","yo","weereee",1223));
        articles.add(new Article("fddfdfsdsd","Su madre","yo","weereee",1223));
        articles.add(new Article("fddfdfw2222","Su madre","yo","weereee",1223));

        createFridgeList(articles);
    }

    public void createFridgeList(ArrayList<Article> articles) {
        recycler.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, articles);
        recycler.setAdapter(myAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Context _ctx;
        private ArticlesView _fragment;
        private ArrayList<Article> _articles;

        public MyAdapter(ArticlesView fragment, ArrayList<Article> articles) {
            _fragment = fragment;
            _articles = articles;
            _ctx = fragment.getActivity();
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Article article = _articles.get(position);
            holder.tvTitle.setText(article.getTitle());
            holder.tvAuthorTime.setText(String.format("%s - %s", article.getAuthor(), article.getCreated_at_i()));
        }

        @Override
        public int getItemCount() {
            return _articles.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public ViewGroup parent;
            public TextView tvTitle;
            public TextView tvAuthorTime;

            public ViewHolder(View v) {
                super(v);
                parent = v.findViewById(R.id.parent);
                tvTitle = v.findViewById(R.id.tvTitle);
                tvAuthorTime = v.findViewById(R.id.tvAuthorTime);
            }
        }
    }

}
