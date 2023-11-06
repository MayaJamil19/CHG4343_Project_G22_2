public class UncontrolledCSTR extends Reactor implements Function
{
    private double[] g_opConditions;
    private double[] g_reactionConditions;
    private double g_V; //Volume
    private double g_C_A;
    private double g_C_B;

    public UncontrolledCSTR()
    {
        super();
        this.resetGlobalVariables();
    }

    public UncontrolledCSTR(UncontrolledCSTR source)
    {
        super(source);
        this.g_V = source.g_V;
        this.g_C_A = source.g_C_A;
        this.g_C_B = source.g_C_B;

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
        super.resetGlobalVariables();
        this.g_opConditions = null;
        this.g_reactionConditions = null;
        this.g_V = 0.0;
        this.g_C_A = 0.0;
        this.g_C_B = 0.0;
    }

    protected void setGlobalVariables(double[] opConditions, double[] reactionConditions, Reaction r)
    {
        super.setGlobalVariables(r);

        this.g_V = g_V;
        this.g_C_A = g_C_A;
        this.g_C_B = g_C_B;

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

    public double calculateExitConcentration(double[] opConditions, double[] reactionConditions, Reaction r)
    {
        this.setGlobalVariables(opConditions,reactionConditions,r);

        double h = 0.01; //step size in minutes

        double C = RK4.integrate(0.0,0.0,h,this);

        this.resetGlobalVariables();

        return C;
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

        if(this.g_V != cast.g_V || this.g_C_A != cast.g_C_A || this.g_C_B != cast.g_C_B)
            return false;

        return true;
    }

    public double calculateValue(double C, double t)
    {
        // X = (k_A*τ)/(1 + k_A*τ)
        // τ = V/v_0
        double X = (this.g_reactionConditions[0]*this.g_V/this.g_opConditions[0])/(1+this.g_reactionConditions[0]*this.g_V/this.g_opConditions[0]);

        // dC_B/dt = -v_0*C_B + k_A*C_A
        // C_B = C_A0*X
        this.g_C_B = -this.g_opConditions[0]*this.g_reactionConditions[1]*X+this.g_reactionConditions[0]*C;

        // dC_A/dt = v_0*(C_A - C_A0) - k_A*C_A
        return this.g_opConditions[0]*(this.g_reactionConditions[1]-C)-this.g_reactionConditions[0]*C;
    }
}
