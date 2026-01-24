// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IDs;
import frc.robot.Constants.intakeConstants;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

public class Intake extends SubsystemBase {
  public final TalonFX intakeMotor = new TalonFX(IDs.intakeMotorID);
  
  

  public Intake() {
    TalonFXConfiguration intakeConfig = new TalonFXConfiguration();

    // PID values
    intakeConfig.Slot0.kP = Constants.PIDConstants.Drive.kDriveP;
    intakeConfig.Slot0.kI = Constants.PIDConstants.Drive.kDriveI;
    intakeConfig.Slot0.kD = Constants.PIDConstants.Drive.kDriveD;
    intakeConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    //aplying the PID
    intakeMotor.getConfigurator().apply(intakeConfig);
  }

  public void intake(){
    intakeMotor.set(intakeConstants.kIntakeSpeed);
    
  }

  public void outtake(){
    intakeMotor.set(intakeConstants.kOuttakeSpeed);
  }

  private void end(){
    intakeMotor.set(0);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
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
