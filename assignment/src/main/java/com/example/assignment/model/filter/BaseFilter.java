package com.example.assignment.model.filter;

public class BaseFilter {
    private int limit;
    private int page;
    private String orderBy;

    public int getPage() {
        if (page < 0)
            return 0;
        else return this.page;
    }

    public int getLimit() {
        if (limit < 0)
            return 250;
        else return this.limit;
    }
}
