package nickerman.com.dictionary2;


import android.os.Bundle;
import android.view.View;

import nickerman.com.dictionary2.base.BaseActivity;
import nickerman.com.dictionary2.base.Constants;
import nickerman.com.dictionary2.room.TranslateWordRoomDatabase;
import nickerman.com.dictionary2.screens.edit_item.EditItemContract;
import nickerman.com.dictionary2.screens.edit_item.EditItemPresenter;
import nickerman.com.dictionary2.screens.edit_item.EditItemView;

public class EditItemActivity extends BaseActivity {
    private View root;
    private EditItemContract.View view;
    private EditItemContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        int position = Integer.valueOf(getIntent().getStringExtra(Constants.POSITION));

        TranslateWordRoomDatabase translateWordRoomDatabase = TranslateWordRoomDatabase.getINSTANCE(this);

        this.root = findViewById(R.id.root);
        this.view = new EditItemView(root);
        this.presenter = new EditItemPresenter(position, translateWordRoomDatabase);
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
