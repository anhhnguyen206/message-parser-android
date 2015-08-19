package anh.nguyen.messageparser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class MessageMetadata {
    @SerializedName("mentions")
    private List<String> mMentions;
    @SerializedName("emoticons")
    private List<String> mEmoticons;
    @SerializedName("links")
    private List<Link> mLinks;

    public List<String> getMentions() {
        return mMentions;
    }

    public void setMentions(List<String> mentions) {
        mMentions = mentions;
    }

    public List<String> getEmoticons() {
        return mEmoticons;
    }

    public void setEmoticons(List<String> emoticons) {
        mEmoticons = emoticons;
    }

    public List<Link> getLinks() {
        return mLinks;
    }

    public void setLinks(List<Link> links) {
        mLinks = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageMetadata)) return false;

        MessageMetadata that = (MessageMetadata) o;

        if (getMentions() != null ? !getMentions().equals(that.getMentions()) : that.getMentions() != null)
            return false;
        if (getEmoticons() != null ? !getEmoticons().equals(that.getEmoticons()) : that.getEmoticons() != null)
            return false;
        return !(getLinks() != null ? !getLinks().equals(that.getLinks()) : that.getLinks() != null);

    }

    @Override
    public int hashCode() {
        int result = getMentions() != null ? getMentions().hashCode() : 0;
        result = 31 * result + (getEmoticons() != null ? getEmoticons().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        return result;
    }
}
