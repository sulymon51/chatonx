package com.chatonx.chat.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.activities.StatusPreviewActivity;
import com.chatonx.chat.constant.Constant;
import com.chatonx.chat.models.StatusModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<StatusModel> mStatusModel;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public StatusAdapter(Activity mActivity, List<StatusModel> dataset) {
        super();
        this.mStatusModel = dataset;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.ad_view, parent, false);
            return new HeaderViewHolder(v);
        } else if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.status_item, parent, false);
            return new ContentViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            if (Constant.SHOW_ADS) {
                headerHolder.mAdView.setVisibility(View.VISIBLE);
                AdRequest adRequest = new AdRequest.Builder().build();
                headerHolder.mAdView.loadAd(adRequest);
            } else {
                headerHolder.mAdView.setVisibility(View.GONE);
            }
        }else if (holder instanceof ContentViewHolder) {
            StatusModel item = getItem(position - 1);
            ContentViewHolder contentHolder = (ContentViewHolder) holder;

            contentHolder.picture.setImageResource(item.getPicture());
            contentHolder.username.setText(item.getUsername());
            contentHolder.date.setText(item.getDate());
        }
    }

    private StatusModel getItem (int position) {
        return mStatusModel.get (position);
    }

    @Override
    public int getItemViewType (int position) {
        if(isPositionHeader (position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader (int position) {
        return position == 0;
    }

    @Override
    public int getItemCount () {
        return mStatusModel.size ();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        AdView mAdView;

        HeaderViewHolder(View itemView) {
            super(itemView);
            mAdView = (AdView) itemView.findViewById(R.id.adView);
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView picture;
        TextView username, date;
        LinearLayout select;

        ContentViewHolder(View v) {
            super(v);
            picture = (ImageView) v.findViewById(R.id.picture);
            username = (TextView) v.findViewById(R.id.username);
            date = (TextView) v.findViewById(R.id.date);
            select = (LinearLayout) v.findViewById(R.id.select);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StatusModel item = mStatusModel.get(this.getLayoutPosition() - 1);
            switch (v.getId()) {
                default:
                    Intent mIntent = new Intent(mActivity, StatusPreviewActivity.class);
                    mActivity.startActivity(mIntent);
                break;
            }
        }
    }
}