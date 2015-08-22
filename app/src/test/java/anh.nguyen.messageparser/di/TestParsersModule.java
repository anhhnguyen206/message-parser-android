package anh.nguyen.messageparser.di;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.mockito.Mockito;

import java.util.Arrays;

import javax.inject.Singleton;

import anh.nguyen.messageparser.common.DocumentWrapper;
import anh.nguyen.messageparser.parser.EmoticonParser;
import anh.nguyen.messageparser.parser.LinkParser;
import anh.nguyen.messageparser.parser.MentionParser;
import anh.nguyen.messageparser.parser.UrlParser;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
@Module(
        library = true,
        complete = false
)
public class TestParsersModule {
    @Provides
    @Singleton
    MentionParser provideMentionParser() {
        MentionParser mentionParser = Mockito.mock(MentionParser.class);
        Mockito.when(mentionParser.parse("@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"))
                .thenReturn(Arrays.asList("bob", "john"));
        return mentionParser;
    }

    @Provides
    @Singleton
    EmoticonParser provideEmoticonParser() {
        EmoticonParser emoticonParser = Mockito.mock(EmoticonParser.class);
        Mockito.when(emoticonParser.parse("@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"))
                .thenReturn(Arrays.asList("success"));
        return emoticonParser;
    }

    @Provides
    @Singleton
    UrlParser provideUrlParser() {
        UrlParser urlParser = Mockito.mock(UrlParser.class);
        Mockito.when(urlParser.parse("@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"))
                .thenReturn(Arrays.asList("https://twitter.com/jdorfman/status/430511497475670016"));
        return urlParser;
    }

    @Provides
    @Singleton
    DocumentWrapper provideDocumentWrapper() {
        DocumentWrapper documentWrapper = Mockito.mock(DocumentWrapper.class);
        Connection connection = Mockito.mock(Connection.class);
        Document document = Mockito.mock(Document.class);
        Mockito.when(document.title()).thenReturn("Twitter / jdorfman: nice @littlebigdetail from ...");
        try {
            Mockito.when(documentWrapper.connect("https://twitter.com/jdorfman/status/430511497475670016"))
                    .thenReturn(connection);
            Mockito.when(documentWrapper.get(connection))
                    .thenReturn(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Mockito.when(documentWrapper.getTitle(document))
                .thenCallRealMethod();
        return documentWrapper;
    }

    @Provides
    @Singleton
    LinkParser provideLinkParser(UrlParser urlParser, DocumentWrapper documentWrapper) {
        return new LinkParser(urlParser, documentWrapper);
    }
}
