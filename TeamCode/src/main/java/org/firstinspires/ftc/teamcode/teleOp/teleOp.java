package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.BasicDrive;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.NewArm;

@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {


    public void runOpMode() throws InterruptedException {

        // all the used classes
        Parts robot = new Parts(hardwareMap);
        NewArm arm = new NewArm();
        GearClaw claw = new GearClaw();
        BasicDrive drive = new BasicDrive();

        waitForStart(); // initialize

        // reset the encoder ticks for arm motors
        Parts.slide.setTargetPosition(0);
        Parts.arm.setTargetPosition(0);

        // set the power used for arm motors to 1
        arm.armPower(1);
        arm.extendPower(1);

        // set claw open and close positions
        claw.openClosePose(0.35, 0.5);
        claw.sampSpecPose(0.25, 0.8);

        while (opModeIsActive()) {

            // arm controls
            arm.up(gamepad2.dpad_up);
            arm.down(gamepad2.dpad_down);
            arm.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            // extend controls
            arm.extend(gamepad2.right_trigger);
            arm.retract(gamepad2.left_trigger);
            arm.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            // claw controls
            claw.grabs(gamepad2.right_bumper);
            claw.drops(gamepad2.left_bumper);

            // wrist controls
            claw.specimen(gamepad2.y);
            claw.sample(gamepad2.a);

            // drive train controls
            drive.feildCentric(gamepad1);


            // update every loop
            telemetry.addLine("arm:" + Parts.arm.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());

            telemetry.update();

        }

    }
}
