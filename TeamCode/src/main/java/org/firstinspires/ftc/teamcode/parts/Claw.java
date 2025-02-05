package org.firstinspires.ftc.teamcode.parts;

public interface Claw {
    public void openClosePose(double open, double close);
    public void grabs(boolean move);
    public void grabs();
    public void drops(boolean move);
    public void drops();
    public void sampSpecPose(double sample, double specimen);
    public void sample(boolean move);
    public void sample();
    public void specimen(boolean move);
    public void specimen();
}

