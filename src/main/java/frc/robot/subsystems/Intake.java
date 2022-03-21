package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    static WPI_TalonSRX intake = new WPI_TalonSRX(Constants.srxIntake);

    static DoubleSolenoid intakePiston = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.intakePistonFoward, Constants.intakePistonReverse);

    public Intake() {;
    }

    public void spinIntake(double speed) {
        intake.set(speed);
    }

    public void extendIntake(boolean extend) {
        if (extend) {
            intakePiston.set(DoubleSolenoid.Value.kForward);
        } else {
            intakePiston.set(DoubleSolenoid.Value.kReverse);
        }
    }
}
