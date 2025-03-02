package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Parts;

public class OurRobot implements Arm, Claw, Drive {
    /**
     * ARM SECTION
     */
    /* Set the arm power used */
    public void armPower(double power) {
        Parts.armPower = power;
    }

    /* Set the extend power used */
    public void extendPower(double power) {
        Parts.extendPower = power;
    }

    /* up movement methods for the arm */
    public void up(boolean move) {
        if (move) {
            Parts.piv1.setPower(Parts.armPower);
            Parts.piv2.setPower(Parts.armPower);
            Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        }
    }

    public void up(int sec) throws InterruptedException{
        Parts.piv1.setPower(Parts.armPower);
        Parts.piv2.setPower(Parts.armPower);
        Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.piv1.setPower(0);
        Parts.piv2.setPower(0);
        Parts.slide.setPower(0);
    }

    /* down movement methods for the arm */
    public void down(boolean move) {
        if (move) {
            Parts.piv1.setPower(-Parts.armPower);
            Parts.piv2.setPower(-Parts.armPower);
            Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        }
    }

    public void down(int sec) throws InterruptedException{
        Parts.piv1.setPower(-Parts.armPower);
        Parts.piv2.setPower(-Parts.armPower);
        Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.piv1.setPower(0);
        Parts.piv2.setPower(0);
        Parts.slide.setPower(0);
    }

    /* method to check to stop the arm */
    public void armStop(boolean stop) {
        if (stop) {
            Parts.piv1.setPower(0);
            Parts.piv2.setPower(0);
        }
    }

    /* extend movement methods for the arm */
    public void extend(double power) {
        if (power != 0) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-Parts.extendPower);
        }
    }

    public void extend(int sec) throws InterruptedException{
        Parts.slide.setPower(-Parts.extendPower);
        Thread.sleep(sec);
        Parts.slide.setPower(0);
    }

    /* retract movement methods for the arm */
    public void retract(double power) {
        if (power != 0) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(Parts.extendPower);
        }
    }

    public void retract(int sec) throws InterruptedException{
        Parts.slide.setPower(Parts.extendPower);
        Thread.sleep(sec);
        Parts.slide.setPower(0);
    }

    /* method to stop the extention */
    public void slideStop(boolean stop) {
        if (stop) {
            Parts.slide.setPower(0);
        }
    }

    /* setting the arm ticks */
    public void setArm(double ticks) {
        Parts.piv1.setTargetPosition((int)(ticks * Parts.pivTPR));
        Parts.piv2.setTargetPosition((int)(ticks * Parts.pivTPR));
        Parts.piv1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Parts.piv2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Parts.piv1.setPower(Parts.armPower);
        Parts.piv2.setPower(Parts.armPower);
        Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        while (Parts.piv1.isBusy() && Parts.piv2.isBusy()) {
        }
        Parts.piv1.setPower(0);
        Parts.piv2.setPower(0);
        Parts.slide.setPower(0);
    }

    /* setting the slide ticks */
    public void setSlide(double ticks) {
        Parts.slide.setTargetPosition((int)(ticks * Parts.slideTPR));
        Parts.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Parts.slide.setPower(Parts.extendPower);
        while (Parts.slide.isBusy()) {
        }
        Parts.slide.setPower(0);
    }

    /* limit methods for the arm and extention */
    public void armLims() {

    }

    public void slideLims() {

    }

    /* methods to start encoders */
    public void armGo() {

    }

    public void slideGo() {

    }


    /**
     * CLAW SECTION
     */
    // setting the open and close positions for the claw
    public void openClosePose(double open, double close) {
        Parts.openClaw = open;
        Parts.closeClaw = close;
    }

    // grab methods for claw
    public void grabs(boolean move) {
        if (move) { Parts.claw.setPosition(Parts.closeClaw); }
    }
    public void grabs() { Parts.claw.setPosition(Parts.closeClaw); }

    // drop methods for claw
    public void drops(boolean move) {
        if (move) { Parts.claw.setPosition(Parts.openClaw); }
    }
    public void drops() { Parts.claw.setPosition(Parts.openClaw); }

    // setting the sample and specimen positions for the claw
    public void sampSpecPose(double sample, double specimen) {
        Parts.sample = sample;
        Parts.specimen = specimen;
    }

    // sample methods for claw
    public void sample(boolean move) {
        if (move) { Parts.wrist.setPosition(Parts.sample); }
    }
    public void sample() { Parts.wrist.setPosition(Parts.sample); }

    // specimen methods for claw
    public void specimen(boolean move) {
        if (move) { Parts.wrist.setPosition(Parts.specimen); }
    }
    public void specimen() { Parts.wrist.setPosition(Parts.specimen); }


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
