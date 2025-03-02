package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Parts;

public class OurRobot implements Arm, Claw, Drive {
    /**
     * ARM SECTION
     */
    public void armPower(double power) {
        /*
        Set power variable
         */
    }

    public void extendPower(double power) {
        /*
        Set power variable
         */
    }

    public void up(boolean move) {
        if (move) {
            /*
            Move parameter is for the button used
            Code to move arm up
            */
        }
    }

    public void up(int sec) throws InterruptedException{
        /*
        Code to move arm up in auto
        Ours uses a "second" parameter for seconds running, but change if you need for something different
        (Keep in mind you might have to change the interface for this)
         */
    }

    public void down(boolean move) {
        if (move) {
            /*
            Move parameter is for the button used
            Code to move arm down
            */
        }
    }

    public void down(int sec) throws InterruptedException{
        /*
        Code to move arm down in auto
        Ours uses a "second" parameter for seconds running, but change if you need for something different
        (Keep in mind you might have to change the interface for this)
         */
    }

    public void armStop(boolean stop) {
        if (stop) {
            /*
            Code to stop
            the parameter is the condition to stop
            */
        }
    }

    public void extend(double power) {
        if (power != 0) {
            /*
            Move parameter is for the button used
            Code to extend arm
             */
        }
    }

    public void extend(int sec) throws InterruptedException{
        /*
        Code to extend arm in auto
        Ours uses a "second" parameter for seconds running, but change if you need for something different
        (Keep in mind you might have to change the interface for this)
         */
    }

    public void retract(double power) {
        if (power != 0) {
            /*
            Move parameter is for the button used
            Code to retract arm
            */
        }
    }

    public void retract(int sec) throws InterruptedException{
        /*
        Code to retract arm in auto
        Ours uses a "second" parameter for seconds running, but change if you need for something different
        (Keep in mind you might have to change the interface for this)
         */
    }

    public void slideStop(boolean stop) {
        if (stop) {
            /*
            Code to stop
            the parameter is the condition to stop
            */
        }
    }

    public void setArm(double ticks) {
        /*
        Code to assign the target position of arm
         */
    }

    public void setSlide(double ticks) {
        /*
        Code to assign the target position of slides
         */
    }

    public void armLims() {
        /*
        Code for arm limits
         */
    }

    public void slideLims() {
        /*
        code for slide limits
         */
    }

    public void armGo() {
        /*
        Tell arm to go to target position
         */
    }

    public void slideGo() {
        /*
        Tell slide to go to target position
         */
    }


    /**
     * CLAW SECTION
     */
    public void openClosePose(double open, double close) {
        /*
        Set position variable
         */
    }

    public void grabs(boolean move) {
        /*
        Code for grabbing in teleOp. Parameter is for the button used
         */
    }

    public void grabs() {
        /*
        Code for grabbing in auto
         */
    }

    public void drops(boolean move) {
        /*
        Code for dropping in teleOp. Parameter is for the button used
         */
    }
    public void drops() {
        /*
        Code for grabbing in auto
         */
    }

    public void sampSpecPose(double sample, double specimen) {
        /*
        Set wrist position variable
         */
    }

    public void sample(boolean move) {
        /*
        Code for setting to sample position in teleOp. Parameter is for the button used
         */
    }

    public void sample() {
        /*
        Code for setting to sample position in auto
         */
    }

    public void specimen(boolean move) {
        /*
        Code for setting to specimen position in teleOp. Parameter is for the button used
         */
    }

    public void specimen() {
        /*
        Code for setting to specimen position in auto
         */
    }


    /**
     * DRIVE SECTION
     */
    // Setting the normal and slow speeds
    public void fastSlowSpd(double fast, double slow) {
        Parts.driveMaxSpd = fast;
        Parts.driveSlwSpd = slow;
    }

    // for auto movement
    public void moveRobot(double x, double y, double yaw) { // power in the x, y, and turn directions
        // Calculate wheel powers.
        double leftFrontPower = x - y - yaw;
        double rightFrontPower = x + y + yaw;
        double leftBackPower = x + y - yaw;
        double rightBackPower = x - y + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send powers to the wheels.
        Parts.FL.setPower(leftFrontPower);
        Parts.FR.setPower(rightFrontPower);
        Parts.BL.setPower(leftBackPower);
        Parts.BR.setPower(rightBackPower);
    }

    // for teleOp movement (field centric)

    public void feildCentric(Gamepad gamepad) { // yeah just put this in your teleOp
        double botHeading = Parts.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double vertical = -gamepad.left_stick_y * Parts.driveMaxSpd;
        double horizontal = gamepad.left_stick_x * Parts.driveMaxSpd;
        double pivot = gamepad.right_stick_x * Parts.driveMaxSpd;
        double denominator = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(pivot), 1);

        if (gamepad.right_trigger > 0) {
            vertical = -gamepad.left_stick_y * Parts.driveSlwSpd;
            horizontal = gamepad.left_stick_x * Parts.driveSlwSpd;
            pivot = gamepad.right_stick_x * (Parts.driveSlwSpd + 0.1);
        }

        // Kinematics (Counter-acting angle of robot's heading)
        double newVertical = horizontal * Math.sin(-botHeading) + vertical * Math.cos(-botHeading);
        double newHorizontal = horizontal * Math.cos(-botHeading) - vertical * Math.sin(-botHeading);

        // Setting Field Centric Drive
        Parts.FL.setPower((newVertical + newHorizontal + pivot) / denominator);
        Parts.FR.setPower((newVertical - newHorizontal - pivot) / denominator);
        Parts.BL.setPower((newVertical - newHorizontal + pivot) / denominator);
        Parts.BR.setPower((newVertical + newHorizontal - pivot) / denominator);
    }

    public void robotCentric(Gamepad gamepad) {
        double vertical = -gamepad.left_stick_y * Parts.driveMaxSpd;
        double horizontal = gamepad.left_stick_x * Parts.driveMaxSpd;
        double pivot = gamepad.right_stick_x * Parts.driveMaxSpd;
        double denominator = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(pivot), 1);

        if (gamepad.right_trigger > 0) {
            vertical = -gamepad.left_stick_y * Parts.driveSlwSpd;
            horizontal = gamepad.left_stick_x * Parts.driveSlwSpd;
            pivot = gamepad.right_stick_x * (Parts.driveSlwSpd + 0.1);
        }

        // Setting Field Centric Drive
        Parts.FL.setPower((vertical + horizontal + pivot) / denominator);
        Parts.FR.setPower((vertical - horizontal - pivot) / denominator);
        Parts.BL.setPower((vertical - horizontal + pivot) / denominator);
        Parts.BR.setPower((vertical + horizontal - pivot) / denominator);
    }

    public void tankDrive(Gamepad gamepad) {
        double leftPower = gamepad.left_stick_y * Parts.driveMaxSpd;
        double rightPower = gamepad.right_stick_y * Parts.driveMaxSpd;

        Parts.FL.setPower(leftPower);
        Parts.FR.setPower(rightPower);
        Parts.BL.setPower(leftPower);
        Parts.BR.setPower(rightPower);
    }
}
