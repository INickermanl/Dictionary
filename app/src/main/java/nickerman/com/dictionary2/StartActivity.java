package nickerman.com.dictionary2;

import android.os.Bundle;
import android.view.View;

import io.paperdb.Paper;
import nickerman.com.dictionary2.base.App;
import nickerman.com.dictionary2.base.BaseActivity;
import nickerman.com.dictionary2.room.TranslateWordRoomDatabase;
import nickerman.com.dictionary2.screens.main.MainContract;
import nickerman.com.dictionary2.screens.main.MainPresenter;
import nickerman.com.dictionary2.screens.main.MainView;


public class StartActivity extends BaseActivity {
    private View root;
    private MainContract.View view;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        this.root = findViewById(R.id.root);
        Paper.init(this);
        view = new MainView(root);
        TranslateWordRoomDatabase translateWordRoomDatabase = TranslateWordRoomDatabase.getINSTANCE(this);
        presenter = new MainPresenter(translateWordRoomDatabase);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(view);
        presenter.setNavigation(getNavigator());
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
