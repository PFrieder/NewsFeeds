package com.example.pinchasfrieder.newsfeeds.LoadFeeds;

/**
 * Created by Pinchas Frieder on 11/16/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "title",
        "link",
        "author",
        "publishedDate",
        "contentSnippet",
        "content",
        "categories"
})
public class Entry {

    @JsonProperty("title")
    private String title;
    @JsonProperty("link")
    private String link;
    @JsonProperty("author")
    private String author;
    @JsonProperty("publishedDate")
    private String publishedDate;
    @JsonProperty("contentSnippet")
    private String contentSnippet;
    @JsonProperty("content")
    private String content;
    @JsonProperty("categories")
    private List<Object> categories = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The link
     */
    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The publishedDate
     */
    @JsonProperty("publishedDate")
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     *
     * @param publishedDate
     * The publishedDate
     */
    @JsonProperty("publishedDate")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     *
     * @return
     * The contentSnippet
     */
    @JsonProperty("contentSnippet")
    public String getContentSnippet() {
        return contentSnippet;
    }

    /**
     *
     * @param contentSnippet
     * The contentSnippet
     */
    @JsonProperty("contentSnippet")
    public void setContentSnippet(String contentSnippet) {
        this.contentSnippet = contentSnippet;
    }

    /**
     *
     * @return
     * The content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The categories
     */
    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     * The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
