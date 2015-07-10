package com.codemobi.android.tvthailand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codemobi.android.tvthailand.R;
import com.codemobi.android.tvthailand.dao.show.ShowItemDao;

import java.util.List;

/**
 * Created by nattapong on 7/9/15 AD.
 */
public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private Context mContext;
    private List<ShowItemDao> mShowItemDaoList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mDescription;
        ImageView mThumbnail;

        ViewHolder (View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

    public ShowAdapter(Context context, List<ShowItemDao> showItemDaoList) {
        mContext = context;
        mShowItemDaoList = showItemDaoList;
    }

    @Override
    public ShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_show_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShowItemDao showItem = mShowItemDaoList.get(position);
        holder.mTitle.setText(showItem.getTitle());
        holder.mDescription.setText(showItem.getDescription());
        Glide.with(mContext)
                .load(showItem.getThumbnailURL())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mShowItemDaoList.size();
    }

}
