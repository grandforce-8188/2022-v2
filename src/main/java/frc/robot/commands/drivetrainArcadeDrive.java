package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class drivetrainArcadeDrive extends CommandBase {
    private Drivetrain drivetrain = new Drivetrain();
    private Joystick joystick = new Joystick(1);

    public drivetrainArcadeDrive(Drivetrain arcadeDrivetrain, Joystick arcadeJoystick) {
        joystick = arcadeJoystick;
    }

    public void DrivetrainArcadeDrive(Drivetrain arcadeDrivetrain, Joystick arcadeJoystick) {
    }

    public void execute() {
        double twist = Math.pow(joystick.getTwist(), 3);

        drivetrain.arcadeDrive(joystick.getY(), twist);
    }
}
