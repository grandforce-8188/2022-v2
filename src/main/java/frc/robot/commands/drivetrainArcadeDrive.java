package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

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

    public void execute() {
        double speed = 0.8*xboxController.getRightY();
        double twist = -0.8*xboxController.getLeftX();
        //
  //      if(xboxController.getLeftTriggerAxis() > 0.1) {
   //         speed = xboxController.getLeftTriggerAxis();
   //     }
   //     else{
   //         speed = -xboxController.getRightTriggerAxis();
      //  }

        drivetrain.arcadeDrive(Math.pow(speed, 2)*Math.signum(speed),
                Math.pow(twist, 2)*Math.signum(twist));
    }
}
