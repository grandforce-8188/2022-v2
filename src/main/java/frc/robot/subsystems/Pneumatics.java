package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;

public class Pneumatics {
    Compressor compressor = new Compressor(1, PneumaticsModuleType.REVPH);
    PneumaticHub pneumaticHub = new PneumaticHub();

    public Pneumatics(){
        enableCompressor(60.0, 120.0);
        doubleSolenoids[1].toggle();
        doubleSolenoids[2].toggle();
    }

    newDoubleSolenoid[] doubleSolenoids = {
            new newDoubleSolenoid(PneumaticsModuleType.REVPH,
                    Constants.gearShifterFoward,Constants.gearShifterReverse, "gearshifter"), //Gearshifter
            new newDoubleSolenoid(PneumaticsModuleType.REVPH,
                    Constants.intakePistonFoward,Constants.intakePistonReverse, "intake"), //Intake
            new newDoubleSolenoid(PneumaticsModuleType.REVPH,
                    Constants.climberPistonFoward,Constants.climberPistonReverse, "climber"), //Climber

    };

    public void shiftDoubleSolenoid(int number, DoubleSolenoid.Value value)
    {
        doubleSolenoids[number].set(value);
    }

    public newDoubleSolenoid getSolenoid(int number)
    {
        return doubleSolenoids[number];
    }

    public Double getCompressorVoltage()
    {
        return compressor.getAnalogVoltage();
    }

    public Double getCompressorCurrent()
    {
        return compressor.getCurrent();
    }

    public Double getCompressorPressure()
    {
        return compressor.getPressure();
    }

    public void disableCompressor()
    {
        compressor.disable();
    }

    public void enableCompressor(Double minPressure, Double maxPressure) {
        compressor.enableDigital(); //.enableAnalog(minPressure, maxPressure);
        //System.out.println("Compressor");
    }

    public double getChannelVoltage(int channel)
    {
        return pneumaticHub.getAnalogVoltage(channel);
    }

    public double getChannelPressure(int channel)
    {
        return pneumaticHub.getPressure(channel);
    }
}
class newDoubleSolenoid extends DoubleSolenoid{
    public static String key;

    public newDoubleSolenoid(PneumaticsModuleType moduleType, int forwardChannel, int reverseChannel, String solenoidKey) {
        super(moduleType, forwardChannel, reverseChannel);
        key = solenoidKey;
    }

    public String getKey()
    {
       return key;
    }
}
