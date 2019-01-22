package nickerman.com.dictionary2.screens.edit_item;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import nickerman.com.dictionary2.navigation.Navigator;

public class EditItemPresenter implements EditItemContract.Presenter {

    private EditItemContract.View view;
    private CompositeDisposable subscriptions;
    private Navigator navigator;

    @Override
    public void start(EditItemContract.View view) {
        this.view = view;
        subscriptions = new CompositeDisposable();
        initAction();

    }

    private void initAction() {
        view.changeWordAction().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriptions.add(d);
            }

            @Override
            public void onNext(Object o) {

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
