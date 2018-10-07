package ntktest.example.com.ntktest.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RssSource {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name = "";

    @NonNull
    public String url = "";
}
