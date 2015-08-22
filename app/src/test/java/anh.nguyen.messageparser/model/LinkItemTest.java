package anh.nguyen.messageparser.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class LinkItemTest {

    @Test
    public void getType_shouldReturnLinkType() throws Exception {
        LinkItem linkItem = new LinkItem(new Link("url", "title"));
        assertThat(linkItem.getType(), is(MessageMetadataItem.LINK));
    }

    @Test
    public void getValue_shouldReturnCorrectValueInConstructor() throws Exception {
        LinkItem linkItem = new LinkItem(new Link("url", "title"));
        assertThat(linkItem.getValue().getUrl(), is("url"));
        assertThat(linkItem.getValue().getTitle(), is("title"));
    }
}