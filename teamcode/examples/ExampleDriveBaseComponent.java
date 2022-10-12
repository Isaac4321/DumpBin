package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.components.ComponentBase;

/**
 * This file contains an example of a mecanum style Drive-base component,
 * hopefully teaches programmers how to make a component in our current design structure.
 */
public class ExampleDriveBaseComponent extends ComponentBase {

    //Define constants up here!
    private final double MOTOR_SPEED_MULT = 0.50;

    //Define component hardware here!
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    //Define other variables here!
    Gamepad controller1;

    //Must have constructor defined as follows (feel free to add stuff past the super(robot) statement)
    public ExampleDriveBaseComponent(Robot robot) {
        super(robot);
    }

    /*
        The method which calls the 'command' method i.e drive, move etc...
        Add event listening here!
     */
    @Override
    public void update() {

    }

    /*
        Hardware or other variables that need setting up get put in here
     */
    public void init() {
        controller1 = robot.controller1();

        frontLeftMotor = robot.hardwareMap().get(DcMotor.class, "frontLeft");
        frontRightMotor = robot.hardwareMap().get(DcMotor.class, "frontRight");
        rearLeftMotor = robot.hardwareMap().get(DcMotor.class, "rearLeft");
        rearRightMotor = robot.hardwareMap().get(DcMotor.class, "rearRight");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /*
        The 'command' which controls the actual component
     */
    private void drive() {
        double drive = -controller1.left_stick_y;
        double turn = controller1.left_stick_x;
        double rx = controller1.right_stick_x;

        double frontLeftPower = Range.clip(drive + turn + rx, -1.0, 1);
        double backLeftPower = Range.clip((drive - turn + rx), -1.0, 1);
        double frontRightPower = Range.clip((drive - turn - rx), -1.0, 1);
        double backRightPower = Range.clip((drive + turn - rx), -1.0, 1);

        frontLeftMotor.setPower(frontLeftPower * MOTOR_SPEED_MULT);
        frontRightMotor.setPower(backLeftPower * MOTOR_SPEED_MULT);
        rearLeftMotor.setPower(frontRightPower * MOTOR_SPEED_MULT);
        rearRightMotor.setPower(backRightPower * MOTOR_SPEED_MULT);
    }
}
