package org.firstinspires.ftc.teamcode.teleOp.IntoTheDeep;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.IntoTheDeep.Parts;
import org.firstinspires.ftc.teamcode.util.IntoTheDeep.OurRobot;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.util.pedroPathing.constants.LConstants;

/**
 * Actually not used, but it's the same as our other teleOp it just uses the pedro pathing stuff for the drive
 */

@TeleOp (name = "State TeleOp", group = "TELEOP")
public class StateTeleOp extends LinearOpMode {
    private static Follower follower;
    private final Pose startPose = new Pose(0,0,Math.toRadians(0));
    public void runOpMode() throws InterruptedException {
        // all the used classes
        Parts config = new Parts(hardwareMap); // configure robot
        OurRobot robot = new OurRobot();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        waitForStart(); // initialize
        follower.startTeleopDrive();

        // reset the encoder ticks for arm motors
        Parts.slide.setTargetPosition(0);
        Parts.piv1.setTargetPosition(0);
        Parts.piv2.setTargetPosition(0);

        // set the power used for arm motors to 1
        robot.armPower(1);
        robot.extendPower(1);

        // set claw open and close positions
        //claw.openClosePose(0.35, 0.5);
        robot.openClosePose(0.375, 0.8); // I'm never letting jacob r touch this code again
        robot.sampSpecPose(0.2, 0.4);

        while (opModeIsActive()) {

            // arm controls
            robot.up(gamepad2.dpad_up);
            robot.down(gamepad2.dpad_down);
            robot.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            // extend controls
            robot.extend(gamepad2.right_trigger);
            robot.retract(gamepad2.left_trigger);
            robot.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            // claw controls
            robot.grabs(gamepad2.right_bumper);
            robot.drops(gamepad2.left_bumper);

            // wrist controls
            robot.specimen(gamepad2.y);
            robot.sample(gamepad2.a);

            // drive controls
//            drive.feildCentric(gamepad1);
            follower.setTeleOpMovementVectors(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);
            follower.update();

            /* Telemetry Outputs of our Follower */
            telemetry.addData("X", follower.getPose().getX());
            telemetry.addData("Y", follower.getPose().getY());
            telemetry.addData("Heading in Degrees", Math.toDegrees(follower.getPose().getHeading()));

            /* Update Telemetry to the Driver Hub */
            telemetry.update();
        }
    }
}
