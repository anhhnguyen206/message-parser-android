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
    int getType() {
        return LINK;
    }

    @Override
    Link getValue() {
        return mLink;
    }
}
