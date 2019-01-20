package nickerman.com.dictionary2.room;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.dictionary2.room.dao.TranslateWordDAO;
import nickerman.com.dictionary2.room.entity.TranslateWord;
import nickerman.com.dictionary2.room.repository.IWordDataSource;


public class WordDataSource implements IWordDataSource {

    private TranslateWordDAO translateWordDAO;
    private static WordDataSource mWordDataSourceInstance;

    public WordDataSource(TranslateWordDAO translateWordDAO) {
        this.translateWordDAO = translateWordDAO;
    }

    public static WordDataSource getInstance(TranslateWordDAO translateWordDAO) {
        if (mWordDataSourceInstance == null) {
            mWordDataSourceInstance = new WordDataSource(translateWordDAO);
        }
        return mWordDataSourceInstance;
    }

    @Override
    public Flowable<TranslateWord> getTranslateWordById(int translateId) {
        return this.translateWordDAO.getTranslateWordById(translateId);
    }

    @Override
    public Flowable<List<TranslateWord>> getAllWords() {
        return translateWordDAO.getAllWords();
    }

    @Override
    public void insertWord(TranslateWord... translateWord) {
        translateWordDAO.insertWord(translateWord);
    }

    @Override
    public void updateWord(TranslateWord... translateWords) {
        translateWordDAO.updateWord(translateWords);
    }

    @Override
    public void deleteWord(TranslateWord translateWord) {
        translateWordDAO.deleteWord(translateWord);
    }

    @Override
    public void deleteAllWords() {
        translateWordDAO.deleteAllWords();
    }
}
