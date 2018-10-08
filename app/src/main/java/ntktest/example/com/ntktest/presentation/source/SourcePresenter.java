package ntktest.example.com.ntktest.presentation.source;

import android.support.annotation.NonNull;

public class SourcePresenter {
    @NonNull
    private SourceView view;

    public SourcePresenter(@NonNull SourceView view) {
        this.view = view;
    }
}
