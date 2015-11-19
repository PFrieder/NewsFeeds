package com.example.pinchasfrieder.newsfeeds.LoadFeeds;

/**
 * Created by Pinchas Frieder on 11/16/2015.
 */
import java.util.HashMap;
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
        "feed"
})
public class ResponseData {

    @JsonProperty("feed")
    private Feed feed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The feed
     */
    @JsonProperty("feed")
    public Feed getFeed() {
        return feed;
    }

    /**
     *
     * @param feed
     * The feed
     */
    @JsonProperty("feed")
    public void setFeed(Feed feed) {
        this.feed = feed;
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


