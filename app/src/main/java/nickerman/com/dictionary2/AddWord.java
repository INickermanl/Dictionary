package nickerman.com.dictionary2;

import android.os.Bundle;
import android.view.View;

import nickerman.com.dictionary2.base.BaseActivity;
import nickerman.com.dictionary2.screens.add_word.AddWordContract;
import nickerman.com.dictionary2.screens.add_word.AddWordPresenter;
import nickerman.com.dictionary2.screens.add_word.AddWordView;


public class AddWord extends BaseActivity {
    private View root;
    private AddWordContract.View view;
    private AddWordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        root = findViewById(R.id.root);
        view = new AddWordView(root);
        presenter = new AddWordPresenter(getApplication());


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start(view);
        presenter.setNavigator(getNavigator());

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
