package com.quarkus.todo.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

@ApplicationScoped
public class DifferenceBetweenApplicationAndRequestScopeService {

    private List<String> listOfNames = new ArrayList<>();


    public void add(String name) {
        listOfNames.add(name);
    }

    public List<String> lists(){
        return listOfNames;
    }
}
