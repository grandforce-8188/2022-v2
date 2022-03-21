package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class runIntake extends CommandBase {

    Intake intake;
    Feeder feeder;
    XboxController button;
    Shooter shooter = new Shooter();

    public runIntake(Intake intakeToRun, Feeder feederToRun, XboxController buttonToPress) {
            intake = intakeToRun;
            feeder = feederToRun;
            button = buttonToPress;

            addRequirements(intake, feeder);
        }

    public void execute()
    {
        if(button.getBButton()) {
            intake.spinIntake(-1.0);
            feeder.runFeeder();
        } else if(button.getXButton()) {
            intake.spinIntake(1.0);
            feeder.reverseFeeder();;
            shooter.runKickWheel(1.0);
        } else {
            intake.spinIntake(0.0);
            feeder.stopFeeder();
        }
    }
}
