package anh.nguyen.messageparser.ui.main.presenter;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public interface MainPresenter {
    void resume();

    void pause();

    void parse(String chatMessage);

    void showAsJsonString();

    void showAsCards();
}
