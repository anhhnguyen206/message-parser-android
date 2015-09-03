package anh.nguyen.messageparser.interactor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.parser.EmoticonParser;
import anh.nguyen.messageparser.parser.LinkParser;
import anh.nguyen.messageparser.parser.MentionParser;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

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
    private Scheduler mSubscribeOnScheduler;
    private Scheduler mObserveOnScheduler;

    @Inject
    public ExtractMetadataInteractorImpl(MentionParser mentionParser, EmoticonParser emoticonParser, LinkParser linkParser, @Named("io-scheduler") Scheduler subscribeOnScheduler, @Named("ui-scheduler") Scheduler observeOnScheduler) {
        mMentionParser = mentionParser;
        mEmoticonParser = emoticonParser;
        mLinkParser = linkParser;
        mSubscribeOnScheduler = subscribeOnScheduler;
        mObserveOnScheduler = observeOnScheduler;
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
            public void call(final Subscriber<? super MessageMetadata> subscriber) {
                final MessageMetadata messageMetadata = new MessageMetadata();
                // parse mentions and add to the messageMetadata obj
                messageMetadata.setMentions(mMentionParser.parse(message));
                // parse emoticons and add to the messageMetadata obj
                messageMetadata.setEmoticons(mEmoticonParser.parse(message));
                // parse links and add to the messageMetadata obj
                mLinkParser.parse(message).subscribeOn(mSubscribeOnScheduler)
                        .observeOn(mObserveOnScheduler)
                        .doOnCompleted(new Action0() {
                            @Override
                            public void call() {
                                subscriber.onNext(messageMetadata);
                                subscriber.onCompleted();
                            }
                        })
                        .doOnNext(new Action1<List<Link>>() {
                            @Override
                            public void call(List<Link> links) {
                                messageMetadata.setLinks(links);
                            }
                        })
                        .subscribe();
            }
        });
    }
}
