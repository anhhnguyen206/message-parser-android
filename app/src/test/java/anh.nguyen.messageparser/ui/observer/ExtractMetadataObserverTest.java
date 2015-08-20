package anh.nguyen.messageparser.ui.observer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.di.TestExtractMetadataObserverModule;
import anh.nguyen.messageparser.model.EmoticonItem;
import anh.nguyen.messageparser.model.HeaderItem;
import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.model.MessageMetadataItem;
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
    @Inject
    MessageMetadataConverter mMessageMetadataConverter;

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
        MessageMetadata messageMetadata = new MessageMetadata();
        messageMetadata.setMentions(Arrays.asList("bob", "john"));
        messageMetadata.setEmoticons(Arrays.asList("success"));
        messageMetadata.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Twitter / jdorfman: nice @littlebigdetail from ...")));

        List<MessageMetadataItem> messageMetadataItems = new ArrayList<>();
        messageMetadataItems.add(new HeaderItem("Header"));
        messageMetadataItems.add(new EmoticonItem("Emoticon"));

        Mockito.when(mMessageMetadataConverter.convert(messageMetadata))
                .thenReturn(messageMetadataItems);
        mExtractMetadataObserver.onNext(messageMetadata);

        Mockito.verify(mMainView).bindMetadata(messageMetadataItems);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Mockito.verify(mMainView).bindMetadata(gson.toJson(messageMetadata));
        Mockito.verify(mMainView).showMetadataAsList();
        mExtractMetadataObserver.onCompleted();
        Mockito.verify(mMainView).hideProgress();
        Mockito.verifyNoMoreInteractions(mMainView);
    }
}
