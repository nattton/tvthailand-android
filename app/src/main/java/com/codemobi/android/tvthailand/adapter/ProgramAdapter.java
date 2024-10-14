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
import com.codemobi.android.tvthailand.datasource.Program;
import com.codemobi.android.tvthailand.datasource.Programs;

/**
 * Created by nattapong on 7/9/15 AD.
 */
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    private Context mContext;
    private Programs mPrograms;

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

    public ProgramAdapter(Context context, Programs mPrograms) {
        mContext = context;
        this.mPrograms = mPrograms;
    }

    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_program_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Program item = mPrograms.get(position);
        holder.mTitle.setText(item.getTitle());
        holder.mDescription.setText(item.getDescription());
        Glide.with(mContext)
                .load(item.getThumbnail())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mPrograms.size();
    }

}
