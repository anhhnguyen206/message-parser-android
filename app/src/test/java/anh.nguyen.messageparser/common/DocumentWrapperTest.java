package anh.nguyen.messageparser.common;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class DocumentWrapperTest {

    @Test
    public void getTitle_whenConnected_shouldGetTitle() {
        String actual = "Page title 1";

        Document mockedDocument = Mockito.mock(Document.class);
        Mockito.when(mockedDocument.title())
                .thenReturn("Page title 1");

        DocumentWrapper documentWrapper = new DocumentWrapper();
        String expected = documentWrapper.getTitle(mockedDocument);

        assertThat(actual, is(expected));
    }

    @Test
    public void getDocument_whenConnected_shouldGetDocument() throws Exception {
        Connection mockedConnection = Mockito.mock(Connection.class);
        Mockito.when(mockedConnection.get())
                .thenReturn(Mockito.mock(Document.class));

        DocumentWrapper documentWrapper = new DocumentWrapper();
        Document document = documentWrapper.get(mockedConnection);

        assertNotNull(document);
    }
}