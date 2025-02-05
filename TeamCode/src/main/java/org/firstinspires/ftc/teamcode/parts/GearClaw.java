package org.firstinspires.ftc.teamcode.parts;

import org.firstinspires.ftc.teamcode.Parts;

public class GearClaw implements Claw {
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
}
