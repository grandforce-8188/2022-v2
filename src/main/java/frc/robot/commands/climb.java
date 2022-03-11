//package frc.robot.commands;
//
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants;
//import frc.robot.subsystems.Climber;
//
//public class climb extends CommandBase {
//    Climber climber;
//    //Motor unwinds
//    //Motor winds
//
//    //Piston Extends
//    //Motor unwinds
//    //Piston Retract
//    //Motor Winds
//
//    //Piston Extends
//    //Motor winds
//    //Piston Retracts
//
//    public climb(Climber newClimber) {
//        climber = newClimber;
//    }
//
//    public void exectute(){ //order 66
//        climber.setClimberPosition((int) Constants.climberConstants.maxHeight);
//        climber.setClimberPosition(0);
//
//        climber.extendClimberPiston();
//        climber.setClimberPosition((int) Constants.climberConstants.maxHeight);
//        climber.retractClimberPiston();
//        climber.setClimberPosition(0);
//
//        climber.extendClimberPiston();
//        climber.setClimberPosition(0);
//        climber.retractClimberPiston();
//    }
//}
