package nickerman.com.dictionary2.navigation;

import android.os.Bundle;


public interface Navigator {
    void navigateTo(Screen screen, ScreenType type);
    void navigateTo(Screen screen, ScreenType type, Bundle args);
    Screen getScreen();
}
