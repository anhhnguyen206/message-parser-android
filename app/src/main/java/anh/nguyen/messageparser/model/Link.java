package anh.nguyen.messageparser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class Link {
    @SerializedName("url")
    private String mUrl;
    @SerializedName("title")
    private String mTitle;

    public Link(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link link = (Link) o;

        if (getUrl() != null ? !getUrl().equals(link.getUrl()) : link.getUrl() != null)
            return false;
        return !(getTitle() != null ? !getTitle().equals(link.getTitle()) : link.getTitle() != null);

    }

    @Override
    public int hashCode() {
        int result = getUrl() != null ? getUrl().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }
}
