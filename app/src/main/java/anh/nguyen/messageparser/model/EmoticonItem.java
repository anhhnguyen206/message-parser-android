package anh.nguyen.messageparser.model;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class EmoticonItem extends MessageMetadataItem<String> {
    private String mEmoticon;

    public EmoticonItem(String emoticon) {
        mEmoticon = emoticon;
    }

    @Override
    public int getType() {
        return MessageMetadataItem.EMOTICON;
    }

    @Override
    public String getValue() {
        return mEmoticon;
    }
}
