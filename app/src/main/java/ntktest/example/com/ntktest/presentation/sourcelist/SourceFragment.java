package ntktest.example.com.ntktest.presentation.sourcelist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssSource;

public class SourceFragment extends Fragment implements SourceView {
    private static final String TAG = "SourceFragment";

    @BindView(R.id.list)
    RecyclerView recycler;

    private Unbinder unbinder;
    private SourceAdapter adapter;
    private SourcePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_source, container, false);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SourcePresenter(this);
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
