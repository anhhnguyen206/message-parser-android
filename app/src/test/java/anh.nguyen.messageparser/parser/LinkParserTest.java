package anh.nguyen.messageparser.parser;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import anh.nguyen.messageparser.common.DocumentWrapper;
import anh.nguyen.messageparser.model.Link;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class LinkParserTest {
    @Test
    public void parse_NoUrl_ShouldReturnNoLink() throws IOException {
        String noUrlChatMessage = "Hello, world!";

        UrlParser mockedUrlParser = Mockito.mock(UrlParser.class);
        Mockito.when(mockedUrlParser.parse(noUrlChatMessage)).thenReturn(new ArrayList<String>());

        Document mockedDocumentTest1 = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest1.title()).thenReturn("test");

        Document mockedDocumentTest2 = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest2.title()).thenReturn("test2");

        DocumentWrapper mockedDocumentWrapper = Mockito.mock(DocumentWrapper.class);
        Connection mockedConnection1 = Mockito.mock(Connection.class);
        Mockito.when(mockedDocumentWrapper.connect("http://www.test.com")).thenReturn(mockedConnection1);
        Mockito.when(mockedConnection1.get()).thenReturn(mockedDocumentTest1);
        Connection mockedConnection2 = Mockito.mock(Connection.class);
        Mockito.when(mockedDocumentWrapper.connect("http://www.test2.com")).thenReturn(mockedConnection2);
        Mockito.when(mockedConnection1.get()).thenReturn(mockedDocumentTest2);
        Mockito.when(mockedDocumentWrapper.getTitle(Mockito.any(Document.class))).thenCallRealMethod();

        LinkParser linkParser = new LinkParser(mockedUrlParser, mockedDocumentWrapper);
        Observable<List<Link>> actual = linkParser.parse(noUrlChatMessage);

        TestSubscriber testSubscriber = new TestSubscriber();
        actual.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertNoValues();
    }

    @Test
    public void parse_UnreachableUrlChatMessage_ShouldHaveCauseInTitle() throws Exception {
        String oneUrlChatMessage = "Olympics are starting soon; http://www.nbcolympics.com";

        UrlParser mockedUrlParser = Mockito.mock(UrlParser.class);
        Mockito.when(mockedUrlParser.parse(oneUrlChatMessage)).thenReturn(Arrays.asList("http://www.nbcolympics.com"));

        Document mockedDocumentTest = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest.title()).thenReturn("NBC Olympics | 2014 NBC Olympics in Sochi Russia");

        DocumentWrapper mockedDocumentWrapper = Mockito.mock(DocumentWrapper.class);
        Connection mockedConnection = Mockito.mock(Connection.class);

        Mockito.when(mockedDocumentWrapper.connect("http://www.nbcolympics.com")).thenReturn(mockedConnection);
        Mockito.when(mockedDocumentWrapper.get(mockedConnection)).thenThrow(new Exception("Unable to retrieve the page title"));
        Mockito.when(mockedDocumentWrapper.getTitle(mockedDocumentTest)).thenCallRealMethod();

        LinkParser linkParser = new LinkParser(mockedUrlParser, mockedDocumentWrapper);
        List<Link> expect = new ArrayList<>(1);
        expect.add(new Link("http://www.nbcolympics.com", "Unable to retrieve the page title"));

        Observable<List<Link>> actual = linkParser.parse(oneUrlChatMessage);

        TestSubscriber testSubscriber = new TestSubscriber();
        actual.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(expect));

    }

    @Test
    public void parse_OneUrlChatMessage_ShouldReturnOneUrls() throws Exception {
        String oneUrlChatMessage = "Olympics are starting soon; http://www.nbcolympics.com";

        UrlParser mockedUrlParser = Mockito.mock(UrlParser.class);
        Mockito.when(mockedUrlParser.parse(oneUrlChatMessage)).thenReturn(Arrays.asList("http://www.nbcolympics.com"));

        Document mockedDocumentTest = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest.title()).thenReturn("NBC Olympics | 2014 NBC Olympics in Sochi Russia");

        DocumentWrapper mockedDocumentWrapper = Mockito.mock(DocumentWrapper.class);
        Connection mockedConnection = Mockito.mock(Connection.class);

        Mockito.when(mockedDocumentWrapper.connect("http://www.nbcolympics.com")).thenReturn(mockedConnection);
        Mockito.when(mockedDocumentWrapper.get(mockedConnection)).thenReturn(mockedDocumentTest);
        Mockito.when(mockedDocumentWrapper.getTitle(mockedDocumentTest)).thenCallRealMethod();

        LinkParser linkParser = new LinkParser(mockedUrlParser, mockedDocumentWrapper);
        List<Link> expect = new ArrayList<>(1);
        expect.add(new Link("http://www.nbcolympics.com", "NBC Olympics | 2014 NBC Olympics in Sochi Russia"));

        Observable<List<Link>> actual = linkParser.parse(oneUrlChatMessage);

        TestSubscriber testSubscriber = new TestSubscriber();
        actual.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(expect));
    }

    @Test
    public void parse_TwoUrlChatMessages_ShouldReturnTwoUrls() throws Exception {
        String twoUrlChatMessage = "@bob @john (success) such a cool feature; " +
                "https://twitter.com/jdorfman/status/430511497475670016, also Olympics are starting soon; http://www.nbcolympics.com";

        UrlParser mockedUrlParser = Mockito.mock(UrlParser.class);
        Mockito.when(mockedUrlParser.parse(twoUrlChatMessage)).thenReturn(Arrays.asList("https://twitter.com/jdorfman/status/430511497475670016", "http://www.nbcolympics.com"));

        Document mockedDocumentTest1 = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest1.title()).thenReturn("Twitter / jdorfman: nice @littlebigdetail from ...");

        Document mockedDocumentTest2 = Mockito.mock(Document.class);
        Mockito.when(mockedDocumentTest2.title()).thenReturn("NBC Olympics | 2014 NBC Olympics in Sochi Russia");

        Connection mockedConnection1 = Mockito.mock(Connection.class);
        Mockito.when(mockedConnection1.get()).thenReturn(mockedDocumentTest1);

        Connection mockedConnection2 = Mockito.mock(Connection.class);
        Mockito.when(mockedConnection2.get()).thenReturn(mockedDocumentTest2);

        DocumentWrapper mockedDocumentWrapper = Mockito.mock(DocumentWrapper.class);

        Mockito.when(mockedDocumentWrapper.connect("https://twitter.com/jdorfman/status/430511497475670016")).thenReturn(mockedConnection1);
        Mockito.when(mockedDocumentWrapper.get(mockedConnection1)).thenReturn(mockedDocumentTest1);
        Mockito.when(mockedDocumentWrapper.getTitle(mockedDocumentTest1)).thenCallRealMethod();

        Mockito.when(mockedDocumentWrapper.connect("http://www.nbcolympics.com")).thenReturn(mockedConnection2);
        Mockito.when(mockedDocumentWrapper.get(mockedConnection2)).thenReturn(mockedDocumentTest2);
        Mockito.when(mockedDocumentWrapper.getTitle(mockedDocumentTest2)).thenCallRealMethod();

        LinkParser linkParser = new LinkParser(mockedUrlParser, mockedDocumentWrapper);
        List<Link> expect = new ArrayList<>(2);
        expect.add(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Twitter / jdorfman: nice @littlebigdetail from ..."));
        expect.add(new Link("http://www.nbcolympics.com", "NBC Olympics | 2014 NBC Olympics in Sochi Russia"));

        Observable<List<Link>> actual = linkParser.parse(twoUrlChatMessage);

        TestSubscriber testSubscriber = new TestSubscriber();
        actual.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(expect));
    }
}
