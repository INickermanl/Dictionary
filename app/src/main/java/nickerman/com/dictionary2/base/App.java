package nickerman.com.dictionary2.base;

import android.support.multidex.MultiDexApplication;

import io.paperdb.Paper;

public class App extends MultiDexApplication {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Paper.init(this);
    }

    public static App getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application initialization error!");
        }
        return instance;
    }
}
