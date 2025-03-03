package org.firstinspires.ftc.teamcode.parts;

public interface Arm {
    // methods for setting power used
    public void armPower(double power);
    public void extendPower(double power);

    /**
     *
     * methods for motor movements
     * 1st version: for teleOp, put the button you want to assign to that motion as the parameter
     * 2nd: is for auto where the parameter is the time in milliseconds the motor runs
     *
     * the stop methods are the conditions in which the motors must stop (normally for teleOp)
     */
    public void up(boolean move);
    public void up(int sec) throws InterruptedException;
    public void down(boolean move);
    public void down(int sec) throws InterruptedException;
    public void armStop(boolean stop);
    public void extend(double power);
    public void extend(int sec) throws InterruptedException;
    public void retract(double power);
    public void retract(int sec) throws InterruptedException;
    public void slideStop(boolean stop);

    // methods for setting motor encoder position target
    public void setArm(double ticks);
    public void setSlide(double ticks);

    // methods for limits
    public void armLims();
    public void slideLims();

    // actually go to encoder position
    public void armGo();
    public void slideGo();
}
