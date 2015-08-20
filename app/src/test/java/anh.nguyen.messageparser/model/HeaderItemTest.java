package anh.nguyen.messageparser.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class HeaderItemTest {

    @Test
    public void getType_shouldReturnHeaderType() throws Exception {
        HeaderItem headerItem = new HeaderItem("Emoticon");
        assertThat(headerItem.getType(), is(MessageMetadataItem.HEADER));
    }

    @Test
    public void getValue_shouldReturnCorrectValueInConstructor() throws Exception {
        HeaderItem headerItem = new HeaderItem("Emoticon");
        assertThat(headerItem.getValue(), is("Emoticon"));
    }
}