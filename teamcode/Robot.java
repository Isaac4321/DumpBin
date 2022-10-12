package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.Component;
import java.util.ArrayList;


public final class Robot {

    private final HardwareMap hardwareMap;

    private final Gamepad controller1;
    private final Gamepad controller2;

    private final ArrayList<Component> components = new ArrayList<>();

    public Robot(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        controller1 = opMode.gamepad1;
        controller2 = opMode.gamepad2;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public ArrayList<Component> components() {
        return this.components;
    }

    public HardwareMap hardwareMap() {
        return hardwareMap;
    }

    public Gamepad controller1() {
        return controller1;
    }

    public Gamepad controller2() {
        return controller2;
    }





}
