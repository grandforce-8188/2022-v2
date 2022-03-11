package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    CANSparkMax feederLeader = new CANSparkMax(Constants.srxOmniLeader, CANSparkMax.MotorType.kBrushless);
    CANSparkMax feederFollower = new CANSparkMax(Constants.srxOmniFollower, CANSparkMax.MotorType.kBrushless);

    final double feederSpeed = 1.0;

    public Feeder()
    {
        feederFollower.follow(feederLeader, true);
    }

    public void runFeeder()
    {
        System.out.println("Feeder running");
        feederLeader.set(feederSpeed);
    }

    public void reverseFeeder() {
        feederLeader.set(-feederSpeed);
    }

    public void stopFeeder() {
        feederLeader.set(0.0);
    }
}
