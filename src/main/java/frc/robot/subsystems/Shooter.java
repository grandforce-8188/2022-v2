package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    WPI_TalonFX shooterLeader = new WPI_TalonFX(Constants.fxShooterLeader);
    //WPI_TalonFX shooterFollower = new WPI_TalonFX(Constants.fxShooterFollower);

    WPI_VictorSPX kickWheel = new WPI_VictorSPX(Constants.srxKickWheel);

    public Shooter() {
        //shooterFollower.follow(shooterLeader);
        //shooterFollower.setInverted(true);
        shooterLeader.enableVoltageCompensation(true);
    }

    public void setShooterSpeed(double speed) {
        shooterLeader.set(speed);
    }

    public void runKickWheel(double speed) {
        kickWheel.set(speed);
    }

    public double getRPM() {
        return (shooterLeader.getSelectedSensorPosition()*600.0)/2048.0;
    }

}
