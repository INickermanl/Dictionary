package nickerman.com.dictionary2.room.repository;

import java.util.List;

import io.reactivex.Flowable;
import nickerman.com.dictionary2.room.entity.TranslateWord;

public class Repository implements IWordDataSource {

    private IWordDataSource mLocalDaoSource;
    private static Repository mInstance;

    public Repository(IWordDataSource mLocalDataSource) {
        this.mLocalDaoSource = mLocalDataSource;
    }

    public static Repository getInstance(IWordDataSource mLocalDataSource) {
        if (mInstance == null) {
            mInstance = new Repository(mLocalDataSource);
        }
        return mInstance;
    }


    @Override
    public Flowable<TranslateWord> getTranslateWordById(int translateId) {
        return mLocalDaoSource.getTranslateWordById(translateId);
    }

    @Override
    public Flowable<List<TranslateWord>> getAllWords() {
        return mLocalDaoSource.getAllWords();
    }

    @Override
    public void insertWord(TranslateWord... translateWord) {
        mLocalDaoSource.insertWord(translateWord);
    }

    @Override
    public void updateWord(TranslateWord... translateWords) {
        mLocalDaoSource.updateWord(translateWords);
    }

    @Override
    public void deleteWord(TranslateWord translateWord) {
        mLocalDaoSource.deleteWord(translateWord);
    }

    @Override
    public void deleteAllWords() {
        mLocalDaoSource.deleteAllWords();
    }
}
