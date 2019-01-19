package nickerman.com.dictionary2.screens.edit_item;

public interface EditItemContract {
    interface View{


    }

    interface Presenter{
        void start(View view);
        void stop();
    }
}
