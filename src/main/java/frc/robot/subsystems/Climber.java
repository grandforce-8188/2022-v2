package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    static Pneumatics pneumatics;
    static WPI_TalonFX climberMotor = new WPI_TalonFX(Constants.fxClimber);
    static newDoubleSolenoid pivotPiston = new newDoubleSolenoid(PneumaticsModuleType.REVPH, Constants.climberPistonFoward, Constants.climberPistonReverse, "climber");

    DigitalInput magLimit = new DigitalInput(1);

    public static double climberPosition = 0;
    private static double oldClimberPosition = 0;

    public Climber(Pneumatics pNeumatics)
    {
        pneumatics = pNeumatics;
        climberMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        double newClimberPosition = climberMotor.getSelectedSensorPosition();

        if (newClimberPosition < oldClimberPosition)
        {
            if(newClimberPosition < 100 & oldClimberPosition > 1948)
            {
                climberPosition += ((2048-oldClimberPosition) + newClimberPosition);
            }
            else
            {
                climberPosition -= (oldClimberPosition - newClimberPosition);
            }
        }

        if (newClimberPosition > oldClimberPosition)
        {
            if(oldClimberPosition < 100 & newClimberPosition > 1948)
            {
                climberPosition -= ((2048-newClimberPosition) + oldClimberPosition);
            }
            else
            {
                climberPosition += (newClimberPosition - oldClimberPosition);
            }
        }

        oldClimberPosition = newClimberPosition;
    }

    public void setClimberPosition(int Position)
    {
        PIDController pidController = new PIDController(0.35, 0, 0.0018, 0.005);
        climberMotor.set(pidController.calculate(climberPosition, Position));
    }

    public void runWinch(double speed)
    {
        if(!(magLimit.get() && speed > 0))
        {
            climberMotor.set(speed*0.6);
        }
        else if(magLimit.get() && speed > 0)
        {
            climberMotor.set(0);
        }
    }

    public void extendClimberPiston()
    {
        pivotPiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void retractClimberPiston()
    {
        pivotPiston.set(DoubleSolenoid.Value.kForward);
    }

    public Double getClimberRPM()
    {
        return (climberMotor.getSelectedSensorVelocity()*600)/2048;
    }

    public Double getPivotRPM()
    {
        return (climberMotor.getSelectedSensorVelocity()*600)/2048;
    }

    public void disableClimber()
    {
        climberMotor.disable();
    }

    public void setClimberVoltageCompensation(boolean enabled)
    {
        climberMotor.configVoltageCompSaturation(Constants.saturationVoltage);
        climberMotor.enableVoltageCompensation(enabled);
    }
}
