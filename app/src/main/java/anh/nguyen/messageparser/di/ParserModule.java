package anh.nguyen.messageparser.di;

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
public class ParserModule {
    @Provides
    @Singleton
    MentionParser provideMentionParser() {
        return new MentionParser();
    }

    @Provides
    @Singleton
    EmoticonParser provideEmoticonParser() {
        return new EmoticonParser();
    }

    @Provides
    @Singleton
    UrlParser provideUrlParser() {
        return new UrlParser();
    }

    @Provides
    @Singleton
    DocumentWrapper provideDocumentWrapper() {
        return new DocumentWrapper();
    }

    @Provides
    @Singleton
    LinkParser provideLinkParser(UrlParser urlParser, DocumentWrapper documentWrapper) {
        return new LinkParser(urlParser, documentWrapper);
    }
}
