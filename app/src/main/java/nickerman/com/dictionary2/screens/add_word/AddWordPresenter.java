package nickerman.com.dictionary2.screens.add_word;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.navigation.Navigator;
import nickerman.com.dictionary2.navigation.Screen;
import nickerman.com.dictionary2.navigation.ScreenType;
import nickerman.com.dictionary2.room.TranslateWordRoomDatabase;
import nickerman.com.dictionary2.room.entity.TranslateWord;
import nickerman.com.dictionary2.room.WordDataSource;
import nickerman.com.dictionary2.room.repository.Repository;


public class AddWordPresenter implements AddWordContract.Presenter {
    private AddWordContract.View view;
    private CompositeDisposable subscriptions;
    private Navigator navigator;
    private Repository mRepository;


    public AddWordPresenter(TranslateWordRoomDatabase translateWordRoomDatabase) {
        this.mRepository = Repository.getInstance(WordDataSource.getInstance(translateWordRoomDatabase.translateWordDAO()));

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

                    Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                        @Override
                        public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                            mRepository.insertWord(translateWord);
                            emitter.onComplete();
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {
                                    Log.d("accept", "accept");
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.d("accept", "accept");
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    navigator.navigateTo(Screen.START_ACTIVITY, ScreenType.ACTIVITY);
                                }
                            });

                    subscriptions.add(disposable);
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
        subscriptions.dispose();
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
