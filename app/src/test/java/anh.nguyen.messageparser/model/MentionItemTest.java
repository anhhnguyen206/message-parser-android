package anh.nguyen.messageparser.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class MentionItemTest {

    @Test
    public void getType_shouldReturnMentionType() throws Exception {
        MentionItem mentionItem = new MentionItem("bob");
        assertThat(mentionItem.getType(), is(MessageMetadataItem.MENTION));
    }

    @Test
    public void getValue_shouldReturnCorrectValueInConstructor() throws Exception {
        MentionItem mentionItem = new MentionItem("bob");
        assertThat(mentionItem.getValue(), is("bob"));
    }
}