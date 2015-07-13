package com.codemobi.android.tvthailand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codemobi.android.tvthailand.R;
import com.codemobi.android.tvthailand.dao.section.ChannelItemDao;
import com.codemobi.android.tvthailand.manager.SectionManager;

/**
 * Created by nattapong on 7/9/15 AD.
 */
public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context mContext;
        TextView mTitle;
        ImageView mThumbnail;

        ViewHolder (Context context, View itemView) {
            super(itemView);
            mContext = context;
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ChannelItemDao item = (ChannelItemDao)itemView.getTag();
            Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    public ChannelAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ChannelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_channel_item, parent, false);

        return new ViewHolder(parent.getContext(), v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChannelItemDao item = SectionManager.getInstance().getData().getChannels().get(position);
        holder.mTitle.setText(item.getTitle());
        Glide.with(mContext)
                .load(item.getThumbnail())
                .centerCrop()
                .placeholder(R.drawable.ic_tvthailand_120)
                .crossFade()
                .into(holder.mThumbnail);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        if (SectionManager.getInstance().getData() == null
                || SectionManager.getInstance().getData().getChannels() == null)
            return 0;
        return SectionManager.getInstance().getData().getChannels().size();
    }
}
