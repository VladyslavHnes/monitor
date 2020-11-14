package com.eco.monitor.dto;

public class IncidentDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String imageUrl;
    private PlaceDto place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto placeDto) {
        this.place = placeDto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
