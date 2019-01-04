package com.example.rssreader.presentation.sourcelist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rssreader.R;
import com.example.rssreader.data.RssSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        public TextView name;

        @BindView(R.id.url)
        public TextView url;

        public ViewHolder(@NonNull View item) {
            super(item);
            ButterKnife.bind(this, item);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener == null || getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            clickListener.onClick(getAdapterPosition());
        }
    }

    @NonNull
    private List<RssSource> data = new ArrayList<>();

    @Nullable
    private OnItemClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_source, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RssSource source = data.get(position);
        holder.name.setText(source.getName());
        holder.url.setText(source.getUrl());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    public List<RssSource> getData() {
        return data;
    }

    public void setData(@NonNull List<RssSource> data) {
        this.data = data;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        clickListener = listener;
    }
}
