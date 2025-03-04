package org.firstinspires.ftc.teamcode.teleOp.IntoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Parts;
import org.firstinspires.ftc.teamcode.util.IntoTheDeep.OurRobot;


/**
 * TeleOp used from qual 2 to State
 */
@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends LinearOpMode {


    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        OurRobot robot = new OurRobot();

        waitForStart(); // initialize

        // reset the encoder ticks for arm motors
        Parts.slide.setTargetPosition(0);
        Parts.piv1.setTargetPosition(0);
        Parts.piv2.setTargetPosition(0);

        // set the power used for arm motors to 1
        robot.armPower(1); // set arm power
        robot.extendPower(1); // set extend power

        robot.openClosePose(0.4, 0.8); // set claw positions
        robot.sampSpecPose(0.075, 0.6); // set wrist positions

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

            // drive train controls
            robot.feildCentric(gamepad1);


            // update every loop
            telemetry.addLine("arm:" + Parts.piv1.getCurrentPosition());
            telemetry.addLine("slide:" + Parts.slide.getCurrentPosition());

            telemetry.update();
        }
    }
}
