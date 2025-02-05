package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Parts;

public class NewArm implements Arm{
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
        if (move && !Parts.lims) {
            Parts.arm.setPower(Parts.armPower);
            Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        } else if (move && (Parts.arm.getCurrentPosition() < Parts.armHigh || Parts.arm.getCurrentPosition() == 0)) {
            Parts.arm.setPower(Parts.armPower);
            Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        }
    }

    public void up(int sec) throws InterruptedException{
        Parts.arm.setPower(Parts.armPower);
        Parts.slide.setPower(Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.arm.setPower(0);
        Parts.slide.setPower(0);
    }

    /* down movement methods for the arm */
    public void down(boolean move) {
        if (move && !Parts.lims) {
            Parts.arm.setPower(-Parts.armPower);
            Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        } else if (move && Parts.arm.getCurrentPosition() > Parts.armLow) {
            Parts.arm.setPower(-Parts.armPower);
            Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        }
    }

    public void down(int sec) throws InterruptedException{
        Parts.arm.setPower(-Parts.armPower);
        Parts.slide.setPower(-Parts.armPower * Parts.armToExtend);
        Thread.sleep(sec);
        Parts.arm.setPower(0);
        Parts.slide.setPower(0);
    }

    /* method to check to stop the arm */
    public void armStop(boolean stop) {
        if (stop) {
            Parts.arm.setPower(0);
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
        if ((Parts.arm.getCurrentPosition() < Parts.armHigh || Parts.arm.getCurrentPosition() < Parts.armHigh) && (Parts.arm.getCurrentPosition() > Parts.armLow)) {
            Parts.inEncoderA = false;
        } else if (Parts.arm.getCurrentPosition() < Parts.armLow) {
            Parts.inEncoderA = true;
            Parts.setArm = Parts.armLow;
        } else if (Parts.arm.getCurrentPosition() > Parts.armHigh) {
            Parts.inEncoderA = true;
            Parts.setArm = Parts.armHigh;
        } else {
            Parts.inEncoderA = false;
        }
    }

    public void slideLims() {
        Parts.inEncoderA = true;

        if (Parts.slidePose < Parts.slideLow) {
            Parts.setSlide = Parts.slideLow;
        } else if (Parts.slidePose > Parts.slideHigh) {
            Parts.setSlide = Parts.slideHigh;
        } else {
            Parts.inEncoderA = false;
        }
    }

    /* methods to start encoders */
    public void armGo() {
        if (Parts.inEncoderA) {
            Parts.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            Parts.arm.setTargetPosition(Parts.setArm);
            Parts.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Parts.arm.setPower(Parts.armPower);
            if (Parts.arm.getCurrentPosition() > Parts.setArm - 1 || Parts.arm.getCurrentPosition() < Parts.setArm + 1) {
                Parts.inEncoderA = false;
            }

            Parts.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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
