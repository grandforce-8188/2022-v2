package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShooterSetPercentOutput extends CommandBase {
  private final Shooter mShooter;
  private int mOutput;

  public ShooterSetPercentOutput(Shooter shooter, int output) {
    mShooter = shooter;
    mOutput = output;
    addRequirements(mShooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mShooter.setShooterSpeed();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
