package com.example.simplenotes.other;

public class Note {
    private String name;
    private String fileName;

    public Note(String name) {
        this(name, name);
    }

    public Note(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    //** Methods ****************************************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
