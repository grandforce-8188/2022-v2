package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    static Pneumatics pneumatics;

    static WPI_TalonSRX intake = new WPI_TalonSRX(Constants.srxIntake);

    static CANSparkMax omniLeader = new CANSparkMax(Constants.srxOmniLeader, MotorType.kBrushless);
    static CANSparkMax omniFollower = new CANSparkMax(Constants.srxOmniFollower, MotorType.kBrushless);

    static DoubleSolenoid intakePiston = new newDoubleSolenoid(PneumaticsModuleType.REVPH, Constants.intakePistonFoward, Constants.intakePistonReverse, "intake");

    public Intake(Pneumatics pNeumatics) {
        omniFollower.setInverted(true);
        omniFollower.follow(omniLeader);

        pneumatics = pNeumatics;
    }

    public void spinIntake(double speed) {
        intake.set(speed);
        omniLeader.set(0.75);
    }

    public void extendIntake(boolean extend) {
        if (extend) {
            intakePiston.set(DoubleSolenoid.Value.kForward);
        } else {
            intakePiston.set(DoubleSolenoid.Value.kReverse);
        }
    }
}
