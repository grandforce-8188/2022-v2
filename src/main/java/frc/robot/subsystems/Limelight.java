package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase
{
    private static final NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");

    private static NetworkTableEntry limelightLED = networkTable.getEntry("ledMode"); //LED mode
    private static NetworkTableEntry limelightPipeline = networkTable.getEntry("pipeline"); //Pipeline used for vision processing
    private static NetworkTableEntry limelightSnapshot = networkTable.getEntry("snapshot");
    //Enables taking snapshots of the image every two seconds
    private static NetworkTableEntry limelightCamMode = networkTable.getEntry("camMode");
    //Sets camera mode between driver and vision processing

    public static double limelightTarget = 0.0; //Whether The Limelight Has A Target
    public static double limelightX = 0.0; //Horizontal Offset From Cross-hair To Target
    public static double limelightY = 0.0; //Vertical Offset From Cross-hair To Target
    public static double limelightA = 0.0; //Target Area
    public static double limelightDelay = 0.0; //Delay In Milliseconds

    /**
     * Gets whether the limelight has a target
     * @return Whether the limelight has a target
     */
    public double getLimelightTarget() {return networkTable.getEntry("tv").getDouble(0.0);}

    /**
     * Gets the horizontal offset from the crosshair to the target
     * @return The horizontal offset from the crosshair to the target
     */
    public double getLimelightX() {return networkTable.getEntry("tx").getDouble(0.0);}

    /**
     * Gets the vertical offset from the crosshair to the target
     * @return The vertical offset from the crosshair to the target
     */
    public double getLimelightY() {return networkTable.getEntry("ty").getDouble(0.0);}

    public static double getLimelightArea() {return networkTable.getEntry("ta").getDouble(0.0);}

    public static double getLimelightSkew() {return networkTable.getEntry("ts").getDouble(0.0);}

    /**
     * Gets the delay in milliseconds
     * @return The delay in milliseconds
     */
    public double getLimelightDelay() {return networkTable.getEntry("tl").getDouble(0.0);}

    /**
     * Enables the limelight LED
     */
    public void enableLED()
    {
        limelightLED.setNumber(3);
    }

    /**
     * Disables the limelight LED
     */
    public void disableLED()
    {
        limelightLED.setNumber(1);
    }

    /**
     * Blinks the limelight LED
     */
    public void blinkLED()
    {
        limelightLED.setNumber(2);
    }

    /**
     * Sets the pipeline to be used for vision processing
     * @param pipeline The pipeline between 0 and 9 to be used
     */
    public void setPipeline(int pipeline)
    {
        limelightPipeline.setNumber(pipeline);
    }

    /**
     * Enables image processing
     */
    public void enableImageProcessing() {limelightCamMode.setNumber(0);}

    /**
     * Disables image processing
     */
    public void disableImageProcessing()
    {
        limelightCamMode.setNumber(1);
    }

    /**
     * Enables the taking of snapshots of the image every two seconds
     */
    public void enableSnapshot() {limelightSnapshot.setNumber(1);}

    /**
     * Disables the taking of snapshots of the image every two seconds
     */
    public void disableSnapshot()
    {
        limelightSnapshot.setNumber(0);
    }
}
