package anh.nguyen.messageparser.ui.observer;

import com.google.gson.Gson;

import javax.inject.Inject;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.ui.view.MainView;
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
        mMainView.hideProgress();
    }

    @Override
    public void onError(Throwable throwable) {
        mMainView.showToast(throwable.getMessage());
    }

    @Override
    public void onNext(MessageMetadata messageMetadata) {
        mMainView.bindMetadata(mMessageMetadataConverter.convert(messageMetadata));
        mMainView.bindMetadata(mGson.toJson(messageMetadata));
        mMainView.showMetadataAsList();
    }
}
