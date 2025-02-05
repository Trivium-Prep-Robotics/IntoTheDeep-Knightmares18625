package org.firstinspires.ftc.teamcode.extra;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.DirectGear;
import org.firstinspires.ftc.teamcode.parts.GearClaw;

@TeleOp (name = "Direct Gear Bot")
@Disabled

public class BackUpBot extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        PartsBackUp robot = new PartsBackUp(hardwareMap);
        DirectGear arm = new DirectGear();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        PartsBackUp.setSlide = 0;
        PartsBackUp.setArm = 0;

        waitForStart();

        while (opModeIsActive()) {
            arm.armLims();
            arm.slideLims();

            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);
//            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);

            claw.grabs(gamepad2.right_bumper);
            claw.drops(gamepad2.left_bumper);

            drive.feildCentric(gamepad1);

            arm.armGo();
            arm.slideGo();
        }

    }
}
