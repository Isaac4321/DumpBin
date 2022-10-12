package org.firstinspires.ftc.teamcode.autonomous.seq;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.components.ClawComponent;
import org.firstinspires.ftc.teamcode.components.DriveBaseComponent;
import org.firstinspires.ftc.teamcode.components.LifterComponent;

import java.util.ArrayList;
import java.util.List;

enum State {
    WAITING,
    RUNNING,
    FINISHED,
}

public class SequenceManager {

    private final Robot robot;
    private DriveBaseComponent driveBaseComponent;
    private LifterComponent lifterComponent;
    private ClawComponent clawComponent;

    private int cursor = 0;

    private ArrayList<Sequence> sequences;


    public SequenceManager(Robot robot) {
        this.robot = robot;
        this.sequences = new ArrayList<>();
        driveBaseComponent = new DriveBaseComponent(robot, true);
    }

    public void addSequences(List<Sequence> sequenceList) {
        for (Sequence seq : sequenceList) {
            sequences.add(seq);
        }
    }

    public void run() {
        for (Sequence sequence : sequences) {
            sequence.run();
            cursor++;
        }
    }

    public DriveBaseComponent getDriveBaseComponent() {
        return driveBaseComponent;
    }

    public String getStatus() {
        StringBuilder message = new StringBuilder();
        for (int x = 0; x < cursor; x++) {
            message.append("Sequence at index " + sequences.indexOf(sequences.get(x)) + " has finished.\n");
        }
        message.append("Sequence at index " + sequences.indexOf(sequences.get(cursor)) + " has finished.\n");
        for (int x = 0; x > cursor && x < sequences.size(); x++) {
            message.append("Sequence at index " + sequences.indexOf(sequences.get(x)) + " has not started.\n");
        }
        return message.toString();
    }



}
