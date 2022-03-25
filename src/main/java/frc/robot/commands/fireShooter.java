package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class fireShooter extends CommandBase {
    Shooter shooter;
    Feeder feeder;
    JoystickButton button;

    int desiredRPM = 6100;

    public fireShooter(Shooter shooterToFire, Feeder feederToFire, JoystickButton buttonToPress) {
        shooter = shooterToFire;
        feeder = feederToFire;
        button = buttonToPress;

        addRequirements(shooter, feeder);
    }

    public void execute() {
        if(button.get())
        {
            shooter.autoShooterSpeed();
        }
        else
        {
            shooter.autoShooterSpeed();
        }
    }
    //double actualRPM = shooter.getRPM();
//        if(button.getTrigger()){
//            if ( actualRPM > (desiredRPM-50) && actualRPM < (desiredRPM+50) &&
//                    desiredRPM != 0 && button.getTrigger()){
//                feeder.runFeeder();
//                shooter.runKickWheel(1.0);
//                System.out.println("Hello");
//          }
//         else
//          {
//             PIDController pid = new PIDController(0.35, 0, 0.0018, 0.005);
//             shooter.setShooterSpeed(pid.calculate(actualRPM, desiredRPM));
//          }
//        } else {
//            shooter.setShooterSpeed(0.0);
//            feeder.stopFeeder();
//            shooter.runKickWheel(0.0);
//        }


    public void setRPM(int desiredRPM) {
        this.desiredRPM = desiredRPM;
    }
}
