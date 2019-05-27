package com.ignacio.lectorrss.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ignacio.lectorrss.R;
import com.ignacio.lectorrss.view.MainActivity;
import com.ignacio.lectorrss.view.interfaces.DataComunication;
import butterknife.ButterKnife;

/**
 * Created By Ignacio Galliano
 */
public class DetailNewFragment extends Fragment {

    private MainActivity activity;
    private ImageView image;
    private TextView textDescriptionDetail;
    private TextView textDateDetail;
    private DataComunication mCallback;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        mCallback = (DataComunication) getContext();
        image = (ImageView) view.findViewById(R.id.imageDetailNews);
        textDescriptionDetail = (TextView) view.findViewById(R.id.textDescriptionDetail);
        textDateDetail = (TextView) view.findViewById(R.id.textDateDetail);


        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.noimage)
                .dontAnimate();

        Glide.with(this)
                .load(mCallback.getNewsSelected().getEnclosure())
                .apply(requestOptions)
                .into(image);

        textDateDetail.setText(mCallback.getNewsSelected().getPubDate());
        textDescriptionDetail.setText(mCallback.getNewsSelected().getTitle());

        return view;
    }



    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
            activity = (MainActivity) context;
        }
    }
}
