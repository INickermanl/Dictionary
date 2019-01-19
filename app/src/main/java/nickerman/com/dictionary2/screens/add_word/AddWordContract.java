package nickerman.com.dictionary2.screens.add_word;

import io.reactivex.Observable;
import nickerman.com.dictionary2.navigation.Navigator;

public interface AddWordContract {
    interface View{
        Observable<Object> addWord();
        String getEnglishWord();
        String getTranslatedWord();

    }
    interface Presenter{
        void start(View view);
        void stop();
        void setNavigator(Navigator navigator);
    }
}
