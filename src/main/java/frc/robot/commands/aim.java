package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pneumatics;

public class aim extends CommandBase {
    Limelight limelight;
    Drivetrain drivetrain = new Drivetrain(new Pneumatics());

    PIDController pidController = new PIDController(0.35, 0, 0.0018, 0.005);


    public void execute()
    {
        drivetrain.arcadeDrive(0, pidController.calculate(limelight.getLimelightX(), Constants.limelightConstants.desiredX));
    }
}
