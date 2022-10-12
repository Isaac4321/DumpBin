package org.firstinspires.ftc.teamcode.autonomous.seq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sequence {

    private final Set<String> COMMAND_NAMES = new HashSet<>(Arrays.asList("drive", "strafe", "rotate"));

    private SequenceManager sequenceManager;
    private MultiHashMap multiHashMap;


    public Sequence(SequenceManager sequenceManager) {
        this.sequenceManager = sequenceManager;
        this.multiHashMap = new MultiHashMap();

    }

    public void addCommand(String key, String value) {
        multiHashMap.put(key, value);
    }

    public void run() {
        for (String key : multiHashMap.getHashMap().keySet()) {
            ArrayList<String> keyValues = multiHashMap.getHashMap().get(key);
            for (String values : keyValues) {
                doCommand(key, values);
            }
        }
    }

    private void doCommand(String command, String value) {
        if (COMMAND_NAMES.contains(command)) {
            String[] values = value.split(",");
            switch (command) {
                case "drive":
                    sequenceManager.getDriveBaseComponent()
                            .drive(Integer.parseInt(values[0]), Double.parseDouble(values[1]));
                    break;
                case "strafe":
                    sequenceManager.getDriveBaseComponent()
                            .strafe(Integer.parseInt(values[0]), Double.parseDouble(values[1]));
                    break;
                case "rotate":
                    sequenceManager.getDriveBaseComponent()
                            .rotate(Integer.parseInt(values[0]), Double.parseDouble(values[1]));
                    break;
            }
        }
    }

}
