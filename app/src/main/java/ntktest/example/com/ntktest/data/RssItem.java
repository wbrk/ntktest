package ntktest.example.com.ntktest.data;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false, name = "item")
public class RssItem {
    @NonNull
    @Element(required = false, data = true)
    private String title = "";

    @NonNull
    @Element(required = false, data = true)
    private String description = "";

    @NonNull
    @Element(required = false)
    private String link = "";

    @NonNull
    @Element(required = false)
    private String pubDate = "";

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getLink() {
        return link;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getPubDate() {
        return pubDate;
    }

    @NonNull
    public String toString() {
        return "RssItem { "
                + "title=" + title + ", "
                + "link=" + link + " }";
    }
}
