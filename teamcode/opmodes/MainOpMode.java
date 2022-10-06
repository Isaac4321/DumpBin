package org.firstinspires.ftc.teamcode.opmodes;

import android.os.Build;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.components.DriveBaseComponent;


@TeleOp(name="Main Iterative OpMode", group="Iterative Opmode")
public class MainOpMode extends OpMode
{
//    TODO: Create a LED manager that allows for full control of LEDs
//    TODO: Create a sound manager that allows for playing sound during the op-mode
//    TODO: Create a readme.md which explains how to:
//         - Create a component
//         - Add a component
//         - Use the sound manager
//         - Use the LED manager
//         - Printing to the telemetry console
//         - Write comments for the other classes

    private Robot robot;

    /**
     * This function is called when the INIT button is clicked on the driver station (NOT RUNNING).
     * Treat this method similarly to a class constructor as it is only ran once during initialization,
     * initialize variables here and add components to the robot
     *
     * DRIVE TEAM:
     * If you are having issues with a component you can disable the component here.
     * For example, if the drive-base was constantly driving (without touching joystick) comment it out like shown,
     * this is the same for all other components.
     *
     * //robot.addComponent(new DriveBaseComponent(robot));
     *
     */
    @Override
    public void init() {
        robot = new Robot(this);
        robot.addComponent(new DriveBaseComponent(robot));
    }

    /**
     * This function is called when the robot has been initialized and you click the START button.
     * This is where you can display initial values once i.e for debugging and testing.
     */
    @Override
    public void start() {
        telemetry.addData("Current OpMode Started.", null);
        telemetry.update();
    }

    /**
     * This function is continuously ran while the robot is running.
     * Calls the @link{ComponentBase#update()} method for all the components
     */
    @Override
    public void loop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            robot.components().forEach(component -> component.update());
        }
        telemetry.addData("Current OpMode Updating.", null);
        telemetry.update();
    }

    /**
     * This function is called once when the STOP button has been pressed.
     */
    @Override
    public void stop() {
        telemetry.addData("Current OpMode Stopped.", null);
        telemetry.update();
    }
}
