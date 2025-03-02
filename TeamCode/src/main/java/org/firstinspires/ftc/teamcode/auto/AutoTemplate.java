package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.OurRobot;

/**
 * This is the general structure for an autonomous
 */

@Autonomous (name = "Net side", group = "old Auto")
//@Disabled
public class AutoTemplate extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap); // configure robot
        OurRobot bot = new OurRobot(); // configure robot

        waitForStart();
        
    }
}
