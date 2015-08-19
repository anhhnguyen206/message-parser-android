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
}
