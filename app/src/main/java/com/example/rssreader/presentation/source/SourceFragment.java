package com.example.rssreader.presentation.source;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.rssreader.R;
import com.example.rssreader.data.RssSource;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SourceFragment extends Fragment implements SourceView {
    private static final String TAG = "SourceFragment";

    private static final int NEW_SOURCE = -1;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.url)
    EditText url;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;
    private SourcePresenter presenter;
    private boolean newSource = true;

    @NonNull private RssSource source = new RssSource(0, "", "");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_source, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!newSource) {
            name.setText(source.getName());
            url.setText(source.getUrl());
        }

        fab.setOnClickListener((v) -> {
            // todo validate
            String srcName = name.getText().toString();
            String srcUrl = url.getText().toString();
            source = source.copy(source.getId(), srcName, srcUrl);

            if (newSource) {
                presenter.add(source);
            } else {
                presenter.update(source);
            }

            Navigation.findNavController(v).popBackStack();
            // todo something bad happens to keyboard
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
        presenter = new SourcePresenter(this);

        int id = SourceFragmentArgs.fromBundle(getArguments()).getSourceId();
        if (id != NEW_SOURCE) {
            source = presenter.getById(id);
            setHasOptionsMenu(true);
            newSource = false;
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.new_screen);
            // fixme: kind of a hack
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_source, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remove) {
            presenter.delete(source);
            NavHostFragment.findNavController(this).popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
