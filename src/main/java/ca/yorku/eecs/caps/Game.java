package ca.yorku.eecs.caps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game
{
private CountryDB db;
public Game() {
    this.db = new CountryDB();
    }
public String qa() {
    List<String> capitals = new ArrayList<>();
    capitals = db.getCapitals();
    int k = capitals.size();
    int index = (int) (Math.random() * k);
    String a = capitals.get(index);
    Map <String, Country> map = new TreeMap<>();
    map = db.getData();
    Country cho = map.get(a);
    if (Math.random() < 0.5)
        return "What is the capital of " + cho.getName() + "\n" + cho.getCapital();
    else
        return cho.getCapital() + "  is the capital of " + "\n" + cho.getName();
}
}