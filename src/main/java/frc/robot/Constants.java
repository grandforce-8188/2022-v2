// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int PDP = 0;

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
    public static final int srxShooterLeader = 24,
                            srxShooterFollower = 25;
    /////////////////////////////////////////////////////////////////////////////

    //DoubleSolenoids
    public static final int intakeForward = 0,
                            intakeReverse = 1;

    //Saturation Voltage
    public static final double saturationVoltage = 11.0;
}
