package nickerman.com.dictionary2.screens.add_word;


import android.app.Application;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.navigation.Navigator;
import nickerman.com.dictionary2.navigation.Screen;
import nickerman.com.dictionary2.navigation.ScreenType;
import nickerman.com.dictionary2.room.entity.TranslateWord;
import nickerman.com.dictionary2.room.WordDataSource;


public class AddWordPresenter implements AddWordContract.Presenter {
    private AddWordContract.View view;
    private ArrayList<WordItem> wordItemList;
    private CompositeDisposable subscriptions;
    private Navigator navigator;
    private WordDataSource mWordDataSource;

    public AddWordPresenter(Application application) {
       /* mWordDataSource = new WordDataSource(application);*/

        /* wordItemList = Paper.book().read(Constants.WORD_BOOK, new ArrayList<>());*/
    }

    @Override
    public void start(AddWordContract.View view) {
        this.view = view;
        subscriptions = new CompositeDisposable();
        initAction();
    }

    private void initAction() {
        view.addWord().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(Object o) {
                if (view.getTranslatedWord().length() > 0) {

                    TranslateWord translateWord = new TranslateWord(view.getEnglishWord(), view.getTranslatedWord());

                    mWordDataSource.insertWord(translateWord);
                    navigator.navigateTo(Screen.START_ACTIVITY, ScreenType.ACTIVITY);


                   /* WordItem item = new WordItem();
                    item.setEnglishWord(view.getEnglishWord());
                    item.setTranslateWord(view.getTranslatedWord());
                    item.setNumber(wordItemList.size()); //+1
                    wordItemList.add(item);
                    Paper.book().write(Constants.WORD_BOOK, wordItemList);
                    navigator.navigateTo(Screen.START_ACTIVITY, ScreenType.ACTIVITY);*/
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
