package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.Gamepad;

public interface Drive {
    public void moveRobot(double x, double y, double yaw);
    public void feildCentric(Gamepad gamepad);
}

