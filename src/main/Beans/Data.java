package main.Beans;

import java.sql.Date;

/**
 * Created by lee on 2017/9/1.
 *
 * @author: lee
 * create time: 下午1:50
 */
public class Data {
    public String URL;

    public String sites;

    public String DataID;

    public String Title;

    public String Author;

    public Date CrawlDate;

    public Date PublishDate;

    public String Content;

    public String SourceIntelID;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public String getDataID() {
        return DataID;
    }

    public void setDataID(String dataID) {
        DataID = dataID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Date getCrawlDate() {
        return CrawlDate;
    }

    public void setCrawlDate(Date crawlDate) {
        CrawlDate = crawlDate;
    }

    public Date getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(Date publishDate) {
        PublishDate = publishDate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSourceIntelID() {
        return SourceIntelID;
    }

    public void setSourceIntelID(String sourceIntelID) {
        SourceIntelID = sourceIntelID;
    }
}
