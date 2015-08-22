package anh.nguyen.messageparser.ui.main.presenter;

import javax.inject.Inject;
import javax.inject.Named;

import anh.nguyen.messageparser.interactor.ExtractMetadataInteractor;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.ui.main.observer.ExtractMetadataObserver;
import anh.nguyen.messageparser.ui.main.view.MainView;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;
    private ExtractMetadataInteractor mExtractMetadataInteractor;
    private ExtractMetadataObserver mExtractMetadataObserver;
    private Scheduler mSubscribeOnScheduler;
    private Scheduler mObserveOnScheduler;

    private Observable<MessageMetadata> mMetadataObservable;
    private Subscription mMetadataSubscription = Subscriptions.empty();

    @Inject
    public MainPresenterImpl(MainView mainView, ExtractMetadataInteractor extractMetadataInteractor, ExtractMetadataObserver extractMetadataObserver, @Named("io-scheduler") Scheduler subscribeOnScheduler, @Named("ui-scheduler") Scheduler observeOnScheduler) {
        mMainView = mainView;
        mExtractMetadataInteractor = extractMetadataInteractor;
        mExtractMetadataObserver = extractMetadataObserver;
        mSubscribeOnScheduler = subscribeOnScheduler;
        mObserveOnScheduler = observeOnScheduler;
    }

    @Override
    public void resume() {
        // once the activity resumed
        // we subscribe again so that we can continue to listen the observable
        if (mMetadataSubscription.isUnsubscribed() && mMetadataObservable != null) {
            mMetadataSubscription = mMetadataObservable.subscribe(mExtractMetadataObserver);
        }
    }

    @Override
    public void pause() {
        // unsubscribe since we don't want the observable to emit results
        // when our activity is paused, which means that our context might not be available
        mMetadataSubscription.unsubscribe();
    }

    @Override
    public void showAsJsonString() {
        mMainView.showMetadataAsString();
    }

    @Override
    public void showAsCards() {
        mMainView.showMetadataAsList();
    }

    @Override
    public void parse(String message) {
        // tell the view to show progress
        mMainView.showProgress();

        mMetadataObservable = mExtractMetadataInteractor.execute(message)
                .subscribeOn(mSubscribeOnScheduler)
                .observeOn(mObserveOnScheduler);

        mMetadataSubscription = mMetadataObservable.subscribe(mExtractMetadataObserver);
    }
}
