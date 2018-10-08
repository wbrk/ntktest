package ntktest.example.com.ntktest.presentation.source;

import android.support.annotation.NonNull;
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
import ntktest.example.com.ntktest.data.RssSource;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        public TextView name;

        @BindView(R.id.url)
        public TextView url;

        public ViewHolder(@NonNull View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }

    @NonNull
    private List<RssSource> data = new ArrayList<>();

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
        holder.name.setText(source.name);
        holder.url.setText(source.url);
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
}
