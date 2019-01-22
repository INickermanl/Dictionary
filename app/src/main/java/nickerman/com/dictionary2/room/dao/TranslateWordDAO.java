package nickerman.com.dictionary2.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.dictionary2.room.entity.TranslateWord;

@Dao
public interface TranslateWordDAO {


    @Query("SELECT * FROM words WHERE  id = :id")
    Flowable<TranslateWord> getTranslateWordById(int id);

    @Query("SELECT * FROM words")
    Flowable<List<TranslateWord>> getAllWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWord(TranslateWord... translateWord);

    @Update
    void updateWord(TranslateWord... translateWords);

    @Delete
    void deleteWord(TranslateWord translateWord);

    @Query("DELETE FROM words")
    void deleteAllWords();
}
