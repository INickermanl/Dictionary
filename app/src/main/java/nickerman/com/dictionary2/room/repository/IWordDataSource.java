package nickerman.com.dictionary2.room.repository;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.dictionary2.room.entity.TranslateWord;

public interface IWordDataSource {


    Flowable<TranslateWord> getTranslateWordById(int translateId);

    Flowable<List<TranslateWord>> getAllWords();

    void insertWord(TranslateWord... translateWord);

    void updateWord(TranslateWord... translateWords);

    void deleteWord(TranslateWord translateWord);

    void deleteAllWords();

}
