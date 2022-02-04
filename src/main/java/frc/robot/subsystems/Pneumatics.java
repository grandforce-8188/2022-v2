package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Pneumatics {
    Compressor compressor = new Compressor(0, PneumaticsModuleType.REVPH);
    PneumaticHub pneumaticHub = new PneumaticHub(1);

    DoubleSolenoid doubleSolenoids[] = {
            new DoubleSolenoid(PneumaticsModuleType.REVPH,0,1), //Gearshifter
    };

    public void shiftDoubleSolenoid(int number, DoubleSolenoid.Value value)
    {
        doubleSolenoids[number].set(value);
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

    public void enableCompressor(Double minPressure, Double maxPressure) {compressor.enableAnalog(minPressure, maxPressure);}

    public double getChannelVoltage(int channel)
    {
        return pneumaticHub.getAnalogVoltage(channel);
    }

    public double getChannelPressure(int channel)
    {
        return pneumaticHub.getPressure(channel);
    }
}
