package anh.nguyen.messageparser.model;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class MentionItem extends MessageMetadataItem<String> {
    private String mMention;

    public MentionItem(String mention) {
        mMention = mention;
    }

    @Override
    int getType() {
        return MessageMetadataItem.MENTION;
    }

    @Override
    String getValue() {
        return mMention;
    }
}
