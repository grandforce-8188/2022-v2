package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;

public class runIntake extends CommandBase {

    Intake intake;
    Feeder feeder;
    XboxController button;

    public runIntake(Intake intakeToRun, Feeder feederToRun, XboxController buttonToPress) {
            intake = intakeToRun;
            feeder = feederToRun;
            button = buttonToPress;

            addRequirements(intake);
            addRequirements(feeder);
        }

    public void execute()
    {
        if(button.getBButton()) {
            intake.spinIntake(1.0);
            feeder.runFeeder();
        }
        else {
            intake.spinIntake(0.0);
            feeder.stopFeeder();
        }
    }
}
