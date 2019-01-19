package nickerman.com.dictionary2.navigation.factories;

import android.app.Activity;
import android.content.Intent;

import nickerman.com.dictionary2.AddWord;
import nickerman.com.dictionary2.StartActivity;
import nickerman.com.dictionary2.base.App;
import nickerman.com.dictionary2.navigation.Screen;


public class ScreenActivityFactory {

    public Intent getActivityByType(Screen screen) {
        return new Intent(App.getInstance(), getActivityClassByType(screen));
    }

    private Class<? extends Activity> getActivityClassByType(Screen screen) {
        switch (screen) {
            case START_ACTIVITY:
                return StartActivity.class;
            case ADD_WORD:
                return AddWord.class;


            default:
                return StartActivity.class;
        }
    }

}
