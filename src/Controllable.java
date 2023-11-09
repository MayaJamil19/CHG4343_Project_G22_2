public interface Controllable
{
    double setControlVariable(double controlVariable);
    double getProcessVariable();
    void calculateControl();
}

