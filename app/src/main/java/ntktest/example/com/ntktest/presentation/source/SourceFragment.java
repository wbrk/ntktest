package ntktest.example.com.ntktest.presentation.source;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ntktest.example.com.ntktest.R;

public class SourceFragment extends Fragment implements SourceView {
    private static final String TAG = "SourceFragment";

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.url)
    EditText url;

    private Unbinder unbinder;
    private SourcePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_source, container, false);
        unbinder = ButterKnife.bind(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = SourceFragmentArgs.fromBundle(getArguments()).getSourceId();
        Log.e(TAG, "onViewCreated: id: " + id);
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
}