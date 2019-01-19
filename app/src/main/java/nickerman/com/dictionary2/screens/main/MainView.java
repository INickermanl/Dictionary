package nickerman.com.dictionary2.screens.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;

import io.reactivex.Observable;
import nickerman.com.dictionary2.R;
import nickerman.com.dictionary2.WordItem;
import nickerman.com.dictionary2.base.App;


public class MainView implements MainContract.View {
    private View root;
    private FloatingActionButton fab;
    private EditText searchWord;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private View searchContent;
    private RecyclerView.Adapter adapter;


    public MainView(View root) {
        this.root = root;
        initView();
    }

    private void initView() {
        this.fab = root.findViewById(R.id.fab);
        this.searchWord = root.findViewById(R.id.search);
        this.recyclerView = root.findViewById(R.id.recycler_view);
        this.progressBar = root.findViewById(R.id.progress_bar);
        this.searchContent = root.findViewById(R.id.container);

    }

    @Override
    public void setWordAdapter(ArrayList<WordItem> listWord, ClickCallback callback) {
        LinearLayoutManager llm = new LinearLayoutManager(App.getInstance(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        adapter = new WordItemAdaptor(listWord, callback);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();

    }

    @Override
    public Observable<CharSequence> search() {
        return RxTextView.textChanges(searchWord);
    }

    @Override
    public Observable<Object> addWord() {
        return RxView.clicks(fab);
    }

    @Override
    public String getSearchWords() {
        return searchWord.getText().toString().trim();
    }

    @Override
    public void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        searchContent.setVisibility(show ? View.GONE : View.VISIBLE);
    }


}
