package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.parts.OurRobot;


/**
 * TeleOp used from qual 2 to State
 */
@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        Parts robot = new Parts(hardwareMap); // configure robot
        OurRobot bot = new OurRobot();

        waitForStart(); // initialize

        // reset the encoder ticks for arm motors
        Parts.slide.setTargetPosition(0);
        Parts.piv1.setTargetPosition(0);
        Parts.piv2.setTargetPosition(0);

        // set the power used for arm motors to 1
        bot.armPower(1); // set arm power
        bot.extendPower(1); // set extend power

        bot.openClosePose(0.4, 0.8); // set claw positions
        bot.sampSpecPose(0.075, 0.6); // set wrist positions

        while (opModeIsActive()) {

            // arm controls
            bot.up(gamepad2.dpad_up);
            bot.down(gamepad2.dpad_down);
            bot.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            // extend controls
            bot.extend(gamepad2.right_trigger);
            bot.retract(gamepad2.left_trigger);
            bot.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            // claw controls
            bot.grabs(gamepad2.right_bumper);
            bot.drops(gamepad2.left_bumper);

            // wrist controls
            bot.specimen(gamepad2.y);
            bot.sample(gamepad2.a);

            // drive train controls
            bot.feildCentric(gamepad1);


            // update every loop
            telemetry.addLine("arm:" + Parts.piv1.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());

            telemetry.update();
        }
    }
}
