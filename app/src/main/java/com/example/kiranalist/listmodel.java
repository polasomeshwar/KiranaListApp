package com.example.kiranalist;

public class listmodel {
    private int id;
    private String title;
    private String list;

    public listmodel(String title, String list) {
        this.title = title;
        this.list = list;
    }

    public listmodel(int id, String title, String list) {
        this.id = id;
        this.title = title;
        this.list = list;
    }
    public listmodel()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
