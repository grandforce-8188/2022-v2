// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /* Naming Conventions
        Electronics start at index position 0
        Talon FXs start at index position 10
        Talon SRXs start at index position 20
        Victor SPXs start at index position 30
     */

    //Electronics
    public static final int PCM = 1,
                            PDP = 2;

    public static final int gearShifterForward = 2,
                            gearShifterReverse = 3,
                            intakePistonFoward = 0,
                            intakePistonReverse=1,
                            climberPistonFoward=4,
                            climberPistonReverse=5;


    //Drivetrain Motors
    public static final int fxLeftOne = 10,
                            fxLeftTwo = 11,
                            fxLeftThree = 12,
                            fxRightOne = 13,
                            fxRightTwo = 14,
                            fxRightThree = 15;

    //Climber Vertical Motor
    public static final int fxClimber = 16;

    //Climber Pivot Motor
    public static final int srxPivot = 20;

    //Intake Motors
    public static final int srxIntake = 21,
                            srxOmniLeader = 22,
                            srxOmniFollower = 23;

    //Shooter Motors
    public static final int fxShooterLeader = 17,
                            fxShooterFollower = 18;

    public static final int spxKickWheel = 24;


    /////////////////////////////////////////////////////////////////////////////

    //DoubleSolenoids

    //Saturation Voltage
    public static final double saturationVoltage = 11.0;

    public static final class AutoConstants {
        public static final double autonomousMaxSpeed = 3;
        public static final double autonomousMaxAcceleration = 3;

        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        public static final double ramsetB = 2;
        public static final double ramsetZeta = 0.7;
    }

    public static final class DrivetrainConstants {
        static final Double wheelDistance = 0.568;
        public static final DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(wheelDistance);
    }

    public static final class climberConstants {
        public static final double maxHeight = 12000;
    }

    public static final class limelightConstants {
        public static final double desiredX = 0.0;
        public static final double desiredY = 0.0;
    }

    public static final class driverXbox {
        public static final XboxController xboxController = new XboxController(0);

        public static final JoystickButton intakePistonUpButton = new JoystickButton(xboxController, XboxController.Button.kY.value);
        public static final JoystickButton intakePistonDownButton = new JoystickButton(xboxController, XboxController.Button.kA.value);

        public static final JoystickButton intakeForwardButton = new JoystickButton(xboxController, XboxController.Button.kB.value);
        public static final JoystickButton intakeReverseButton = new JoystickButton(xboxController, XboxController.Button.kX.value);

        public static final JoystickButton gearshiftHighButton = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
        public static final JoystickButton gearshiftLowButton = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);
    }

    public static final class driverJoystick {
        public static final Joystick joystick = new Joystick(1);

        public static final JoystickButton shooterTrigger = new JoystickButton(joystick, 1); //Fires the shooter
        public static final JoystickButton shooterRamp = new JoystickButton(joystick, 2); //Ramps the shooter up to speed

        public static final JoystickButton winchUpButton = new JoystickButton(joystick, 12); //Winch up
        public static final JoystickButton winchDownButton = new JoystickButton(joystick, 11); //Winch down

        public static final JoystickButton climberPistonUpButton = new JoystickButton(joystick, 8);
        public static final JoystickButton climberPistonDownButton = new JoystickButton(joystick, 7); //Piston Up

        public static final JoystickButton intakeButton = new JoystickButton(joystick, 3); //Intake
        public static final JoystickButton reverseIntakeButton = new JoystickButton(joystick, 4); //Intake reverse

        public static final JoystickButton shooterStaticButton = new JoystickButton(joystick, 5);
        public static final JoystickButton shooterLowGoalButton = new JoystickButton(joystick, 9);

        public static final JoystickButton shooterAimButton = new JoystickButton(joystick, 6);
    }
    }