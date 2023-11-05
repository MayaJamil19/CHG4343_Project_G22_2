//WORK IN PROGRESS

public class TestCase1 extends Reaction
{
    private double k_A, C_A0, v_0;

    public TestCase1(double k_A, double C_A0, double v_0)
    {
        if(k_A<0. || C_A0<0. || v_0<0.)
        {
            System.out.println("Values must be greater than 0");
            System.exit(0);
        }

        this.k_A=k_A;
        this.C_A0=C_A0;
        this.v_0=v_0;
    }

    public TestCase1(TestCase1 source)
    {
        if(source == null)
        {
            System.out.println("Object is null");
            System.exit(0);
        }

        this.k_A = source.k_A;
        this.C_A0 = source.C_A0;
        this.v_0 = source.v_0;
    }

    public TestCase1 clone()
    {
        return new TestCase1(this);
    }

    public double get_K_A()
    {
        return this.k_A;
    }

    public double getC_A0()
    {
        return this.C_A0;
    }

    public double getV_0()
    {
        return this.v_0;
    }

    public boolean setK_A()
}
