package ntktest.example.com.ntktest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RssSourceDao {
    @Query("SELECT * FROM RssSource")
    List<RssSource> all();

    @Query("SELECT * FROM RssSource WHERE id = :id")
    RssSource getById(int id);

    @Insert
    void insert(RssSource... sources);

    @Update
    void update(RssSource... sources);

    @Delete
    void delete(RssSource... sources);
}
