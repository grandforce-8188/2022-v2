package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    PWMSparkMax feederLeader = new PWMSparkMax(Constants.pwmFeederLeader);
    PWMSparkMax feederFollower = new PWMSparkMax(Constants.pwmFeederFollower);

    final double feederSpeed = 1.0;

    public Feeder()
    {
        feederFollower.setInverted(true);
    }

    public void runFeeder()
    {
        feederLeader.set(feederSpeed);
        feederFollower.set(feederSpeed);
    }

    public void reverseFeeder() {
        feederLeader.set(-feederSpeed);
        feederFollower.set(-feederSpeed);
    }

    public void stopFeeder() {
        feederLeader.set(0.0);
        feederFollower.set(0.0);
    }
}
