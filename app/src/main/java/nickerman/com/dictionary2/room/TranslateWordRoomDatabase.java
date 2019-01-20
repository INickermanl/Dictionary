package nickerman.com.dictionary2.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nickerman.com.dictionary2.room.dao.TranslateWordDAO;
import nickerman.com.dictionary2.room.entity.TranslateWord;


@Database(entities = {TranslateWord.class}, version = 1, exportSchema = false)
public abstract class TranslateWordRoomDatabase extends RoomDatabase {

    public abstract TranslateWordDAO translateWordDAO();

    private static TranslateWordRoomDatabase INSTANCE;


    public static TranslateWordRoomDatabase getINSTANCE(final Context context) {

        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TranslateWordRoomDatabase.class,
                    "dictionary"
            ).fallbackToDestructiveMigration()
                    .build();

        }

        return INSTANCE;

    }

}
