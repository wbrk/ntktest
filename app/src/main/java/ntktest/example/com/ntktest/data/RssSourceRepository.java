package ntktest.example.com.ntktest.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class RssSourceRepository {
    private static RssSourceRepository instance;

    @NonNull
    private RssSourceDao sourceDao = RssDatabase.instance().sourceDao();

    @Nullable
    private List<RssSource> cache;

    @NonNull
    public static RssSourceRepository instance() {
        if (instance == null) {
            instance = new RssSourceRepository();
        }
        return instance;
    }

    @NonNull
    public List<RssSource> getSources(){
        return getSources(false);
    }

    public void add(@NonNull RssSource source) {
        sourceDao.insert(source);
        getSources(true);
    }

    public void update(@NonNull RssSource source) {
        sourceDao.update(source);
        getSources(true);
    }

    public void delete(@NonNull RssSource source) {
        sourceDao.delete(source);
        getSources(true);
    }

    private RssSourceRepository() {}

    private List<RssSource> getSources(boolean forceUpdate) {
        if (forceUpdate || cache == null) {
            cache = sourceDao.all();
        }
        return cache;
    }
}
