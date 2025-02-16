package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.GearClaw;
import org.firstinspires.ftc.teamcode.parts.PedroDrive;
import org.firstinspires.ftc.teamcode.parts.StateArm;

@TeleOp (name = "State TeleOp", group = "TELEOP")
public class StateTeleOp extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        // all the used classes
        Parts robot = new Parts(hardwareMap);
        StateArm arm = new StateArm();
        GearClaw claw = new GearClaw();
        PedroDrive drive = new PedroDrive();

        waitForStart(); // initialize

        // reset the encoder ticks for arm motors
        Parts.slide.setTargetPosition(0);
        Parts.piv1.setTargetPosition(0);
        Parts.piv2.setTargetPosition(0);

        // set the power used for arm motors to 1
        arm.armPower(1);
        arm.extendPower(1);

        // set claw open and close positions
        //claw.openClosePose(0.35, 0.5);
        claw.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
        claw.sampSpecPose(0.2, 0.4);

        while (opModeIsActive()) {
            Parts.follower.startTeleopDrive();

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

            telemetry.update();
        }
    }
}
