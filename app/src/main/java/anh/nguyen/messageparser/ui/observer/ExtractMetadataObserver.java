package anh.nguyen.messageparser.ui.observer;

import javax.inject.Inject;

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

    @Inject
    public ExtractMetadataObserver(MainView mainView) {
        mMainView = mainView;
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
        mMainView.showMetadata(messageMetadata);
    }
}
