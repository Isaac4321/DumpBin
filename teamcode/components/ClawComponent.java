package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Robot;

public class ClawComponent extends ComponentBase {

    private final double SERVO_INCREMENT = 0.1;

    private Servo servo;
    private Gamepad controller1;

    private double servoPosition;

    public ClawComponent(Robot robot) {
        super(robot);
        init();
    }

    private void init() {
        servo = robot.hardwareMap().get(Servo.class, "Servo");
        controller1 = robot.controller1();

        servoPosition = servo.getPosition();
    }

    @Override
    public void update() {
        if (controller1.right_bumper) {
            if (servoPosition < 1) {
                servoPosition += SERVO_INCREMENT;
                servo.setPosition(servoPosition);
            }
        }
        if (controller1.left_bumper) {
            if (servoPosition > 0) {
                servoPosition -= SERVO_INCREMENT;
                servo.setPosition(servoPosition);
            }
        }
    }
}
