package org.firstinspires.ftc.teamcode.components;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class ComponentBase implements Component{

    protected Robot robot;

    public abstract void update();


    public ComponentBase(Robot robot) {
        this.robot = robot;
    }
}
