package com.chatonx.chat.models;

/**
 * Created by Jacuzzy on 30/05/2016.
 */
public class VersionModel {
    public String name;

    public static final String[] data = {"Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

    VersionModel(String name) {
        this.name = name;
    }
}