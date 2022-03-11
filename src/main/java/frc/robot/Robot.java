// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrainArcadeDrive;
import frc.robot.commands.fireShooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PowerController;
import frc.robot.subsystems.Shooter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  static DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
  static LocalDateTime now = LocalDateTime.now();
  public static String startDate = date.format(now);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    setNetworkTablesFlushEnabled(true);
    //RobotController.setBrownoutVoltage(5.5);
  }

  static int brownoutCount = 0;
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    if(RobotController.isBrownedOut()) {
      brownoutCount += 1;
      drivetrain.disableMotorPair(brownoutCount);
    }
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    drivetrain.setDefaultCommand(new drivetrainArcadeDrive(drivetrain, joystick, xboxController)); //Joystick
    shooter.setDefaultCommand(new fireShooter(shooter, feeder, new JoystickButton(joystick, 1)));
  }

  public final Climber climber = new Climber();
  public final Drivetrain drivetrain = new Drivetrain();
  public final Intake intake = new Intake();
  public final PowerController powerController = new PowerController();
  public final Shooter shooter = new Shooter();
  public final Feeder feeder = new Feeder();


  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final XboxController xboxController = new XboxController(0);
  private final Joystick joystick = new Joystick(1);

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    JoystickButton winchUpButton = new JoystickButton(joystick, 11); //Winch up
    JoystickButton winchDownButton = new JoystickButton(joystick, 12); //Winch down

    JoystickButton pistonUpButton = new JoystickButton(joystick, 7); //Piston Up
    JoystickButton pistonDownButton = new JoystickButton(joystick, 8); //Piston Down

    if(winchUpButton.get()) {
      climber.runWinch(1);
    } else if(winchDownButton.get()) {
      climber.runWinch(-1);
    } else{
      climber.runWinch(0);
    }

    if(pistonUpButton.get()) {
      climber.extendClimberPiston();
    } else if(pistonDownButton.get()) {
      climber.retractClimberPiston();
    }

    if(xboxController.getRawButton(2))
    {
      intake.extendIntake(true);
    } else if (xboxController.getRawButton(3))
    {
      intake.extendIntake(false);
    }

    if(xboxController.getRawButton(4)) {
        intake.spinIntake(-1.0);
        feeder.runFeeder();
    } else if (xboxController.getRawButton(1)) {
        intake.spinIntake(1.0);
        feeder.runFeeder();
    }
    else
    {
      intake.spinIntake(0.0);
      feeder.stopFeeder();
    }

    if(joystick.getRawButton(1))
    {
      shooter.runKickWheel(-1.0);
      feeder.runFeeder();
    }
    else if(!joystick.getRawButton(1))
    {
      shooter.runKickWheel(0.0);
      feeder.stopFeeder();
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
