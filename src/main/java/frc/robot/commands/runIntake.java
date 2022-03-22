package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class runIntake extends CommandBase {

    Intake intake;
    Feeder feeder;
    JoystickButton forwardButton;
    JoystickButton reverseButton;

    Shooter shooter = new Shooter();

    public runIntake(Intake intakeToRun, Feeder feederToRun, JoystickButton forwardButtonToPress, JoystickButton reverseButtonToPress) {
            intake = intakeToRun;
            feeder = feederToRun;
            forwardButton = forwardButtonToPress;
            reverseButton = reverseButtonToPress;

            addRequirements(intake, feeder);
        }

    public void execute()
    {
        if(forwardButton.get()) {
            intake.spinIntake(-1.0);
            feeder.runFeeder();
        } else if(reverseButton.get()) {
            intake.spinIntake(1.0);
            feeder.reverseFeeder();;
            shooter.runKickWheel(1.0);
        } else {
            intake.spinIntake(0.0);
            feeder.stopFeeder();
        }
    }
}
