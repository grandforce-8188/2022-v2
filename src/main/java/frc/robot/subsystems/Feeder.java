package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
    CANSparkMax feederLeader = new CANSparkMax(Constants.srxOmniLeader, CANSparkMax.MotorType.kBrushless);
    CANSparkMax feederFollower = new CANSparkMax(Constants.srxOmniFollower, CANSparkMax.MotorType.kBrushless);

    final double feederSpeed = 0.8;

    public Feeder()
    {
        feederLeader.restoreFactoryDefaults();
        feederFollower.restoreFactoryDefaults();
        feederLeader.setInverted(false);
        feederFollower.setInverted(true);
        feederLeader.setIdleMode(IdleMode.kBrake);
        feederFollower.setIdleMode(IdleMode.kBrake);
        feederFollower.follow(feederLeader, true);

        feederLeader.burnFlash();
        feederFollower.burnFlash();
    }

    public void runFeeder()
    {
        feederLeader.set(feederSpeed);
    }

    public void reverseFeeder() {
        feederLeader.set(-feederSpeed);
    }

    public void stopFeeder() {
        feederLeader.set(0.0);
    }
}
