// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.Command;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //public final Pneumatics pneumatics = new Pneumatics();

  // public final Climber climber = new Climber(pneumatics);
  // public final Drivetrain drivetrain = new Drivetrain(pneumatics);
  // public final Intake intake = new Intake(pneumatics);
  // public final Limelight limelight = new Limelight();
  // public final PowerController powerController = new PowerController();
  // public final Shooter shooter = new Shooter();
  // public final Feeder feeder = new Feeder();


  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final XboxController xboxController = new XboxController(0);
  private final Joystick joystick = new Joystick(1);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    //drivetrain.setDefaultCommand(new drivetrainArcadeDrive(drivetrain, joystick)); //Joystick
    // intake.setDefaultCommand(new runIntake(intake, feeder, xboxController)); //Xbox B
    // shooter.setDefaultCommand(new fireShooter(shooter, feeder, joystick)); //Joystick trigger

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //JoystickButton yButton = new JoystickButton(xboxController, 3);
    //yButton.whenPressed(new climb(climber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create a voltage constraint to ensure we don't accelerate too fast
//    var autoVoltageConstraint =
//            new DifferentialDriveVoltageConstraint(
//                    new SimpleMotorFeedforward(DriveConstants.ksVolts,
//                            DriveConstants.kvVoltSecondsPerMeter,
//                            DriveConstants.kaVoltSecondsSquaredPerMeter),
//                    DriveConstants.kDriveKinematics,
//                    10);
//
//    //Create config for trajectory
//    TrajectoryConfig config =
//            new TrajectoryConfig(Constants.AutoConstants.autonomousMaxSpeed,
//                    Constants.AutoConstants.autonomousMaxAcceleration)
//                    // Add kinematics to ensure max speed is actually obeyed
//                    .setKinematics(Constants.DrivetrainConstants.driveKinematics);
//                    // Apply the voltage constraint
//                    .addConstraint(autoVoltageConstraint);
//
//    /// Pathwaever ////////////////////////////////
//    Trajectory trajectory = new Trajectory();
//    try {
//      File trajectoryJSON = new File(
//              Filesystem.getDeployDirectory().toPath().resolve(
//                      System.getenv().get("HOME") + "/8188/Paths/" + SmartDashboard.getString("Auto Selector", "Do Nothing"))
//                      .toString()
//      );
//      Scanner myReader = new Scanner(trajectoryJSON);
//      while (myReader.hasNextLine()) {
//        String data = myReader.nextLine();
//        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve("paths/output/" + data.substring(0, data.length() - 5)+".wpilib.json");
//        Trajectory temp_trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
//        trajectory = trajectory.concatenate(temp_trajectory);
//      }
//      myReader.close();
//      Field2d m_fieldSim = (Field2d) SmartDashboard.getData("Field");
//      m_fieldSim.getObject("traj").setTrajectory(trajectory);
//    } catch (IOException ex) {
//      DriverStation.reportError("Unable to open trajectory: " + SmartDashboard.getString("Auto Selector", "Do Nothing"), ex.getStackTrace());
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
//    /////////////////////////////////////
//
//    /// Pathplanner ////////////////////
//    // Trajectory trajectory = new Trajectory();
//    // try {
//    //   String m_auto = SmartDashboard.getString("Auto Selector", "Do Nothing");
//    //   trajectory = PathPlanner.loadPath(m_auto, 3, 2);
//    //   Field2d m_fieldSim = (Field2d) SmartDashboard.getData("Field");
//    //   m_fieldSim.getObject("traj").setTrajectory(trajectory);
//    // } catch (Exception ex) {
//    //   DriverStation.reportError("Unable to open trajectory: ", ex.getStackTrace());
//    //   String m_auto = "Do Nothing";
//    //   trajectory = PathPlanner.loadPath(m_auto, 3, 2);
//    //   Field2d m_fieldSim = (Field2d) SmartDashboard.getData("Field");
//    //   m_fieldSim.getObject("traj").setTrajectory(trajectory);
//    // }
//    /////////////////////////////////////
//
//
//
//    RamseteCommand ramseteCommand = new RamseteCommand(
//            trajectory,
//            m_Drivetrain::getPose,
//            new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
//            new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter, DriveConstants.kaVoltSecondsSquaredPerMeter),
//            DriveConstants.kDriveKinematics,
//            m_Drivetrain::getWheelSpeeds,
//            new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
//            new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
//            // RamseteCommand passes volts to the callback
//            m_Drivetrain::tankDriveVolts,
//            m_Drivetrain
//    );
//
//    // Reset odometry to the starting pose of the trajectory.
//    m_Drivetrain.resetOdometry(trajectory.getInitialPose());
//
//    // Run path following command, then stop at the end.
//    return ramseteCommand.andThen(() -> m_Drivetrain.tankDriveVolts(0, 0));
    return null;
  }
}
