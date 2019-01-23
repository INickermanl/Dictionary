package nickerman.com.dictionary2.base;

import android.support.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application initialization error!");
        }
        return instance;
    }
}
