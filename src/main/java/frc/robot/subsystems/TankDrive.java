// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IDs;

import com.ctre.phoenix6.hardware.TalonFX;

public class TankDrive extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
    public final TalonFX frontRight = new TalonFX(IDs.frontRightID);
    public final TalonFX backRight = new TalonFX(IDs.backRightID);
    public final TalonFX frontleft = new TalonFX(IDs.frontLeftID);
    public final TalonFX backLeft = new TalonFX(IDs.backLeftID);
    
  private void leftDrive(double speed){
    frontLeft.setControl(speed);
    backLeft.setControl(speed);
  }

  private void rightDrive(double speed){
    frontRight.setControl(speed);
    backRight.setControl(speed);
  }

  public void drive(double forward, double right){
    double leftSpeed = forward + right;
    double rightSpeed = forward - right;
    leftDrive(leftSpeed);
    rightDrive(rightSpeed);
  }
  
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}