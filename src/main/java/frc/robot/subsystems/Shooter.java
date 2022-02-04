package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Shooter {
    WPI_TalonSRX shooterLeader = new WPI_TalonSRX(Constants.srxShooterLeader);
    WPI_TalonSRX shooterFollower = new WPI_TalonSRX(Constants.srxShooterFollower);

    public Shooter() {
        shooterFollower.follow(shooterLeader);
        shooterFollower.setInverted(true);
    }

    public void setSpeed(double speed) {
        shooterLeader.set(speed);
    }
}
