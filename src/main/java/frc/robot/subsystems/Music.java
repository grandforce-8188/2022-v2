package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;
import frc.robot.Constants;

public class Music {
    Orchestra orchestra = new Orchestra();

    WPI_TalonFX violinPrimo = new WPI_TalonFX(Constants.fxLeftOne);
    WPI_TalonFX violinSecundo = new WPI_TalonFX(Constants.fxLeftTwo);
    WPI_TalonFX altoViola = new WPI_TalonFX(Constants.fxLeftThree);
    WPI_TalonFX violinCello = new WPI_TalonFX(Constants.fxRightOne);
    WPI_TalonFX contrabBasso = new WPI_TalonFX(Constants.fxRightTwo);
    WPI_TalonFX cembalo = new WPI_TalonFX(Constants.fxRightThree);

    public Music()
    {
        orchestra.addInstrument(violinPrimo);
        orchestra.addInstrument(violinSecundo);
        orchestra.addInstrument(altoViola);
        orchestra.addInstrument(violinCello);
        orchestra.addInstrument(contrabBasso);
        orchestra.addInstrument(cembalo);

        orchestra.loadMusic("vivaldi.chrp");
    }

    public void play()
    {
        orchestra.play();
    }

    public void pause()
    {
        orchestra.pause();
    }
}
