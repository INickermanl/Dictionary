package nickerman.com.dictionary2.screens.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.base.Constants;
import nickerman.com.dictionary2.navigation.Navigator;
import nickerman.com.dictionary2.navigation.Screen;
import nickerman.com.dictionary2.navigation.ScreenType;
import nickerman.com.dictionary2.room.entity.TranslateWord;
import nickerman.com.dictionary2.room.repository.Repository;

import static nickerman.com.dictionary2.base.Constants.WORD_BOOK;


public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private CompositeDisposable subscriptions;
    private ArrayList<WordItem> listWords;
    private Navigator navigator;
    private Handler handler;
    private ClickCallback clickCallback;
    private Repository mRepository;
    private LiveData<List<TranslateWord>> listWordsRoom;

    public MainPresenter(Application application) {
        listWords = Paper.book().read(WORD_BOOK, new ArrayList<WordItem>());
        handler = new Handler(Looper.getMainLooper());
        this.mRepository = new Repository(application);
        listWordsRoom = mRepository.getAllTranslateWords();

        this.clickCallback = new ClickCallback() {
            @Override
            public void editItem(int position) {
                Bundle args = new Bundle();
                args.putString(Constants.POSITION, position + "");
                navigator.navigateTo(Screen.EDIT, ScreenType.ACTIVITY);

            }

            @Override
            public void deleteItem(int position) {
                listWords.remove(position);

                for (int i = position; i < listWords.size(); i++) {
                    WordItem item = listWords.get(i);
                    listWords.set(i, item).setNumber(i);
                }
                Paper.book().write(WORD_BOOK, listWords);


                view.notifyAdapter();
            }
        };
    }

    @Override
    public void start(MainContract.View view) {
        this.view = view;
        subscriptions = new CompositeDisposable();
        initAction();


    }

    private void initAction() {
        view.setWordAdapter(listWords, clickCallback);
        view.search()
                .skip(1)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        subscriptions.add(d);
                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        view.showProgressBar(true);


                        String searchWord = charSequence.toString().trim();
                        ArrayList<WordItem> searchingListWord = new ArrayList<>();

                        for (WordItem item : listWords) {
                            String englishWord = item.getEnglishWord();

                            if (englishWord.length() >= searchWord.length()) {
                                if (searchWord.equals(englishWord.substring(0, searchWord.length()))) {
                                    searchingListWord.add(item);
                                }
                            }

                        }

                        //setListWords
                        if (searchingListWord.size() > 0) {
                            view.setWordAdapter(searchingListWord, clickCallback);
                        } else {
                            view.setWordAdapter(listWords, clickCallback);
                        }


                        view.showProgressBar(false);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        view.addWord().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(Object o) {
                navigator.navigateTo(Screen.ADD_WORD, ScreenType.ACTIVITY);
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
        subscriptions.dispose();
        subscriptions = null;
    }


    @Override
    public void setNavigation(Navigator navigation) {
        this.navigator = navigation;
    }
}
