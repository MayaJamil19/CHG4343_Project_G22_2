public class UncontrolledCSTR_Driver
{
    public static void main(String[] args)
    {
        UncontrolledCSTR cstr = new UncontrolledCSTR();

        double C_A0 = 0.2; //mol/m^3
        double k_A = 0.2; //min^-1
        double V = 1.0; //m^3
        double v_0 = 0.05; //m^3/min

        double[] reactionConditions = {k_A,C_A0};
        double[] opConditions = {v_0};

        double[] C_A;
    }
}
