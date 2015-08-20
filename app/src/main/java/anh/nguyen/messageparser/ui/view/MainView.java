package anh.nguyen.messageparser.ui.view;

import java.util.List;

import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.model.MessageMetadataItem;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public interface MainView {
    void bindMetadata(String message);
    void bindMetadata(List<MessageMetadataItem> messageMetadataItems);
    void showMetadataAsString();
    void showMetadataAsList();
    void showToast(String message);
    void showProgress();
    void hideProgress();
}
