package com.ignacio.lectorrss.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ignacio.lectorrss.R;
import com.ignacio.lectorrss.models.News;
import com.ignacio.lectorrss.view.interfaces.ListSelectedCallListener;

import java.util.ArrayList;

/**
 * Created By Ignacio Galliano
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {


    private Context context;
    private TextView textTitleCell;
    private TextView textDescriptionCell;
    private TextView textDateCell;
    private ImageView imageCell;
    private LinearLayout cellList;
    private ListSelectedCallListener lscl;

    private ArrayList<News> array;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout cellView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            cellView = v;
        }
    }


    public NewsListAdapter(Context context, ListSelectedCallListener lscl, ArrayList<News> array) {
        this.context = context;
        this.array = array;
        this.lscl = lscl;
    }


    @Override
    public NewsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_list, parent, false);


        textTitleCell = (TextView) v.findViewById(R.id.textTitleCell);
        textDescriptionCell = (TextView) v.findViewById(R.id.textDescriptionCell);
        textDateCell = (TextView) v.findViewById(R.id.textDateCell);
        imageCell = (ImageView) v.findViewById(R.id.imageCell);
        cellList = (LinearLayout) v.findViewById(R.id.cellList);
        context = v.getContext();


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {

            News news = array.get(position);

            textTitleCell.setText(news.getTitle());
            //textDescriptionCell.setText(news.getTitle());
            textDateCell.setText(news.getPubDate());

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.noimage)
                    .dontAnimate();

            Glide.with(context)
                    .load(news.getEnclosure())
                    .apply(requestOptions)
                    .into(imageCell);

            cellList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lscl.cellPressed(array.get(position));
                }
            });



    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {

        if(array != null){
            return array.size();
        }else{
            return 0;
        }

    }
}
