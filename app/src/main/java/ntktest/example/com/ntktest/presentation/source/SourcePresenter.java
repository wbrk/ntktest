package ntktest.example.com.ntktest.presentation.source;

import android.support.annotation.NonNull;

import java.util.List;

import ntktest.example.com.ntktest.data.RssSource;
import ntktest.example.com.ntktest.data.RssSourceRepository;

public class SourcePresenter {
    @NonNull private SourceView view;

    public SourcePresenter(@NonNull SourceView view) {
        this.view = view;
    }

    public void requestData() {
        List<RssSource> data = RssSourceRepository.instance().getSources();
        view.showData(data);
    }
}
