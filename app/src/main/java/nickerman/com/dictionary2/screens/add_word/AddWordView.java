package nickerman.com.dictionary2.screens.add_word;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import nickerman.com.dictionary2.R;

public class AddWordView implements AddWordContract.View {
    private View root;
    private EditText englishWord, translatedWord;
    private View addButton;


    public AddWordView(View root) {
        this.root = root;
        initView();
    }

    private void initView() {
        addButton = root.findViewById(R.id.add_button);
        englishWord = root.findViewById(R.id.english_words);
        translatedWord = root.findViewById(R.id.translate_words);
    }

    @Override
    public String getEnglishWord() {
        return englishWord.getText().toString().trim();
    }

    @Override
    public String getTranslatedWord() {
        return translatedWord.getText().toString().trim();
    }

    @Override
    public Observable<Object> addWord() {
        return RxView.clicks(addButton);
    }
}
