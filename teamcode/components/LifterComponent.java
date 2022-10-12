package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

public class LifterComponent extends ComponentBase{

    private DcMotor leftLifterMotor;
    private DcMotor rightLifterMotor;

    private Gamepad controller1;
    private ElapsedTime time = new ElapsedTime();

    private double PULSES_PER_ARM_DEGREE = (537.7 * 1.4) / 360;

    public LifterComponent(Robot robot) {
        super(robot);
        init();
    }

    private void init() {
        leftLifterMotor = robot.hardwareMap().get(DcMotor.class, "leftLifterMotor");
        leftLifterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightLifterMotor = robot.hardwareMap().get(DcMotor.class, "rightLifterMotor");
        rightLifterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        controller1 = robot.controller1();
    }

    @Override
    public void update() {
        if (controller1.a) {
            rotate(45, 1.0);
        }
        if (controller1.b) {
            rotate(-45, 1.0);
        }
    }

    private void rotate(int deg, double timeout) {
        int targetPos = leftLifterMotor.getCurrentPosition() + (int) (deg * PULSES_PER_ARM_DEGREE);

        if (targetPos > 10 || targetPos < -10) {
            return;
        }

        leftLifterMotor.setTargetPosition(targetPos);
        rightLifterMotor.setTargetPosition(targetPos);

        leftLifterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLifterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        time.reset();
        leftLifterMotor.setPower(0.1);
        rightLifterMotor.setPower(0.1);

        while (time.seconds() < timeout & (leftLifterMotor.isBusy() && rightLifterMotor.isBusy())) {

        }

        leftLifterMotor.setPower(0);
        rightLifterMotor.setPower(0);

    }
}
