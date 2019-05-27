package com.ignacio.lectorrss.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignacio.lectorrss.R;
import com.ignacio.lectorrss.Threads.ReaderRss;
import com.ignacio.lectorrss.models.News;
import com.ignacio.lectorrss.view.MainActivity;
import com.ignacio.lectorrss.view.adapters.NewsListAdapter;
import com.ignacio.lectorrss.view.interfaces.AsyncTaskResponse;
import com.ignacio.lectorrss.view.interfaces.DataComunication;
import com.ignacio.lectorrss.view.interfaces.ListSelectedCallListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created By Ignacio Galliano
 */
public class HomeFragment extends Fragment implements ListSelectedCallListener, AsyncTaskResponse {

    private MainActivity activity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<News> arrayList = new ArrayList<>();
    private DataComunication mCallback;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mCallback = (DataComunication) getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        activity.showProgress();
        AsyncTaskResponse asyncTaskResponse;
        asyncTaskResponse = (AsyncTaskResponse) this;
        ReaderRss readerRss = new ReaderRss(getContext(), asyncTaskResponse, recyclerView);
        readerRss.execute();

        return view;
    }



    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            activity = (MainActivity) context;
        }
    }

    @Override
    public void cellPressed(News news) {

        mCallback.setNewsSelected(news);
        activity.switchContent( new DetailNewFragment(), true);

    }

    @Override
    public void responseArrayNews(ArrayList<News> arrayNews) {
        mAdapter = new NewsListAdapter( getContext(), HomeFragment.this, arrayNews);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        activity.dismissProgress();
    }
}
