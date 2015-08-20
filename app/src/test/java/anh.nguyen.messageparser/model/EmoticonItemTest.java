package anh.nguyen.messageparser.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class EmoticonItemTest {

    @Test
    public void getType_shouldReturnEmoticonType() throws Exception {
        EmoticonItem emoticonItem = new EmoticonItem("success");
        assertThat(emoticonItem.getType(), is(MessageMetadataItem.EMOTICON));
    }

    @Test
    public void getValue_shouldReturnCorrectValueInConstructor() throws Exception {
        EmoticonItem emoticonItem = new EmoticonItem("success");
        assertThat(emoticonItem.getValue(), is("success"));
    }
}