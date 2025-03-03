package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.OurRobot;

/**
 * This Auto moves forward to score the pre-load specimen then moves to park (observation side)
 * Used in the last qual competition
 */
@Autonomous (name = "Observation side", group = "old Auto")
//@Disabled
public class Observation extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap); // configure robot
        OurRobot bot = new OurRobot();

        bot.openClosePose(0.4, 0.8); // set claw positions
        bot.sampSpecPose(0.075, 0.6); // set wrist positions

        bot.grabs();

        bot.armPower(1); // set arm power
        bot.extendPower(1); // set extend power

        waitForStart();

        // rise and extend arm
        bot.up(2000);
        bot.extend(1200);

        // move to chamber
        bot.moveRobot(0.5, 0, 0);
        sleep(2000);
        bot.moveRobot(0, 0, 0);

        // pull down to secure specimen
        bot.down(500);

        // let go
        bot.drops();

        sleep(1000);

        // move back
        bot.moveRobot(-0.5, 0, 0);
        sleep(2000);
        bot.moveRobot(0, -0.5, 0); // move to park
        sleep(2500);
        bot.moveRobot(-0.25, 0, 0); // back into wall to straighten
        sleep(500);
        bot.moveRobot(0, 0, 0);

        // set arm to start position
        bot.retract(1200);
        bot.down(1500);

        
    }
}
