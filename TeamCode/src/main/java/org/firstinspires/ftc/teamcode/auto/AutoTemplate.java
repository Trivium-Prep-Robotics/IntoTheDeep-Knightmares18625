package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.util.Robot;

/**
 * This is the general structure for an autonomous
 */

@Autonomous (name = "Net side", group = "old Auto")
//@Disabled
public class AutoTemplate extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot

        waitForStart();
        
    }
}
