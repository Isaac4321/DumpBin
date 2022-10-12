package org.firstinspires.ftc.teamcode.autonomous.seq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultiHashMap {
    private final Map<String, ArrayList<String>> hashMap = new HashMap<>();

    public void put(String key, String value) {
        ArrayList<String> tempList;
        if (hashMap.containsKey(key)) {
            tempList = hashMap.get(key);
            if (tempList == null)
                tempList = new ArrayList<>();
            tempList.add(value);
        } else {
            tempList = new ArrayList<>();
            tempList.add(value);
        }
        hashMap.put(key, tempList);
    }

    public Map<String, ArrayList<String>> getHashMap() {
        return hashMap;
    }
}