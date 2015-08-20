package anh.nguyen.messageparser.ui.presenter;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import anh.nguyen.messageparser.di.TestMainPresenterImplModule;
import anh.nguyen.messageparser.interactor.ExtractMetadataInteractor;
import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.ui.observer.ExtractMetadataObserver;
import anh.nguyen.messageparser.ui.view.MainView;
import dagger.ObjectGraph;
import rx.Observable;
import rx.schedulers.TestScheduler;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class MainPresenterImplTest {
    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MainView mMainView;
    @Inject
    ExtractMetadataObserver mExtractMetadataObserver;
    @Inject
    ExtractMetadataInteractor mExtractMetadataInteractor;
    @Inject
    @Named("ui-scheduler")
    TestScheduler mObserveOnScheduler;
    @Inject
    @Named("io-scheduler")
    TestScheduler mSubscribeOnScheduler;
    @Inject
    Gson mGson;

    MessageMetadata mMessageMetadata;

    @Before
    public void setUp() throws Exception {
        ObjectGraph.create(new TestMainPresenterImplModule()).inject(this);

        mMessageMetadata = new MessageMetadata();
        mMessageMetadata.setMentions(Arrays.asList("bob", "john"));
        mMessageMetadata.setEmoticons(Arrays.asList("success"));
        mMessageMetadata.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Twitter / jdorfman: nice @littlebigdetail from ...")));

        Mockito.when(mExtractMetadataInteractor.execute(Mockito.anyString()))
                .thenReturn(Observable.just(mMessageMetadata).delay(30, TimeUnit.MILLISECONDS, mSubscribeOnScheduler));
    }

    @After
    public void tearDown() throws Exception {
        mMainPresenter = null;
    }

    @Test
    public void resume_withoutExtract() {
        mMainPresenter.resume();

        mSubscribeOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);

        Mockito.verifyZeroInteractions(mMainView);
    }

    @Test
    public void pause_withoutExtract() {
        mSubscribeOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);

        mMainPresenter.pause();

        Mockito.verifyZeroInteractions(mMainView);
    }

    @Test
    public void resume_withExtract() throws Exception {
        String message = "message";

        mMainPresenter.parse(message);

        mSubscribeOnScheduler.advanceTimeBy(15, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(15, TimeUnit.MILLISECONDS);

        mMainPresenter.pause();

        mSubscribeOnScheduler.advanceTimeBy(14, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(14, TimeUnit.MILLISECONDS);

        mMainPresenter.resume();

        mSubscribeOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);

        Mockito.verify(mMainView).showProgress();
        Mockito.verify(mMainView).bindMetadata(mMessageMetadata);
        Mockito.verify(mMainView).bindMetadata(mGson.toJson(mMessageMetadata));
        Mockito.verify(mMainView).showMetadataAsCards();
        Mockito.verify(mMainView).hideProgress();

        Mockito.verifyNoMoreInteractions(mMainView);
    }

    @Test
    public void pause_withExtract() throws Exception {
        String message = "message";

        mMainPresenter.parse(message);

        Mockito.verify(mMainView).showProgress();

        mSubscribeOnScheduler.advanceTimeBy(25, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(25, TimeUnit.MILLISECONDS);

        mMainPresenter.pause();

        mSubscribeOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);

        Mockito.verifyNoMoreInteractions(mMainView);
    }

    @Test
    public void parse_Successful() throws Exception {
        String message = "message";

        mMainPresenter.parse(message);

        mSubscribeOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);
        mObserveOnScheduler.advanceTimeBy(31, TimeUnit.MILLISECONDS);

        Mockito.verify(mMainView).showProgress();
        Mockito.verify(mMainView).bindMetadata(mMessageMetadata);
        Mockito.verify(mMainView).bindMetadata(mGson.toJson(mMessageMetadata));
        Mockito.verify(mMainView).showMetadataAsCards();
        Mockito.verify(mMainView).hideProgress();
        Mockito.verifyNoMoreInteractions(mMainView);
    }

    @Test
    public void showAsJsonString_viewShouldShowMetadataAsString() {
        mMainPresenter.showAsJsonString();

        Mockito.verify(mMainView).showMetadataAsString();
        Mockito.verifyNoMoreInteractions(mMainView);
    }

    @Test
    public void showAsCards_viewShouldShowMetadataAsCards() {
        mMainPresenter.showAsCards();

        Mockito.verify(mMainView).showMetadataAsCards();
        Mockito.verifyNoMoreInteractions(mMainView);
    }
}
