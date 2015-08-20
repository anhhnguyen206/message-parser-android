package anh.nguyen.messageparser.model;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
public class LinkItem extends MessageMetadataItem<Link> {
    private Link mLink;

    public LinkItem(Link link) {
        mLink = link;
    }

    @Override
    public int getType() {
        return LINK;
    }

    @Override
    public Link getValue() {
        return mLink;
    }
}
