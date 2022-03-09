package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    static boolean leftBrake = false;
    static boolean rightBrake = false;

    static Pneumatics pneumatics;

    static WPI_TalonFX leftOne = new WPI_TalonFX(Constants.fxLeftOne);
    static WPI_TalonFX leftTwo = new WPI_TalonFX(Constants.fxLeftTwo);
    static WPI_TalonFX leftThree = new WPI_TalonFX(Constants.fxLeftThree);
    static MotorControllerGroup leftMotors = new MotorControllerGroup
            ((MotorController) leftOne, (MotorController) leftTwo, (MotorController) leftThree);

    static WPI_TalonFX rightOne = new WPI_TalonFX(Constants.fxRightOne);
    static WPI_TalonFX rightTwo = new WPI_TalonFX(Constants.fxRightTwo);
    static WPI_TalonFX rightThree = new WPI_TalonFX(Constants.fxRightThree);
    static MotorControllerGroup rightMotors = new MotorControllerGroup
            ((MotorController) rightOne, (MotorController) rightTwo, (MotorController) rightThree);

    static DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

    public Drivetrain(Pneumatics pNeumatics) {
        pneumatics = pNeumatics;
        rightMotors.setInverted(true);
    }

    public void arcadeDrive(double move, double rotate) {
        final double MIN_MOVE_THRESHOLD = 0.07;
        final double MIN_ROTATE_THRESHOLD = 0.07;
        
        if (Math.abs(move) < MIN_MOVE_THRESHOLD) {
            move = 0.0;
        }

        if (Math.abs(rotate) < MIN_ROTATE_THRESHOLD) {
            rotate = 0.0;
        }

        drive.arcadeDrive(move, rotate);
    }

    /**
     * Sets the drivetrain based on the left and right motors
     * @param left speed of left motors
     * @param right speed of right motors
     */
    public void setDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    public void setBraking(boolean left, boolean right) {
        leftBrake = left;
        rightBrake = right;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (leftBrake & leftOne.get() == 0) {
            leftOne.setNeutralMode(NeutralMode.Brake);
        }

        if (rightBrake & rightOne.get() == 0) {
            rightOne.setNeutralMode(NeutralMode.Brake);
        }
    }

    public Double getLeftRPM()
    {
        return (leftOne.getSelectedSensorVelocity()*600/2048);
    }

    public Double getRightRPM()
    {
        return (rightOne.getSelectedSensorVelocity()*600)/2048;
    }

    public void disableDrivetrain()
    {
        leftOne.disable();
        leftTwo.disable();
        leftThree.disable();
        rightOne.disable();
        rightTwo.disable();
        rightThree.disable();
    }

    public void disableMotorPair(int motorPair)
    {
        switch(motorPair)
        {
            case 1:
                leftThree.disable();
                rightThree.disable();
                break;
            case 2:
                leftTwo.disable();
                rightTwo.disable();
                break;
        }
    }

    public void setVoltageCompensation(boolean enabled)
    {
        leftOne.configVoltageCompSaturation(Constants.saturationVoltage);
        leftOne.enableVoltageCompensation(enabled);

        leftTwo.configVoltageCompSaturation(Constants.saturationVoltage);
        leftTwo.enableVoltageCompensation(enabled);

        leftThree.configVoltageCompSaturation(Constants.saturationVoltage);
        leftThree.enableVoltageCompensation(enabled);

        rightOne.configVoltageCompSaturation(Constants.saturationVoltage);
        rightOne.enableVoltageCompensation(enabled);

        rightTwo.configVoltageCompSaturation(Constants.saturationVoltage);
        rightTwo.enableVoltageCompensation(enabled);

        rightThree.configVoltageCompSaturation(Constants.saturationVoltage);
        rightThree.enableVoltageCompensation(enabled);
    }
}