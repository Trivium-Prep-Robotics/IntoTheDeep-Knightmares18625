package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.OurRobot;


/**
 * This is a template for a teleOp
 * It should allow you to move field centric and has the base structure of teleOp along with configuring the robot
 */
@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOpTemplate extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap); // configure robot
        OurRobot bot = new OurRobot(); // configure robot
        // if you want to use other classes these are example ways of how you would do that
//        DriveTemplate drive = new DriveTemplate();
//        ClawTemplate claw = new ClawTemplate();
//        ArmTemplate arm = new ArmTemplate();

        waitForStart(); // initialize

        // set the power used for arm motors to 1
        bot.armPower(1); // set arm power
        bot.extendPower(1); // set extend power

        bot.openClosePose(0.4, 0.8); // set claw positions
        bot.sampSpecPose(0.075, 0.6); // set wrist positions

        while (opModeIsActive()) {
            /**
             * I the button in the parameters are the assigned button for that command
             * the stop methods on the other hand you just put the conditions in which you don't want them moving
             *
             * If you use the joysticks or triggers for any case you should go to the interface
             * and set the method to intake a double instead of a boolean and then do the same for
             * the class that extends it.
             *
             * By default the extend and retract use doubles, sense our code used the trigger buttons for those
             * so if you want to use another button you switch it to boolean
             */

            // arm controls
            bot.up(gamepad2.dpad_up);
            bot.down(gamepad2.dpad_down);
            // I put this in my code so that it knew when to stop moving
//            arm.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            // extend controls
            bot.extend(gamepad2.right_trigger);
            bot.retract(gamepad2.left_trigger);
            // I put this in my code so that it knew when to stop moving
//            arm.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            // claw controls
            bot.grabs(gamepad2.right_bumper);
            bot.drops(gamepad2.left_bumper);

            // wrist controls
            bot.specimen(gamepad2.y);
            bot.sample(gamepad2.a);

            // sets field centric drive, if you would like robot centric or tank uncomment which one you want but make sure no more than one is uncommented
            bot.feildCentric(gamepad1);
//            bot.robotCentric(gamepad1);
//            bot.tankDrive(gamepad1);
        }
    }
}
