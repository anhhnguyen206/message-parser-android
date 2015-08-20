package anh.nguyen.messageparser.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.model.MessageMetadataItem;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by nguyenhoanganh on 8/21/15.
 */
public class MessageMetadataConverterTest {

    @Test
    public void convert_shouldReturnCorrectMessageMetadataItems() throws Exception {
        MessageMetadata messageMetadata = new MessageMetadata();
        messageMetadata.setMentions(Arrays.asList("bob", "john"));
        messageMetadata.setEmoticons(Arrays.asList("success"));
        messageMetadata.setLinks(Arrays.asList(new Link("https://twitter.com/jdorfman/status/430511497475670016", "Twitter / jdorfman: nice @littlebigdetail from ...")));

        MessageMetadataConverter messageMetadataConverter = new MessageMetadataConverter();
        List<MessageMetadataItem> actual = messageMetadataConverter.convert(messageMetadata);

        assertThat(actual.get(0).getType(), is(MessageMetadataItem.HEADER));
        assertThat(actual.get(1).getType(), is(MessageMetadataItem.MENTION));
        assertThat(actual.get(2).getType(), is(MessageMetadataItem.MENTION));
        assertThat(actual.get(3).getType(), is(MessageMetadataItem.HEADER));
        assertThat(actual.get(4).getType(), is(MessageMetadataItem.EMOTICON));
        assertThat(actual.get(5).getType(), is(MessageMetadataItem.HEADER));
        assertThat(actual.get(6).getType(), is(MessageMetadataItem.LINK));
    }
}