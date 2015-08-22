package anh.nguyen.messageparser.ui.main.observer;

import com.google.gson.Gson;

import javax.inject.Inject;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.ui.main.view.MainView;
import rx.Observer;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * ExtractMetadataObserver observes the result
 * from an Observable of type Message Metadata
 */
public class ExtractMetadataObserver implements Observer<MessageMetadata> {
    private MainView mMainView;
    private Gson mGson;
    private MessageMetadataConverter mMessageMetadataConverter;

    @Inject
    public ExtractMetadataObserver(MainView mainView, Gson gson, MessageMetadataConverter messageMetadataConverter) {
        mMainView = mainView;
        mGson = gson;
        mMessageMetadataConverter = messageMetadataConverter;
    }

    @Override
    public void onCompleted() {
        // the operation is completed
        // tell the view to hide the progress
        mMainView.hideProgress();
    }

    @Override
    public void onError(Throwable throwable) {
        // encounter error
        // tell the view to show toast with error message
        mMainView.showToast(throwable.getMessage());
    }

    @Override
    public void onNext(MessageMetadata messageMetadata) {
        // the operation is successful without error
        // tell the view to bind a list of MessageMetadataItem
        mMainView.bindMetadata(mMessageMetadataConverter.convert(messageMetadata));
        // tell the view to bind a beautified json String
        mMainView.bindMetadata(mGson.toJson(messageMetadata));
    }
}
