package ntktest.example.com.ntktest.presentation.feed;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssItem;

public class FeedFragment extends Fragment implements FeedView {
    private static final String TAG = "FeedFragment";

    @BindView(R.id.list)
    RecyclerView recycler;

    @BindView(R.id.feed_root)
    View rootView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private Unbinder unbinder;
    private FeedAdapter adapter;
    private FeedPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new FeedAdapter();
        adapter.setOnItemClickListener(this::openDetails);

        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        swipeLayout.setOnRefreshListener(presenter::requestData);
        swipeLayout.setRefreshing(true);

        presenter.requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FeedPresenter(this);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
        swipeLayout.setRefreshing(false);
        // fixme re-request data if was stopped before data arrived
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
    }

    @Override
    public void showData(List<RssItem> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();

        swipeLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        Snackbar.make(rootView, "Error", Snackbar.LENGTH_SHORT).show();
        swipeLayout.setRefreshing(false);
    }

    private void openDetails(int position) {
        try {
            String url = adapter.getData().get(position).getLink();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Snackbar.make(rootView, "Couldn't open item", Snackbar.LENGTH_SHORT).show();
        }
    }
}
