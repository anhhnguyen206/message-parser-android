package anh.nguyen.messageparser.ui.view;

import anh.nguyen.messageparser.model.MessageMetadata;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public interface MainView {
    void bindMetadata(String message);
    void bindMetadata(MessageMetadata messageMetadata);
    void showMetadataAsString();
    void showMetadataAsList();
    void showToast(String message);
    void showProgress();
    void hideProgress();
}
