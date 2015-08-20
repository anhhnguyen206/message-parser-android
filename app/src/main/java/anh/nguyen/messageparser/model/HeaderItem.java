package anh.nguyen.messageparser.model;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class HeaderItem extends MessageMetadataItem<String> {
    private String mHeader;

    public HeaderItem(String header) {
        mHeader = header;
    }

    @Override
    int getType() {
        return 0;
    }

    @Override
    String getValue() {
        return mHeader;
    }
}
