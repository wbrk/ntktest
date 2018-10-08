package ntktest.example.com.ntktest.presentation.sourcelist;

import android.support.annotation.NonNull;

import java.util.List;

import ntktest.example.com.ntktest.data.RssSource;
import ntktest.example.com.ntktest.data.RssSourceRepository;

public class SourceListPresenter {
    @NonNull private SourceListView view;

    public SourceListPresenter(@NonNull SourceListView view) {
        this.view = view;
    }

    public void requestData() {
        List<RssSource> data = RssSourceRepository.instance().getSources();
        view.showData(data);
    }
}
