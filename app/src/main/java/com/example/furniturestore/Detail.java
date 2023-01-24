package com.example.furniturestore;

public class Detail {
    private int number=0;
    String name_of_announcement, url_of_the_announcement_dimensions, url_of_the_announcement_image, price_of_announcement, type_of_announcement;
    String composition_of_announcement, durability_of_announcement,color_of_announcement,Email;
    Detail(){

    }

    public Detail(String name_of_announcement, String url_of_the_announcement_dimensions, String url_of_the_announcement_image, String price_of_announcement, String type_of_announcement, String composition_of_announcement, String durability_of_announcement, String color_of_announcement, String Email, int num) {
        this.name_of_announcement = name_of_announcement;
        this.url_of_the_announcement_dimensions = url_of_the_announcement_dimensions;
        this.url_of_the_announcement_image = url_of_the_announcement_image;
        this.price_of_announcement = price_of_announcement;
        this.type_of_announcement = type_of_announcement;
        this.composition_of_announcement = composition_of_announcement;
        this.durability_of_announcement = durability_of_announcement;
        this.color_of_announcement=color_of_announcement;
        this.Email=Email;
        this.number=num;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getColor_of_announcement() {
        return color_of_announcement;
    }

    public void setColor_of_announcement(String color_of_announcement) {
        this.color_of_announcement = color_of_announcement;
    }

    public String getName_of_announcement() {
        return name_of_announcement;
    }

    public String getPrice_of_announcement() {
        return price_of_announcement;
    }

    public String getType_of_announcement() {
        return type_of_announcement;
    }

    public String getComposition_of_announcement() {
        return composition_of_announcement;
    }

    public String getDurability_of_announcement() {
        return durability_of_announcement;
    }

    public String getUrl_of_the_announcement_dimensions() {
        return url_of_the_announcement_dimensions;
    }

    public void setName_of_announcement(String name_of_announcement) {
        this.name_of_announcement = name_of_announcement;
    }

    public void setUrl_of_the_announcement_dimensions(String url_of_the_announcement_dimensions) {
        this.url_of_the_announcement_dimensions = url_of_the_announcement_dimensions;
    }

    public void setUrl_of_the_announcement_image(String url_of_the_announcement_image) {
        this.url_of_the_announcement_image = url_of_the_announcement_image;
    }

    public void setPrice_of_announcement(String price_of_announcement) {
        this.price_of_announcement = price_of_announcement;
    }

    public void setType_of_announcement(String type_of_announcement) {
        this.type_of_announcement = type_of_announcement;
    }

    public void setComposition_of_announcement(String composition_of_announcement) {
        this.composition_of_announcement = composition_of_announcement;
    }

    public void setDurability_of_announcement(String durability_of_announcement) {
        this.durability_of_announcement = durability_of_announcement;
    }

    public String getUrl_of_the_announcement_image() {
        return url_of_the_announcement_image;
    }
}
