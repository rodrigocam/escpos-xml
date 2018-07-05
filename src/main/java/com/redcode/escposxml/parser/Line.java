package com.redcode.escposxml.parser;

public class Line {

    private String id;

    private String align;

    private String font;

    private String cut;

    private Integer feed;

    private String text;

    public String getAlign() {
        return this.align;
    }

    public String getId() {
        return this.id;
    }

    public String getFont() {
        return this.font;
    }

    public String getCut() {
        return this.cut;
    }

    public Integer getFeed() {
        return this.feed;
    }

    public String getText() {
        return this.text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
