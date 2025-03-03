package org.firstinspires.ftc.teamcode.util;

public interface Claw {
    // setting the open and closed position
    public void openClosePose(double open, double close);

    /**
     * methods for grab and drop
     *
     * 1st: is the one for teleOp. Condition is the button you want to assign to it
     * 2nd: is the one for auto
     */
    public void grabs(boolean move);
    public void grabs();
    public void drops(boolean move);
    public void drops();

    // set the specimen and sample positions
    public void sampSpecPose(double sample, double specimen);

    /**
     * methods for sample and specimen positions
     *
     * 1st: is the one for teleOp. Condition is the button you want to assign to it
     * 2nd: is the one for auto
     */
    public void sample(boolean move);
    public void sample();
    public void specimen(boolean move);
    public void specimen();
}
