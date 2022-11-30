package com.example.bug;

import java.util.List;

public class Book
{
    String author;

    List<AbstractAuthor> authors;

    public List<AbstractAuthor> getAuthors()
    {
        return authors;
    }

    public void setAuthors( List<AbstractAuthor> authors )
    {
        this.authors = authors;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }
}
