package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    static WPI_TalonSRX intake = new WPI_TalonSRX(Constants.srxIntake);

    static WPI_TalonSRX omniLeader = new WPI_TalonSRX(Constants.srxOmniLeader);
    static WPI_TalonSRX omniFollower = new WPI_TalonSRX(Constants.srxOmniFollower);

    static DoubleSolenoid intakePiston = new DoubleSolenoid
            (PneumaticsModuleType.REVPH, Constants.intakeForward, Constants.intakeReverse);

    public Intake() {
        omniFollower.setInverted(true);
        omniFollower.follow(omniLeader);
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
