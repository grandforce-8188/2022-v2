package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class drivetrainArcadeDrive extends CommandBase {
    private Drivetrain drivetrain;
    private Joystick joystick;
    private XboxController xboxController;

    public drivetrainArcadeDrive(Drivetrain arcadeDrivetrain, Joystick arcadeJoystick, XboxController arcadeXboxController) {
        joystick = arcadeJoystick;
        drivetrain = arcadeDrivetrain;
        xboxController = arcadeXboxController;
        addRequirements(drivetrain);
    }

    public void DrivetrainArcadeDrive(Drivetrain arcadeDrivetrain, Joystick arcadeJoystick) {
    }

    final double speedMultiplier = Constants.DrivetrainConstants.speedMultiplier;

    public void execute() {
        double speed, twist;
        speed = speedMultiplier * xboxController.getRightY(); System.out.print(speed);
        twist = -(speedMultiplier*1.2) * xboxController.getLeftX(); System.out.println(" : " + twist);

        if(Robot.limelightAim) {
            twist = drivetrain.aim();
            System.out.println("AIMING");
        }
        drivetrain.arcadeDrive(speed, twist);

//        double backwards = xboxController.getLeftTriggerAxis();
//        double forwards = -xboxController.getRightTriggerAxis();
//        if(xboxController.getLeftTriggerAxis() > 0.1) {
//            speed = (Math.pow(backwards, 5) + Math.pow(backwards, 3) + backwards)/3;
//        }
//        else{
//            speed = (Math.pow(forwards, 5) + Math.pow(forwards, 3) + forwards)/3;
//        }
//        drivetrain.arcadeDrive(Math.pow(speed, 2)*Math.signum(speed),
//                Math.pow(twist, 2)*Math.signum(twist));        }
    }
}
