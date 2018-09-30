package ntktest.example.com.ntktest.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssItem;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.date)
        public TextView date;

        @BindView(R.id.source)
        public TextView source;

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
    private List<RssItem> data = new ArrayList<>();

    @Nullable
    private OnItemClickListener clickListener;

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

    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        clickListener = listener;
    }

    @NonNull
    public List<RssItem> getData() {
        return data;
    }

    public void setData(@NonNull List<RssItem> data) {
        this.data = data;
    }
}
