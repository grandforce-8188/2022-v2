// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drivetrainArcadeDrive;
import frc.robot.subsystems.*;

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

  public final Climber climber = new Climber();
  public final Drivetrain drivetrain = new Drivetrain();
  public final Intake intake = new Intake();
  public final PowerController powerController = new PowerController();
  public final Shooter shooter = new Shooter();
  public final Feeder feeder = new Feeder();
  public final Limelight limelight = new Limelight();

  public static boolean limelightAim = false;

  Compressor compressor = new Compressor(1, PneumaticsModuleType.REVPH);

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
    compressor.enableDigital();
    intake.extendIntake(true);
    drivetrain.setBraking(true, true);
    limelight.enableLED();
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

  private final XboxController xboxController = Constants.driverXbox.xboxController;
  private final Joystick joystick = Constants.driverJoystick.joystick;
  private double changeShooterSpeed = 0;
  Command driveTheRobot = new drivetrainArcadeDrive(drivetrain, joystick, xboxController);

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    limelight.disableLED();
  }

  @Override
  public void disabledPeriodic() {}

  static long startTime = System.currentTimeMillis();

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    driveTheRobot.cancel();
    drivetrain.setBraking(true, true);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    startTime = System.currentTimeMillis();
    drivetrain.shiftGears(true);

    System.out.println("init run");

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }


  /** This function is called periodically during autonomous. */

  @Override
  public void autonomousPeriodic() {
    intake.extendIntake(false);
    long currentTime = System.currentTimeMillis();
    double timeDifference = currentTime - startTime;
    int autoType = 1;
    System.out.println(currentTime - startTime);
    if (autoType == 1) {
      if (timeDifference > 1500 && timeDifference < 3000) {

        drivetrain.setDrive(-0.9, -0.9);
        intake.spinIntake(-1);
        //feeder.runFeeder();
      }
      else if (timeDifference > 3000 && timeDifference < 4500) {
        drivetrain.setDrive(0, 0);
        //feeder.stopFeeder();
        //intake.spinIntake(0);
        //shooter.setShooterSpeed(4400);
        if (timeDifference < 3200) {
          intake.spinIntake(1);
          feeder.reverseFeeder();
          shooter.runKickWheel(1);
        } else {
          intake.spinIntake(0);
          feeder.stopFeeder();
          shooter.runKickWheel(0);
        }
      }

      //shooter.setShooterSpeed(4400);
      if (timeDifference > 4300 && timeDifference < 10000) {
        drivetrain.setDrive(0, 0);
        limelightAim = true;
        shooter.setShooterSpeed(5800 + changeShooterSpeed);

      }

      if (timeDifference > 7000 && timeDifference < 10000) {

        feeder.runFeeder();
        shooter.runKickWheel(-0.6);
        System.out.println("fire!");
        if (timeDifference > 8000 && timeDifference < 10000){
          intake.spinIntake(-0.6);
          changeShooterSpeed = 300;
}     }

      if (timeDifference > 10000) {
        shooter.stopShooter();
        intake.spinIntake(0);
        feeder.stopFeeder();
        shooter.runKickWheel(0);
        limelightAim = false;
      }
       else if (autoType == 2) {
        if (timeDifference > 5000 && timeDifference < 6000) {
          drivetrain.setDrive(-0.9, -0.9);
        } else if (timeDifference > 6000) {
          shooter.autoShooterSpeed();
          limelightAim = true;
        }
        if (timeDifference > 9000) {
          limelightAim = false;
          intake.spinIntake(-1);
          feeder.runFeeder();
          shooter.runKickWheel(-1);
        }
        if (timeDifference > 12000) {
          intake.spinIntake(0);
        }
      }
    }
  }

  public DoubleSolenoid.Value climberUpPosition;


  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    drivetrain.setBraking(true, true);
    drivetrain.setDefaultCommand(driveTheRobot); //Joystick
    //shooter.setDefaultCommand(new ShooterSetPercentOutput(shooter, 0.0));
    //shooter.setDefaultCommand(new fireShooter(shooter, feeder, new JoystickButton(joystick, 1)));
  }



  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);



  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if(Constants.driverJoystick.winchUpButton.get()) {
      climber.runWinch(1);
    } else if(Constants.driverJoystick.winchDownButton.get()) {
      climber.runWinch(-0.7);
    } else{
      climber.runWinch(0);
    }

    if(Constants.driverXbox.gearshiftHighButton.get()){
      drivetrain.shiftGears(false);
    }
    else if(Constants.driverXbox.gearshiftLowButton.get())
    {
      drivetrain.shiftGears(true);
    }

    if(Constants.driverJoystick.climberPistonUpButton.get()){
      climber.extendClimberPiston();
    }

    if(Constants.driverJoystick.climberPistonDownButton.get()) {
      climber.retractClimberPiston();
    }

    if(Constants.driverXbox.intakePistonUpButton.get()) {
      intake.extendIntake(true);
    } else if (Constants.driverXbox.intakePistonDownButton.get())
    {
      intake.extendIntake(false);
    }

    if(Constants.driverJoystick.shooterRamp.get())
    {
      shooter.autoShooterSpeed();
    }
    else if(Constants.driverJoystick.joystick.getRawButtonReleased(2)){
      shooter.stopShooter();
    }

    if(Constants.driverJoystick.shooterLowGoalButton.get()){
      shooter.setShooterSpeed(2500);
    } else if (Constants.driverJoystick.joystick.getRawButtonReleased(9)){
      shooter.stopShooter();
    }

    if(Constants.driverJoystick.shooterStaticButton.get())
    {
      shooter.setShooterSpeed(5800);
    } else if(Constants.driverJoystick.joystick.getRawButtonReleased(5)){
      shooter.stopShooter();
    }

    if(Constants.driverXbox.intakeForwardButton.get() || Constants.driverJoystick.shooterTrigger.get() || joystick.getRawButton(3)) {
      intake.spinIntake(-1.0);
      feeder.runFeeder();
      if(Constants.driverJoystick.shooterRamp.get() || Constants.driverJoystick.shooterStaticButton.get() ||
              Constants.driverJoystick.shooterLowGoalButton.get())
      {
        shooter.runKickWheel(-1.0);
      }
    } else if(Constants.driverXbox.intakeReverseButton.get()|| Constants.driverJoystick.reverseIntakeButton.get()) {
      intake.spinIntake(1.0);
      feeder.reverseFeeder();
      shooter.runKickWheel(1.0);
    } else {
      intake.spinIntake(0.0);
      feeder.stopFeeder();
      shooter.runKickWheel(0.0);
    }

    if(Constants.driverJoystick.shooterAimButton.get()){
      limelightAim = true;
    } else{
      limelightAim = false;
    }
//    if(Constants.driverJoystick.shooterAimButton.get())
//    {
//      drivetrain.aim();
//    }
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