package com.example.final_project2;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String category;
    private String name;
    private String author;
    private String release;
    private int pages_number;
    private byte[] image;
    private boolean isFav;

    public Book(String name, int pages_number) {
        this.name = name;
        this.pages_number = pages_number;
    }

    public Book(String name, String author, String release, int pages_number, String category) {
        this.name = name;
        this.author = author;
        this.release = release;
        this.pages_number = pages_number;
        this.category = category;

    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getPages_number() {
        return pages_number;
    }

    public void setPages_number(int pages_number) {
        this.pages_number = pages_number;
    }
}
