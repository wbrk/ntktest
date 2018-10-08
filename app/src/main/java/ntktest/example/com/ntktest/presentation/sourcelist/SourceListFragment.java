package ntktest.example.com.ntktest.presentation.sourcelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssSource;

public class SourceListFragment extends Fragment implements SourceListView {
    private static final String TAG = "SourceListFragment";

    @BindView(R.id.list)
    RecyclerView recycler;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;
    private SourceAdapter adapter;
    private SourceListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_source_list, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new SourceAdapter();

        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        fab.setOnClickListener((v) -> {
            Navigation.findNavController(v).navigate(R.id.actionEditSource);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SourceListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestData();
    }

    @Override
    public void showData(List<RssSource> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
