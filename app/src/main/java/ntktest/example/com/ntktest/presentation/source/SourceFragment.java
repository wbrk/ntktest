package ntktest.example.com.ntktest.presentation.source;

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

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ntktest.example.com.ntktest.R;
import ntktest.example.com.ntktest.data.RssSource;

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

    @NonNull private RssSource source = new RssSource();

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
            name.setText(source.name);
            url.setText(source.url);
        }

        fab.setOnClickListener((v) -> {
            // todo validate
            source.name = name.getText().toString();
            source.url = url.getText().toString();

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
        }
        return true;
    }
}
