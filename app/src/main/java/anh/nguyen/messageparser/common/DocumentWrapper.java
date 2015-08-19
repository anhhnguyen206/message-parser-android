package anh.nguyen.messageparser.common;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * A wrapper for jsoup in order to mock and test.
 */
public class DocumentWrapper {
    private static int TIMEOUT = 5000;

    public Connection connect(String url) {
        return Jsoup.connect(url).timeout(TIMEOUT);
    }

    /**
     * Get the document given a Connection as an input.  Throws exception if
     * the response of connection is a failure.
     * @param connection
     * @return
     * @throws Exception
     */
    public Document get(Connection connection) throws Exception {
        if (connection.response() != null &&
                connection.response().statusCode() > 400) {
            throw new Exception(connection.response().statusMessage());
        }

        return connection.get();
    }

    /**
     * Return the title of the Jsoup Document given the Document as input
     * @param document
     * @return
     */
    public String getTitle(Document document) {
        return document.title();
    }
}
