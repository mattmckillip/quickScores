package com.example.matt.movieWatchList.Models.POJO;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultiSearchResult {

    @SerializedName("backdrop_path")
    @Expose
    private Object backdropPath;
    @SerializedName("first_air_date")
    @Expose
    private Object firstAirDate;
    @SerializedName("genre_ids")
    @Expose
    private List<Object> genreIds = new ArrayList<Object>();
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("overview")
    @Expose
    private Object overview;
    @SerializedName("origin_country")
    @Expose
    private List<Object> originCountry = new ArrayList<Object>();
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;

    /**
     * 
     * @return
     *     The backdropPath
     */
    public Object getBackdropPath() {
        return backdropPath;
    }

    /**
     * 
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(Object backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     * 
     * @return
     *     The firstAirDate
     */
    public Object getFirstAirDate() {
        return firstAirDate;
    }

    /**
     * 
     * @param firstAirDate
     *     The first_air_date
     */
    public void setFirstAirDate(Object firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    /**
     * 
     * @return
     *     The genreIds
     */
    public List<Object> getGenreIds() {
        return genreIds;
    }

    /**
     * 
     * @param genreIds
     *     The genre_ids
     */
    public void setGenreIds(List<Object> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * 
     * @param originalLanguage
     *     The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     * 
     * @return
     *     The originalName
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 
     * @param originalName
     *     The original_name
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public Object getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(Object overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The originCountry
     */
    public List<Object> getOriginCountry() {
        return originCountry;
    }

    /**
     * 
     * @param originCountry
     *     The origin_country
     */
    public void setOriginCountry(List<Object> originCountry) {
        this.originCountry = originCountry;
    }

    /**
     * 
     * @return
     *     The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * 
     * @param posterPath
     *     The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     * 
     * @return
     *     The popularity
     */
    public Double getPopularity() {
        return popularity;
    }

    /**
     * 
     * @param popularity
     *     The popularity
     */
    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     * 
     * @param voteAverage
     *     The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    /**
     * 
     * @return
     *     The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * 
     * @param voteCount
     *     The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * 
     * @return
     *     The mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * 
     * @param mediaType
     *     The media_type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * 
     * @return
     *     The adult
     */
    public Boolean getAdult() {
        return adult;
    }

    /**
     * 
     * @param adult
     *     The adult
     */
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

}
