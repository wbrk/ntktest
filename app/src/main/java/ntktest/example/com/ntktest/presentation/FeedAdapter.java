package ntktest.example.com.ntktest.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssItem;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    @NonNull
    private List<RssItem> data;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.date)
        public TextView date;

        @BindView(R.id.source)
        public TextView source;

        public ViewHolder(@NonNull View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }

    public FeedAdapter(@NonNull List<RssItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RssItem item = data.get(position);

        holder.title.setText(item.getTitle());
        holder.date.setText(item.getPubDate());
        holder.source.setText("Meduza");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
