package anh.nguyen.messageparser.interactor;

import javax.inject.Inject;

import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.parser.EmoticonParser;
import anh.nguyen.messageparser.parser.LinkParser;
import anh.nguyen.messageparser.parser.MentionParser;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * A self-contained piece of business logic that
 * perform the parsing operation given a string message
 * as an input.
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

    /**
     * Given an input string
     * Perform the parsing logic inside an Observable
     * Immediately return the Observable
     *
     * @param message
     * @return Observable<MessageMetadata>
     */
    @Override
    public Observable<MessageMetadata> execute(final String message) {
        return Observable.create(new Observable.OnSubscribe<MessageMetadata>() {
            @Override
            public void call(Subscriber<? super MessageMetadata> subscriber) {
                MessageMetadata messageMetadata = new MessageMetadata();
                // parse mentions and add to the messageMetadata obj
                messageMetadata.setMentions(mMentionParser.parse(message));
                // parse emoticons and add to the messageMetadata obj
                messageMetadata.setEmoticons(mEmoticonParser.parse(message));
                // parse links and add to the messageMetadata obj
                messageMetadata.setLinks(mLinkParser.parse(message));

                subscriber.onNext(messageMetadata);
                subscriber.onCompleted();
            }
        });
    }
}
