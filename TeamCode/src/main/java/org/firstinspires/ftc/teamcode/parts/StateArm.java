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
        if (power != 0 && !Parts.lims) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-Parts.extendPower);
        } else if (power != 0 && (Parts.slidePose > Parts.slideLow || Parts.slidePose == 0)) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(-1);
        }
    }

    public void extend(int sec) throws InterruptedException{
        Parts.slide.setPower(-Parts.extendPower);
        Thread.sleep(sec);
        Parts.slide.setPower(0);
    }

    /* retract movement methods for the arm */
    public void retract(double power) {
        if (power != 0 && !Parts.lims) {
            Parts.inEncoderS = false;
            Parts.slide.setPower(Parts.extendPower);
        } else if (power != 0 && Parts.slidePose < Parts.slideHigh) {
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
    public void setArm(int ticks) {
        Parts.inEncoderA = true;

        Parts.setArm = ticks;
    }

    /* setting the slide ticks */
    public void setSlide(int ticks) {
        Parts.inEncoderS = true;

        Parts.setSlide = (int)(ticks + Parts.slideTicksZero);

    }

    /* limit methods for the arm and extention */
    public void armLims() {

    }

    public void slideLims() {

    }

    /* methods to start encoders */
    public void armGo() {
        if (Parts.inEncoderA) {
            Parts.piv1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Parts.piv2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            Parts.piv1.setTargetPosition(Parts.setArm);
            Parts.piv2.setTargetPosition(Parts.setArm);
            Parts.piv1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.piv2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.piv1.setPower(Parts.armPower);
            Parts.piv2.setPower(Parts.armPower);
            if (Parts.piv1.getCurrentPosition() > Parts.setArm - 1 || Parts.piv1.getCurrentPosition() < Parts.setArm + 1) {
                Parts.inEncoderA = false;
            }

            Parts.piv1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            Parts.piv2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

    }

    public void slideGo() {
        if (Parts.inEncoderS) {
            Parts.slide.setTargetPosition(Parts.setSlide);
            Parts.slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.slide.setPower(Parts.extendPower);
            if (Parts.slide.getCurrentPosition() > Parts.setSlide - 1 || Parts.slide.getCurrentPosition() < Parts.setSlide + 1) {
                Parts.inEncoderS = false;
            }
        }
    }
}
