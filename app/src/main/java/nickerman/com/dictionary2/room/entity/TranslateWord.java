package nickerman.com.dictionary2.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "words")
public class TranslateWord {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "english_word")
    private String englishWord;

    @ColumnInfo(name = "translate_word")
    private String translateWord;


    public TranslateWord(String englishWord, String translateWord) {

        this.englishWord = englishWord;
        this.translateWord = translateWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public void setTranslateWord(String translateWord) {
        this.translateWord = translateWord;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getTranslateWord() {
        return translateWord;
    }
}
