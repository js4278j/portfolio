package com.example.mymemo.Data;

public class MemoData {

    private String id;
    private String category;
    private String title;
    private String subTitle;
    private String content;
    private String modifyDate;


    public MemoData(String id, String category, String title, String subTitle, String content, String modifyDate) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.modifyDate = modifyDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
