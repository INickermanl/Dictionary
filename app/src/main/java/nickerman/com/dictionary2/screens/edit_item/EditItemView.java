package nickerman.com.dictionary2.screens.edit_item;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import nickerman.com.dictionary2.R;

public class EditItemView implements EditItemContract.View {
    private View root;
    private EditText englishWord, translateWord;
    private View changeWordAction;
    private TextView textChangeWordAction;


    public EditItemView(View root) {

        this.root = root;
        initView();
    }

    private void initView() {

        this.englishWord = root.findViewById(R.id.english_word);
        this.translateWord = root.findViewById(R.id.translate_word);
        this.changeWordAction = root.findViewById(R.id.add_button);
        this.textChangeWordAction = root.findViewById(R.id.action_button_text);

    }

    @Override
    public Observable<Object> changeWordAction() {
        return RxView.clicks(changeWordAction);
    }

    @Override
    public String getEnglishWord() {
        return englishWord.getText().toString().trim();
    }

    @Override
    public String getTranslateWord() {
        return translateWord.getText().toString().trim();
    }

    @Override
    public void setEnglishWord(String englishWord) {
        this.englishWord.setText(englishWord);
    }

    @Override
    public void setTranslateWord(String translateWord) {
            this.translateWord.setText(translateWord);
    }
}
