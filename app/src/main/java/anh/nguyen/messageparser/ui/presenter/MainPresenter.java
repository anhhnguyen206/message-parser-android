package anh.nguyen.messageparser.ui.presenter;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public interface MainPresenter {
    void resume();
    void pause();
    void parse(String chatMessage);
    void showAsJsonString();
}
