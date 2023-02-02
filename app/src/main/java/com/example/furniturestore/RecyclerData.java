package com.example.furniturestore;

public class RecyclerData {
    // string for displaying
    // title and description.
    private String title;
    private String description;

    // constructor for our title and description.
    public RecyclerData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // creating getter and setter methods.
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
}
