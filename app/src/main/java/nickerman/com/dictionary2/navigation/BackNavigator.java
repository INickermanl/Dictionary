package nickerman.com.dictionary2.navigation;

public interface BackNavigator {
    void navigateBack();
    void tryExitActivity();
    void setCouldNavigateBack(boolean couldNavigateBack);
}
