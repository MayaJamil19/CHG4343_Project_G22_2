public class UncontrolledCSTR extends Reactor implements Function
{
    private double[] g_opConditions;
    private double[] g_reactionConditions;

    public UncontrolledCSTR()
    {
        super();
        this.resetGlobalVariables();
    }

    public UncontrolledCSTR(UncontrolledCSTR source)
    {
        if(source.g_opConditions == null)
            this.g_opConditions = null;
        else
            this.g_opConditions = new double[source.g_opConditions.length];

        for(int i=0;i<source.g_opConditions.length;i++)
        {
            this.g_opConditions[i]=source.g_opConditions[i];
        }

        if(source.g_reactionConditions == null)
            this.g_reactionConditions = null;
        else
            this.g_reactionConditions = new double[source.g_reactionConditions.length];

        for(int i=0;i<source.g_reactionConditions.length;i++)
        {
            this.g_reactionConditions[i]=source.g_reactionConditions[i];
        }
    }

    public UncontrolledCSTR clone()
    {
        return new UncontrolledCSTR(this);
    }

    protected void resetGlobalVariables()
    {
        this.g_opConditions = null;
        this.g_reactionConditions = null;
    }

    protected void setGlobalVariables(double[] opConditions, double[] reactionConditions)
    {
        this.g_opConditions = new double[opConditions.length];

        for (int i=0;i<opConditions.length;i++)
        {
            this.g_opConditions[i]=opConditions[i];
        }

        this.g_reactionConditions = new double[reactionConditions.length];

        for (int i=0;i<reactionConditions.length;i++)
        {
            this.g_reactionConditions[i]=reactionConditions[i];
        }
    }

    public boolean equals(Object comparator)
    {
        if(!super.equals(comparator))
            return false;

        UncontrolledCSTR cast = ((UncontrolledCSTR) comparator);

        //Null check for g_opConditions
        if((this.g_opConditions==null && cast.g_opConditions!=null) || (this.g_opConditions!=null && cast.g_opConditions==null))
            return false;

        //Null check for g_reactionConditions
        if((this.g_reactionConditions==null && cast.g_reactionConditions!=null) || (this.g_reactionConditions!=null && cast.g_reactionConditions==null))
            return false;

        //Check that array lengths are equal
        if ((this.g_opConditions.length!=cast.g_opConditions.length) || (this.g_reactionConditions.length!=cast.g_reactionConditions.length))
            return false;

        for(int i=0;i<this.g_opConditions.length;i++)
        {
            if(this.g_opConditions[i]!=cast.g_opConditions[i])
                return false;
        }

        for(int i=0;i<this.g_reactionConditions.length;i++)
        {
            if(this.g_reactionConditions[i]!=cast.g_reactionConditions[i])
                return false;
        }

        return true;
    }

    public double calculateExitConcentration(double x, double y, double h, double[] opConditions, double[] reactionConditions, int n)
    {
        this.setGlobalVariables(opConditions,reactionConditions);

        double C = RK45.performStep(x,y,h,this,n);

        this.resetGlobalVariables();

        return C;
    }

    public double calculateValue(double C, double t, int n)
    {
        if(n == 1)
        {
            // dC_A/dt = v_0*(C_A0 - C_A) - k_A*C_A
            return this.g_opConditions[0]*(this.g_reactionConditions[1]-C)-this.g_reactionConditions[0]*C;
        }

        if(n==2)
        {
            // dC_B/dt = -v_0*C_B + k_A*C_A
            return -this.g_opConditions[0]*C+this.g_reactionConditions[0]*C;
        }

        else
            return 0;
    }
}
