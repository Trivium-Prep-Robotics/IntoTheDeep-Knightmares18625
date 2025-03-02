package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.Gamepad;

public interface Drive {
    public void fastSlowSpd(double fast, double slow);
    public void moveRobot(double x, double y, double yaw);
    public void feildCentric(Gamepad gamepad);
    public void robotCentric(Gamepad gamepad);
    public void tankDrive(Gamepad gamepad);
}
