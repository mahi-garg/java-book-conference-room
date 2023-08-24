package db;

import models.Building;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDataBase {

    Map< Integer, Building> db;

    private static final InMemoryDataBase Instance = new InMemoryDataBase();

    private InMemoryDataBase(){
        db =  new HashMap<>();
    }

    public static InMemoryDataBase getInstance(){
        return Instance;
    }

    public Map<Integer, Building> getDb(){
        return db;
    }
}
