package com.example.colegium.entities.articles;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colegium.R;
import com.example.colegium.model.Article;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ArticlesView extends Fragment implements ArticlesContractViewPresenter.View, SwipeRefreshLayout.OnRefreshListener{

    public static final String NAME = "ArticlesView";

    PrettyTime prettyTime;

    RecyclerView recycler;
    SwipeRefreshLayout swiperefresh;

    Listener mListener;
    MyAdapter myAdapter;
    Fragment thisFragment = this;

    ArticlesContractViewPresenter.Presenter presenter;

    public static ArticlesView newInstance() {
        ArticlesView fragment = new ArticlesView();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mListener == null) createListener(thisFragment.getActivity());
        presenter = new ArticlesPresenter(thisFragment.getActivity(), this);
        prettyTime = new PrettyTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        recycler = view.findViewById(R.id.recycler);
        swiperefresh = view.findViewById(R.id.swiperefresh);

        swiperefresh.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onViewCreated();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        createListener(context);
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    //--------------------------------------- Presenter --------------------------------------------
    @Override
    public void createList(ArrayList<Article> articles) {
        recycler.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, articles);
        recycler.setAdapter(myAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void updateList(ArrayList<Article> articles) {
        myAdapter.setList(articles);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean wasCreatedList() {
        return myAdapter != null;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(thisFragment.getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goneSwipeRefresh() {
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void notifyClickArticle(Article article) {
        mListener.onClickArticle(article);
    }

    private void createListener(Context context){
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface Listener {
        void onClickArticle(Article article);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Context ctx;
        private ArticlesView fragment;
        private ArrayList<Article> articles;

        public MyAdapter(ArticlesView fragment, ArrayList<Article> articles) {
            this.fragment = fragment;
            this.articles = articles;
            ctx = fragment.getActivity();
        }

        public void setList(ArrayList<Article> articles) {
            this.articles = articles;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Article article = articles.get(position);
            holder.tvTitle.setText(article.getTitle());
            holder.tvAuthorTime.setText(String.format("%s - %s", article.getAuthor(), prettyTime.format(new Date(article.getCreated_at_i()))));
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onDeleteArticle(article);
                }
            });

            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.onClickArticle(article);
                }
            });

        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public ViewGroup parent;
            public TextView tvTitle;
            public TextView tvDelete;
            public TextView tvAuthorTime;

            public ViewHolder(View v) {
                super(v);
                parent = v.findViewById(R.id.parent);
                tvTitle = v.findViewById(R.id.tvTitle);
                tvDelete = v.findViewById(R.id.tvDelete);
                tvAuthorTime = v.findViewById(R.id.tvAuthorTime);
            }
        }
    }

}
