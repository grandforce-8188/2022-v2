package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.PowerController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {

    static final PowerController PDP = new PowerController();
    static final Pneumatics pneumatics = new Pneumatics();
    static final Limelight limelight = new Limelight();
    static final Drivetrain drivetrain = new Drivetrain(pneumatics);

    public static void writeLog() {
        String logFile = "";

        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        LocalDateTime now = LocalDateTime.now();

        logFile += ": \n" +
                "Start Time: " +
                time.format(now) +
                "\n          " +
                "PDP: " +
                        String.valueOf(PDP.getVoltage()) + "v " +
                        String.valueOf(PDP.getTotalCurrent()) + "A " +
                        String.valueOf(PDP.getTotalEnergy()) + "W " +
                        String.valueOf(PDP.getTemperature()) + "C " ;
                for(int i = 0; i < 15; i++) {
                    logFile += "               " + String.valueOf(i) + ":"
                            + PDP.getChannelCurrent(i) + "A \n";
                }
                logFile += "\n          Compressor: " +
                        String.valueOf(pneumatics.getCompressorVoltage()) + "v " +
                        String.valueOf(pneumatics.getCompressorCurrent()) + "A " +
                        String.valueOf(pneumatics.getCompressorPressure() ) + "W " +
                        "\n          Pneumatics: \n";

               for(int i = 0; i < 15; i++) {
                    logFile += "               " + String.valueOf(i) + ":"
                            + pneumatics.getChannelVoltage(i) + "V \n"
                            + pneumatics.getChannelPressure(i) + "PSI \n";
                }

                logFile += "\n          Limelight: " +
                        String.valueOf(limelight.getLimelightTarget()) +
                        String.valueOf(limelight.getLimelightX()) + "x" +
                        String.valueOf(limelight.getLimelightY()) + "y" +
                        String.valueOf(limelight.getLimelightArea()) + "area" +
                        String.valueOf(limelight.getLimelightSkew()) + "skew" +
                        String.valueOf(limelight.getLimelightDelay() ) + "l" +

                        "\n          Drivetrain: \n" +

                        "               L1: " + logFX(Constants.fxLeftOne) +
                        "\n               L2: " + logFX(Constants.fxLeftTwo) +
                        "\n               L3: " + logFX(Constants.fxLeftThree) +
                        "\n               R1: " + logFX(Constants.fxRightOne) +
                        "\n               R2: " + logFX(Constants.fxRightTwo) +
                        "\n               R3: " + logFX(Constants.fxRightThree) +
                        "\n\n          Climber:\n" +
                        "               verticalMotor: " + logFX(Constants.fxClimber) +
                        "\n               pivotMotor: " + logSRX(Constants.srxPivot) +
                        "\n\n          Shooter:\n" +
                        "               shooterLeader"+ logFX(Constants.fxShooterLeader)+
                        "\n               shooterFollower"+ logFX(Constants.fxShooterFollower)+
                        "\n\n          Intake:\n" +
                        "               intakeMotor: " + logSRX(Constants.srxIntake) +
                        "\n               intakeOmniLeader: " + logSRX(Constants.srxOmniLeader) +
                        "\n               intakeOmniFollower: " + logSRX(Constants.srxOmniFollower) +
                        //"\n\n          Shooter:\n";

                        "\nEnd Time: "  + time.format(now);
               writeFile(logFile);
    }

    static void writeFile(String logFile)
    {
        File f;
        BufferedWriter bw;
        FileWriter fw = null;

        try {
            f = new File(System.getenv().get("HOME") + "/8188/Logs/" + Robot.startDate + ".log");
            if(!f.exists()){
                f.createNewFile();
            }
            fw = new FileWriter(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bw = new BufferedWriter(fw);

        try {
            bw.write(logFile);
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String logFX(int FXCanID)
    {
        WPI_TalonFX talonFX = new WPI_TalonFX(FXCanID);

         return String.valueOf(talonFX.getBusVoltage()) +
            "vBus " +
            String.valueOf(talonFX.getMotorOutputVoltage()) +
            "vOut " +
            String.valueOf(talonFX.getSupplyCurrent()) +
            "A " +
            String.valueOf(talonFX.getTemperature()) +
            "C ";
    }

    private static String logSRX(int SRXCanID)
    {
        WPI_TalonSRX talonSRX = new WPI_TalonSRX(SRXCanID);

        return String.valueOf(talonSRX.getBusVoltage()) +
                "vBus " +
                String.valueOf(talonSRX.getMotorOutputVoltage()) +
                "vOut " +
                String.valueOf(talonSRX.getSupplyCurrent()) +
                "A " +
                String.valueOf(talonSRX.getTemperature()) +
                "C ";
    }
}
