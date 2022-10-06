/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmodes;

import android.os.Build;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.components.DriveBaseComponent;
import org.firstinspires.ftc.teamcode.components.SoundComponent;


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
        robot.addComponent(new SoundComponent(robot));
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
