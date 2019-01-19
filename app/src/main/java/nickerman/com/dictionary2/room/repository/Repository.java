package nickerman.com.dictionary2.room.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import nickerman.com.dictionary2.room.TranslateWordRoomDatabase;
import nickerman.com.dictionary2.room.dao.TranslateWordDAO;
import nickerman.com.dictionary2.room.entity.TranslateWord;


public class Repository {

    private TranslateWordDAO translateWordDAO;
    private LiveData<List<TranslateWord>> allTranslateWords;

    public Repository(Application application) {

        TranslateWordRoomDatabase roomDatabase = TranslateWordRoomDatabase.getINSTANCE(application);
        this.translateWordDAO = roomDatabase.translateWordDAO();
        this.allTranslateWords = translateWordDAO.getAllWords();

    }

    public LiveData<List<TranslateWord>> getAllTranslateWords(){
        return this.allTranslateWords;
    }





    private static class insertAsyncTask extends AsyncTask<TranslateWord,Void,Void>{
        private TranslateWordDAO asyncDAO;

        public insertAsyncTask(TranslateWordDAO asyncDAO) {
            this.asyncDAO = asyncDAO;
        }

        @Override
        protected Void doInBackground( final TranslateWord... translateWords) {
            asyncDAO.insert(translateWords[0]);
            return null;
        }
    }
}
