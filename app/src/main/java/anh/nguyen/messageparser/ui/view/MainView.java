package anh.nguyen.messageparser.ui.view;

import anh.nguyen.messageparser.model.MessageMetadata;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public interface MainView {
    void showMetadata(String message);
    void showMetadata(MessageMetadata messageMetadata);
    void showToast(String message);
    void showProgress();
    void hideProgress();
}
