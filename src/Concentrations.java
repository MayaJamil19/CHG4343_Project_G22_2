public class Concentrations
{
    private double[] C;

    public Concentrations(double[] C)
    {
        if(C == null)
            System.exit(0);

        this.C = new double[C.length];

        for(int i=0;i<C.length;i++)
        {
            this.C[i] = C[i];
        }
    }

    public Concentrations(Concentrations source)
    {
        if(source == null)
            System.exit(0);

        for(int i = 0; i< C.length; i++)
        {
            this.C[i] = source.C[i];
        }
    }

    public double[] getC()
    {
        double[] C_Copy = new double[this.C.length];

        for(int i = 0; i<this.C.length; i++)
        {
            this.C[i] = C_Copy[i];
        }

        return C_Copy;
    }

    public boolean setC(double[] C)
    {
        if(C == null)
            return false;

        this.C = new double[C.length];

        for(int i=0;i<C.length;i++)
        {
            this.C[i] = C[i];
        }

        return true;
    }

    public boolean equals(Object comparator)
    {
        if(comparator == null)
            return false;

        if(comparator.getClass()!=this.getClass())
            return false;

        Concentrations cast = ((Concentrations)comparator);

        if(this.C.length!=cast.C.length)
            return false;

        for(int i=0;i<this.C.length;i++)
        {
            if(this.C[i] != cast.C[i])
                return false;
        }

        return true;
    }
}
