package org.firstinspires.ftc.teamcode.auto.IntoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.util.IntoTheDeep.OurRobot;

/**
 * This Auto that simply moves the robot to forward to place the pre-load specimen then back to the start (net side)
 * Used in the last qual competition
 */

@Autonomous (name = "Net side", group = "old Auto")
//@Disabled
public class Net extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        OurRobot robot = new OurRobot();

        robot.openClosePose(0.4, 0.8); // set claw positions
        robot.sampSpecPose(0.075, 0.6); // set wrist positions

        robot.grabs();

        robot.armPower(1); // set arm power
        robot.extendPower(1); // set extend power

        waitForStart();

        // rise and extend arm
        robot.up(2000);
        robot.extend(1200);

        // move to chamber
        robot.moveRobot(0.5, 0, 0);
        sleep(2000);
        robot.moveRobot(0, 0, 0);

        // pull down to secure specimen
        robot.down(500);

        // let go
        robot.drops();

        sleep(1000);

        // move back
        robot.moveRobot(-0.5, 0, 0);
        sleep(2000);
        robot.moveRobot(0, 0, 0);

        // set arm to start position
        robot.retract(1200);
        robot.down(1500);

        
    }
}
