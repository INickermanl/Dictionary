package nickerman.com.dictionary2.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import nickerman.com.dictionary2.room.entity.TranslateWord;


@Dao
public interface TranslateWordDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TranslateWord translateWord);

    @Query("DELETE FROM translate_word")
    void deleteAll();

    @Query("SELECT * FROM translate_word")
    LiveData<List<TranslateWord>> getAllWords();
}
