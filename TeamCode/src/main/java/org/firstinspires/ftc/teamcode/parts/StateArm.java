package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;

public class StateArm implements Arm{
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
}
