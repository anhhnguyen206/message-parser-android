package anh.nguyen.messageparser.common;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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

    @Test(expected = Exception.class)
    public void getDocument_404_shouldThrowException() throws Exception {
        Connection mockedConnection = Mockito.mock(Connection.class);
        Connection.Response mockedResponse = Mockito.mock(Connection.Response.class);

        Mockito.when(mockedConnection.response())
                .thenReturn(mockedResponse);

        Mockito.when(mockedResponse.statusCode())
                .thenReturn(404);

        Mockito.when(mockedResponse.statusMessage())
                .thenReturn("Page not found");

        DocumentWrapper documentWrapper = new DocumentWrapper();
        documentWrapper.get(mockedConnection);
    }
}