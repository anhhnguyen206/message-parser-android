package anh.nguyen.messageparser.interactor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.parser.EmoticonParser;
import anh.nguyen.messageparser.parser.LinkParser;
import anh.nguyen.messageparser.parser.MentionParser;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class ExtractMetadataInteractorImpl implements ExtractMetadataInteractor {
    private MentionParser mMentionParser;
    private EmoticonParser mEmoticonParser;
    private LinkParser mLinkParser;

    @Inject
    public ExtractMetadataInteractorImpl(MentionParser mentionParser, EmoticonParser emoticonParser, LinkParser linkParser) {
        mMentionParser = mentionParser;
        mEmoticonParser = emoticonParser;
        mLinkParser = linkParser;
    }

    @Override
    public Observable<MessageMetadata> execute(final String message) {
        return Observable.create(new Observable.OnSubscribe<MessageMetadata>() {
            @Override
            public void call(Subscriber<? super MessageMetadata> subscriber) {
                MessageMetadata messageMetadata = new MessageMetadata();
                messageMetadata.setMentions(mMentionParser.parse(message));
                messageMetadata.setEmoticons(mEmoticonParser.parse(message));
                messageMetadata.setLinks(mLinkParser.parse(message));

                subscriber.onNext(messageMetadata);
                subscriber.onCompleted();
            }
        });
    }
}
