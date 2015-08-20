package anh.nguyen.messageparser.model;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public abstract class MessageMetadataItem<T> {
    public static final int HEADER = 0;
    public static final int MENTION = 1;
    public static final int EMOTICON = 2;
    public static final int LINK = 3;
    public abstract int getType();
    public abstract T getValue();
}
