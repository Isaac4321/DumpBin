package org.firstinspires.ftc.teamcode.components;

import android.content.Context;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;

import java.io.File;

public class SoundComponent extends ComponentBase{

    private final File file = new File("C:\\Users\\12368\\StudioProjects\\FtcRobotController\\TeamCode\\src\\main\\java\\org\\firstinspires\\ftc\\teamcode\\sound\\tiktok-snoring-(original-meme-sound-effect)-By-Tuna.mp3");

    private Gamepad controller1;
    private Context context;

    private boolean soundPlaying = false;

    public SoundComponent(Robot robot) {
        super(robot);
    }

    @Override
    public void update() {
        if (controller1.a && !soundPlaying) {
            playSound(file);
        }
    }

    @Override
    public void init() {
        controller1 = robot.controller1();
        context = robot.hardwareMap().appContext;
    }

    private void playSound(File soundFile ) {
        SoundPlayer.PlaySoundParams params = new SoundPlayer.PlaySoundParams();
        params.loopControl = 0;
        params.waitForNonLoopingSoundsToFinish = true;

        soundPlaying = true;
        SoundPlayer.getInstance().startPlaying(context, soundFile, params, null, () -> {
            soundPlaying = false;
        });
    }
}
