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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.seq.Sequence;
import org.firstinspires.ftc.teamcode.autonomous.seq.SequenceManager;

import java.util.Arrays;

/**
 * The main Op-mode we use for operating the robot, this is also where we control
 * the creation of components @see{Robot#addComponent}, this is done during initialization.
 *
 */
@Autonomous(name="Main Autonomous OpMode", group="Iterative Opmode")
public class MainAutonomous extends OpMode
{

    private Robot robot;

    private SequenceManager sequenceManager;
    @Override
    public void init() {
        robot = new Robot(this);


        sequenceManager = new SequenceManager(robot);
        Sequence seq1 = new Sequence(sequenceManager);

        //Drive forwards 5 cms with a timeout of 5 seconds
        seq1.addCommand("drive", "5,2.0");
        sequenceManager.addSequences(Arrays.asList(seq1));
    }


    @Override
    public void start() {
        sequenceManager.run();
        telemetry.addData("Current OpMode Started.", null);
        telemetry.update();

    }

    @Override
    public void loop() {
        telemetry.addData("Current OpMode Updating.", null);
        telemetry.update();
    }


    @Override
    public void stop() {
        telemetry.addData("Current OpMode Stopped.", null);
        telemetry.update();
    }
}
