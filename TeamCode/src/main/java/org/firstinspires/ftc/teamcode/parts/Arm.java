package org.firstinspires.ftc.teamcode.parts;

public interface Arm {
    public void armPower(double power);
    public void extendPower(double power);
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

    public void setArm(double ticks);
    public void setSlide(int ticks);

    public void armLims();
    public void slideLims();

    public void armGo();
    public void slideGo();
}

