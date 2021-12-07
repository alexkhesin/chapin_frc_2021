package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int FRONT_FLAP_PWM_PORT = 1;
  public static final int REAR_FLAP_PWM_PORT = 2;
  public static final int LIFT_PWM_PORT = 3;
  public static final int ROTOR_PWM_PORT = 4;

  public static final int FRONT_LEFT_TALON_ID = 1;
  public static final int REAR_LEFT_TALON_ID = 2;
  public static final int FRONT_RIGHT_TALON_ID = 3;
  public static final int REAR_RIGHT_TALON_ID = 4;
}
