public interface Controllable
{
    double setManipulatedVariable(double manipulatedVariable);
    double getProcessVariable();
    void calculateControl();
}

