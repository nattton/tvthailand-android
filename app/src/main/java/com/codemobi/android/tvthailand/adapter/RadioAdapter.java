package com.codemobi.android.tvthailand.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codemobi.android.tvthailand.R;
import com.codemobi.android.tvthailand.dao.section.RadioItemDao;
import com.codemobi.android.tvthailand.manager.SectionManager;
import com.codemobi.android.tvthailand.player.RadioPlayerActivity;

/**
 * Created by nattapong on 7/9/15 AD.
 */
public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {
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
            RadioItemDao item = (RadioItemDao)itemView.getTag();

            Uri radioUrl = Uri.parse(item.getUrl());
            Intent intentVideo = new Intent(mContext, RadioPlayerActivity.class);
            intentVideo.putExtra(Intent.EXTRA_TITLE, item.getTitle());

            intentVideo.putExtra(RadioPlayerActivity.EXTRAS_MEDIA_TYPE, "radio");
            intentVideo.putExtra(RadioPlayerActivity.EXTRAS_THUMBNAIL_URL, item.getThumbnail());

            intentVideo.setDataAndType(radioUrl, "video/*");
            mContext.startActivity(intentVideo);
        }
    }

    public RadioAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RadioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_radio_item, parent, false);

        return new ViewHolder(parent.getContext(), v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RadioItemDao item = SectionManager.getInstance().getData().getRadios().get(position);
        holder.mTitle.setText(item.getTitle());
        Glide.with(mContext)
                .load(item.getThumbnail())
                .centerCrop()
                .placeholder(R.drawable.ic_cate_empty)
                .crossFade()
                .into(holder.mThumbnail);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        if (SectionManager.getInstance().getData() == null
                || SectionManager.getInstance().getData().getRadios() == null)
            return 0;
        return SectionManager.getInstance().getData().getRadios().size();
    }
}
