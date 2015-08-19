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
}
