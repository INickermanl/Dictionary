package nickerman.com.dictionary2.screens.main;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import nickerman.com.dictionary2.navigation.Navigator;
import nickerman.com.dictionary2.room.entity.TranslateWord;


public interface MainContract {
    interface View {
        Observable<Object> addWord();

        Observable<CharSequence> search();

        void setWordAdapter(List<TranslateWord> listWords, ClickCallback callback);

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
