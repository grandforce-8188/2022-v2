package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PowerController extends SubsystemBase {
    static PowerDistribution powerDistribution = new PowerDistribution(Constants.PDP, PowerDistribution.ModuleType.kRev);

    public Double getVoltage() {
        return powerDistribution.getVoltage();
    }

    public Double getTemperature() {
        return powerDistribution.getTemperature();
    }

    public Double getTotalCurrent() {
        return powerDistribution.getTotalCurrent();
    }

    public Double getChannelCurrent(int channel) {
        return powerDistribution.getCurrent(channel);
    }

    public Double getTotalEnergy() {
        return powerDistribution.getTotalEnergy();
    }
}
