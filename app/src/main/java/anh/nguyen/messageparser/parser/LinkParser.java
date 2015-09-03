package anh.nguyen.messageparser.parser;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import anh.nguyen.messageparser.common.DocumentWrapper;
import anh.nguyen.messageparser.model.Link;
import rx.Observable;
import rx.Subscriber;
import rx.functions.FuncN;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class LinkParser implements Parser<Observable<List<Link>>> {
    private UrlParser mUrlParser;
    private DocumentWrapper mDocumentWrapper;

    public LinkParser(UrlParser urlParser, DocumentWrapper documentWrapper) {
        mUrlParser = urlParser;
        mDocumentWrapper = documentWrapper;
    }

    /**
     * Return a list of Link objects given a chat message as input
     *
     * @param message
     * @return List of Link objects
     */
    @Override
    public Observable<List<Link>> parse(String message) {
        List<String> urls = mUrlParser.parse(message);
        final List<Link> links = new ArrayList<>();

        if (urls.size() == 0) {
            return Observable.just(links);
        }

        List<Observable<Link>> linkObservables = new ArrayList<>();
        for (final String url : urls) {
            Observable<Link> linkObservable = Observable.create(new Observable.OnSubscribe<Link>() {
                @Override
                public void call(Subscriber<? super Link> subscriber) {
                    try {
                        Connection connection = mDocumentWrapper.connect(url);
                        Document document = mDocumentWrapper.get(connection);
                        Link link = new Link(url, mDocumentWrapper.getTitle(document));
                        subscriber.onNext(link);
                    } catch (Exception e) {
                        // unable to retrieve the page title, replacing title with the cause
                        Link link = new Link(url, e.getMessage());
                        subscriber.onNext(link);
                    }

                    subscriber.onCompleted();
                }
            });

            linkObservables.add(linkObservable);
        }

        return Observable.zip(linkObservables, new FuncN<List<Link>>() {
            @Override
            public List<Link> call(Object... args) {
                List<Link> links = new ArrayList<Link>();
                for (Object emitted : args) {
                    links.add((Link) emitted);
                }

                return links;
            }
        });
    }
}
