package nickerman.com.dictionary2.screens.edit_item;

import io.reactivex.Observable;
import nickerman.com.dictionary2.navigation.Navigator;

public interface EditItemContract {
    interface View {

        Observable<Object> changeWordAction();

        String getEnglishWord();

        String getTranslateWord();

        void setEnglishWord(String englishWord);

        void setTranslateWord(String translateWord);

    }

    interface Presenter {
        void start(View view);

        void stop();

        void setNavigator(Navigator navigator);
    }
}
