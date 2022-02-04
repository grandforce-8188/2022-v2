package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    static WPI_TalonFX climberMotor = new WPI_TalonFX(Constants.fxClimber);
    static WPI_TalonSRX pivotMotor = new WPI_TalonSRX(Constants.srxPivot);

    public static double climberPosition = 0;
    private static double oldClimberPosition = 0;

    public static int pivotPosition = 0;
    private static double oldPivotPosition = 0;

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

    public void disablePivot()
    {
        pivotMotor.disable();
    }

    public void setClimberVoltageCompensation(boolean enabled)
    {
        climberMotor.configVoltageCompSaturation(Constants.saturationVoltage);
        climberMotor.enableVoltageCompensation(enabled);
    }

    public void setPivotVoltageCompensation(boolean enabled)
    {
        climberMotor.configVoltageCompSaturation(Constants.saturationVoltage);
        climberMotor.enableVoltageCompensation(enabled);
    }
}
