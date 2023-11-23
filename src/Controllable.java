public interface Controllable
{
    double setManipulatedVariable(double manipulatedVariable); //for changing flowrate
    double readControlledVariable(double controlledVariable); //for reading concentration
    //double getProcessVariable();
    //void calculateControl();
}

