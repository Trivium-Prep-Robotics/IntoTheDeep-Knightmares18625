package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;
import org.firstinspires.ftc.teamcode.parts.StateArm;

@Autonomous (name = "Observation side")
//@Disabled
public class Observation extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap);
        StateArm arm = new StateArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        claw.grabs();

        arm.armPower(1);
        arm.extendPower(1);

        claw.openClosePose(0.4, 0.8);
        claw.sampSpecPose(0.075, 0.6);

        waitForStart();

        // rise and extend arm
        arm.up(2000);
        arm.extend(1200);

        // move to chamber
        drive.moveRobot(0.5, 0, 0);
        sleep(2000);
        drive.moveRobot(0, 0, 0);

        // pull down to secure specimen
        arm.down(500);

        // let go
        claw.drops();

        sleep(1000);

        // move back
        drive.moveRobot(-0.5, 0, 0);
        sleep(2000);
        drive.moveRobot(0, -0.5, 0); // move to park
        sleep(2500);
        drive.moveRobot(-0.25, 0, 0); // back into wall to straighten
        sleep(500);
        drive.moveRobot(0, 0, 0);

        // set arm to start position
        arm.retract(1200);
        arm.down(1500);

        
    }
}
