package nickerman.com.dictionary2.screens.main;

import java.util.ArrayList;

import io.reactivex.Observable;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.navigation.Navigator;


public interface MainContract {
    interface View {
        Observable<Object> addWord();

        Observable<CharSequence> search();

        void setWordAdapter(ArrayList<WordItem> listWord, ClickCallback callback);

        String getSearchWords();

        void showProgressBar(boolean show);

        void notifyAdapter();
    }

    interface Presenter {
        void setNavigation(Navigator navigation);

        void start(View view);

        void stop();
    }
}
