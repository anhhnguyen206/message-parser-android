package anh.nguyen.messageparser.parser;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import anh.nguyen.messageparser.common.DocumentWrapper;
import anh.nguyen.messageparser.model.Link;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class LinkParser implements Parser<List<Link>> {
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
    public List<Link> parse(String message) {
        List<String> urls = mUrlParser.parse(message);
        List<Link> links = new ArrayList<>();

        for (String url : urls) {
            try {
                Connection connection = mDocumentWrapper.connect(url);
                Document document = mDocumentWrapper.get(connection);
                Link link = new Link(url, mDocumentWrapper.getTitle(document));
                links.add(link);
            } catch (Exception e) {
                // unable to retrieve the page title, replacing title with the cause
                Link link = new Link(url, e.getMessage());
                links.add(link);
            }
        }

        return links;
    }
}
