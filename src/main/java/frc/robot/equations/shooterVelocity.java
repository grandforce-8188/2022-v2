package frc.robot.equations;

public class shooterVelocity {
    final double fourth = 0.0050,
                 third = 0.0771,
                 second = -0.4111,
                 first = -43.01446,
                 zeroth = 5506.0435;
    public double calculateEquation(double limelightY, double limelightTV){

        if(limelightY >= 7.2){
            return 5000.0;
        }
        //else if (limelightY <= -8.5)
        //{
        //    return 6000.0;
        //}
        else if (limelightTV == 1){
            return ((fourth * Math.pow(limelightY, 4)) - (third * Math.pow(limelightY, 3))
                    - (second * Math.pow(limelightY, 2)) - (first * limelightY) + (zeroth));
        }
        else {
            return 4900;
        }
    }
}
