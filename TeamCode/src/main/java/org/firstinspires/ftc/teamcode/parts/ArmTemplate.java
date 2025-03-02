package org.firstinspires.ftc.teamcode.parts;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Parts;

/**
 * This is an empty template for arm class
 * If you don't use any method just keep it empty
 */

public class ArmTemplate  implements Arm{
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
}
