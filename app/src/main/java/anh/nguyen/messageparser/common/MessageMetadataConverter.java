package anh.nguyen.messageparser.common;

import java.util.ArrayList;
import java.util.List;

import anh.nguyen.messageparser.model.EmoticonItem;
import anh.nguyen.messageparser.model.HeaderItem;
import anh.nguyen.messageparser.model.Link;
import anh.nguyen.messageparser.model.LinkItem;
import anh.nguyen.messageparser.model.MentionItem;
import anh.nguyen.messageparser.model.MessageMetadata;
import anh.nguyen.messageparser.model.MessageMetadataItem;

/**
 * Created by nguyenhoanganh on 8/20/15.
 * Used to convert a MessageMetadata object to a
 * list of MessageMetadataItem which in turns would be used
 * in MetadataAdapter
 */
public class MessageMetadataConverter {
    public List<MessageMetadataItem> convert(MessageMetadata messageMetadata) {
        List<MessageMetadataItem> messageMetadataItems = new ArrayList<>();

        messageMetadataItems.add(new HeaderItem("Mentions"));
        for (String mention : messageMetadata.getMentions()) {
            messageMetadataItems.add(new MentionItem(mention));
        }

        messageMetadataItems.add(new HeaderItem("Emoticons"));
        for (String emoticon : messageMetadata.getEmoticons()) {
            messageMetadataItems.add(new EmoticonItem(emoticon));
        }

        messageMetadataItems.add(new HeaderItem("Links"));
        for (Link link : messageMetadata.getLinks()) {
            messageMetadataItems.add(new LinkItem(link));
        }

        return messageMetadataItems;
    }
}
