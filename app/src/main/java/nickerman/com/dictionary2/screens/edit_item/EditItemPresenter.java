package nickerman.com.dictionary2.screens.edit_item;

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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nickerman.com.dictionary2.navigation.Navigator;
import nickerman.com.dictionary2.navigation.Screen;
import nickerman.com.dictionary2.navigation.ScreenType;
import nickerman.com.dictionary2.room.TranslateWordRoomDatabase;
import nickerman.com.dictionary2.room.WordDataSource;
import nickerman.com.dictionary2.room.entity.TranslateWord;
import nickerman.com.dictionary2.room.repository.Repository;

public class EditItemPresenter implements EditItemContract.Presenter {

    private EditItemContract.View view;
    private CompositeDisposable subscriptions;
    private Navigator navigator;
    private Repository mRepository;
    private TranslateWord updateTranslateWord;
    private int idInDB;
    private List<TranslateWord> translateWordList = new ArrayList<>();


    public EditItemPresenter(int idInDB, TranslateWordRoomDatabase translateWordRoomDatabase) {
        this.idInDB = idInDB;
        this.mRepository = Repository.getInstance(WordDataSource.getInstance(translateWordRoomDatabase.translateWordDAO()));

    }

    @Override
    public void start(EditItemContract.View view) {
        this.view = view;
        subscriptions = new CompositeDisposable();
        initView();
        initAction();

    }

    private void initView() {
        view.setTextChangeWordAction();
        //loadAllData();

         getWordByPosition();

    }

    private void getWordByPosition() {
        Disposable disposable = mRepository.getTranslateWordById(idInDB)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TranslateWord>() {
                    @Override
                    public void accept(TranslateWord translateWord) throws Exception {
                        updateTranslateWord = translateWord;
                        view.setTranslateWord(translateWord.getTranslateWord());
                        view.setEnglishWord(translateWord.getEnglishWord());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("data load", "load data field");
                    }
                });

        subscriptions.add(disposable);
    }

    private void loadAllData() {
        Disposable disposable = mRepository.getAllWords()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<TranslateWord>>() {
                    @Override
                    public void accept(List<TranslateWord> translateWords) throws Exception {
                        translateWordList = translateWords;
                        //find needed item
                        for (TranslateWord translateWord : translateWordList) {
                            if (translateWord.getId() == idInDB) {

                                Log.d("data",translateWord.getId() + " ");
                                view.setTranslateWord(translateWord.getTranslateWord());
                                view.setEnglishWord(translateWord.getEnglishWord());
                            }
                        }

                    }
                });

        subscriptions.add(disposable);
    }

    private void initAction() {
        view.changeWordAction().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(Object o) {

                Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        //set him new data
                        updateTranslateWord.setTranslateWord(view.getTranslateWord());
                        updateTranslateWord.setEnglishWord(view.getEnglishWord());

                        //update into db
                        mRepository.updateWord(updateTranslateWord);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe();

                subscriptions.add(disposable);

                //navigate into main activity
                navigator.navigateTo(Screen.START_ACTIVITY, ScreenType.ACTIVITY);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("Error changeWordAction", e.getLocalizedMessage());
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
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
