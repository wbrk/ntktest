package ntktest.example.com.ntktest.presentation.source;

import android.support.annotation.NonNull;

import ntktest.example.com.ntktest.data.RssSource;
import ntktest.example.com.ntktest.data.RssSourceRepository;

public class SourcePresenter {
    @NonNull
    private SourceView view;

    public SourcePresenter(@NonNull SourceView view) {
        this.view = view;
    }

    @NonNull
    public RssSource getById(int id) {
        return RssSourceRepository.instance().getById(id);
    }

    public void add(@NonNull RssSource source) {
        RssSourceRepository.instance().add(source);
    }

    public void update(@NonNull RssSource source) {
        RssSourceRepository.instance().update(source);
    }

    public void delete(@NonNull RssSource source) {
        RssSourceRepository.instance().delete(source);
    }
}
