package com.example.simplenotes;

public class Note {
    private String fileName;
    private String title;

    public Note(String title) {
        this(title, title);
    }

    public Note(String fileName, String title) {
        this.fileName = fileName;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
