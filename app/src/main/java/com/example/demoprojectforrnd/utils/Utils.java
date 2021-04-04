package com.example.demoprojectforrnd.utils;

import com.example.demoprojectforrnd.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Model> getModelItemList() {
        List<Model> modelList = new ArrayList<>();

        modelList.add(new Model("One","https://images.unsplash.com/photo-1611095777904-271a798ed635?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1351&q=80"));
        modelList.add(new Model("Two","https://images.unsplash.com/photo-1612832164066-305667c23a01?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1350&q=80"));
        modelList.add(new Model("Three","https://images.unsplash.com/photo-1593642532871-8b12e02d091c?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1300&q=80"));
        modelList.add(new Model("Four","https://images.unsplash.com/photo-1616149562349-ded7f2834a01?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=701&q=80"));

        return modelList;
    }

}
