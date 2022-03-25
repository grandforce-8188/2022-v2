package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.equations.shooterVelocity;

public class Shooter extends SubsystemBase {
    WPI_TalonFX shooterLeader = new WPI_TalonFX(Constants.fxShooterLeader);
    //WPI_TalonFX shooterFollower = new WPI_TalonFX(Constants.fxShooterFollower);

    WPI_VictorSPX kickWheel = new WPI_VictorSPX(Constants.spxKickWheel);
    Limelight limelight = new Limelight();

    shooterVelocity velocity = new shooterVelocity();

    public Shooter() {
        //shooterFollower.follow(shooterLeader);
        //shooterFollower.setInverted(true);
        shooterLeader.enableVoltageCompensation(true);

        shooterLeader.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
                0,
                30);

        shooterLeader.configNominalOutputForward(0, 30);
        shooterLeader.configNominalOutputReverse(0, 30);
        shooterLeader.configPeakOutputForward(1, 30);
        shooterLeader.configPeakOutputReverse(-1, 30);

        shooterLeader.config_kP(0, 0.1, 30);
        shooterLeader.config_kI(0, 0.0, 30);
        shooterLeader.config_kD(0, 5, 30);

        kickWheel.setNeutralMode(NeutralMode.Coast);
    }

    public void setShooterSpeed(double RPM){
        shooterLeader.set(TalonFXControlMode.Velocity, (RPM*2048)/600);
                //(velocity.calculateEquation(limelight.getLimelightY(), limelight.getLimelightTarget())*2048)/600);
    }

    public void autoShooterSpeed() {
        limelight.enableLED();
        double rpm = 6000.0;
        shooterLeader.set(TalonFXControlMode.Velocity, //(rpm*2048)/600);
                (velocity.calculateEquation(limelight.getLimelightY(), limelight.getLimelightTarget())*2048)/600);
    }

    public void stopShooter(){
        shooterLeader.set(0.0);
        //limelight.disableLED();
    }

    public void runKickWheel(double speed) {
        kickWheel.set(speed);
    }

    public double getRPM() {
        return (shooterLeader.getSelectedSensorPosition()*600.0)/2048.0;
    }
}
