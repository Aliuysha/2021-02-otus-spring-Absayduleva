package ru.otus.tests.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Question {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String text;
    @CsvBindByPosition(position = 2)
    private String answer;

    public Question() {}

    public Question(int id, String text, String answer) {
        this.id = id;
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }
}
