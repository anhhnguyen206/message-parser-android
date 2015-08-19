package anh.nguyen.messageparser.ui.observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;

import anh.nguyen.messageparser.di.TestExtractMetadataObserverModule;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.ui.view.MainView;
import dagger.ObjectGraph;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class ExtractMetadataObserverTest {
    @Inject
    ExtractMetadataObserver mExtractMetadataObserver;
    @Inject
    MainView mMainView;

    @Before
    public void setUp() {
        ObjectGraph.create(new TestExtractMetadataObserverModule()).inject(this);
    }

    @After
    public void tearDown() {
        mExtractMetadataObserver = null;
    }
    @Test
    public void testOnError() {
        String errorMessage = Mockito.anyString();
        mExtractMetadataObserver.onError(new Throwable(errorMessage));
        Mockito.verify(mMainView).showToast(errorMessage);
        mExtractMetadataObserver.onCompleted();
        Mockito.verify(mMainView).hideProgress();
        Mockito.verifyNoMoreInteractions(mMainView);
    }

    @Test
    public void testOnNext() {
        MessageMetadata messageMetadata = Mockito.mock(MessageMetadata.class);
        mExtractMetadataObserver.onNext(messageMetadata);
        Mockito.verify(mMainView).showMetadata(messageMetadata);
        mExtractMetadataObserver.onCompleted();
        Mockito.verify(mMainView).hideProgress();
        Mockito.verifyNoMoreInteractions(mMainView);
    }
}
