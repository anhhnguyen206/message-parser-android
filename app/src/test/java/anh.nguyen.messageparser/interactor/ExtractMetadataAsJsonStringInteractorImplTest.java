package anh.nguyen.messageparser.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jsoup.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import javax.inject.Inject;

import anh.nguyen.messageparser.common.DocumentWrapper;
import anh.nguyen.messageparser.di.TestExtractMetadataAsJsonStringInteractorModule;
import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.parser.LinkParser;
import dagger.ObjectGraph;
import rx.observers.TestSubscriber;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class ExtractMetadataAsJsonStringInteractorImplTest {

    @Inject
    ExtractMetadataAsJsonStringInteractor mExtractMetadataAsJsonStringInteractor;
    @Inject
    LinkParser mLinkParser;
    @Inject
    DocumentWrapper mDocumentWrapper;

    @Before
    public void setUp() throws Exception {
        ObjectGraph.create(new TestExtractMetadataAsJsonStringInteractorModule()).inject(this);
    }

    @After
    public void tearDown() throws Exception {
        mExtractMetadataAsJsonStringInteractor = null;
    }

    @Test
    public void excute_success() throws Exception {
        String message = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";
        MessageMetadata expectMessageMetadata = new MessageMetadata();
        expectMessageMetadata.setMentions(Arrays.asList("bob", "john"));
        expectMessageMetadata.setEmoticons(Arrays.asList("success"));
        expectMessageMetadata.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Twitter / jdorfman: nice @littlebigdetail from ...")));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String expect = gson.toJson(expectMessageMetadata);

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        mExtractMetadataAsJsonStringInteractor.execute(message)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(expect));
    }

    @Test
    public void execute_fail() throws Exception {
        String message = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";
        Mockito.when(mDocumentWrapper.get(Mockito.any(Connection.class))).thenThrow(new Exception("Error message"));

        MessageMetadata expectMessageMetadata = new MessageMetadata();
        expectMessageMetadata.setMentions(Arrays.asList("bob", "john"));
        expectMessageMetadata.setEmoticons(Arrays.asList("success"));
        expectMessageMetadata.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Error message")));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String expect = gson.toJson(expectMessageMetadata);

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        mExtractMetadataAsJsonStringInteractor.execute(message)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(expect));


    }
}
