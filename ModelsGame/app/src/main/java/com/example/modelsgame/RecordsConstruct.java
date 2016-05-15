package com.example.modelsgame;

import java.io.Serializable;

/**
 * Created by Екатерина on 14.05.2016.
 */
public class RecordsConstruct implements Serializable {

    private int id;
    private String name;
    private String result;

    public RecordsConstruct (int id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    public int getID () {return id;}
    public String getName () {return name;}
    public String getResult () {return result;}
}