public class UncontrolledCSTR extends Reactor implements Function
{
    private double[] g_opConditions;
    private double[] g_reactionConditions;
    private int currentRun;

    private double V;

    private double[][] outputValues;

    public UncontrolledCSTR(double V, double[] g_opConditions, double[] g_reactionConditions)
    {
        super();
        this.g_opConditions = new double[g_opConditions.length];
        this.g_reactionConditions = new double[g_reactionConditions.length];

        for(int i=0;i<g_opConditions.length;i++)
        {
            this.g_opConditions[i] = g_opConditions[i];
        }

        for(int i=0;i<g_reactionConditions.length;i++)
        {
            this.g_reactionConditions[i] = g_reactionConditions[i];
        }

        this.V = V;

        this.outputValues = new double[100][2];
    }

    public UncontrolledCSTR(UncontrolledCSTR source)
    {
        this.V = source.V;

        for(int i=0;i<g_opConditions.length;i++)
        {
            this.g_opConditions[i]=source.g_opConditions[i];
        }

        for(int i=0;i<g_reactionConditions.length;i++)
        {
            this.g_reactionConditions[i]=source.g_reactionConditions[i];
        }

        for(int i=0;i<outputValues.length;i++)
            for(int j=0;j<outputValues[i].length;j++)
                this.outputValues[i][j] = source.outputValues[i][j];
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

    public boolean setOutputValues(double[][] outputValues)
    {
        if(outputValues == null)
            return false;

        this.outputValues = new double[outputValues.length][outputValues.length];

        for(int i=0;i<outputValues.length;i++)
            for(int j=0;j<outputValues[i].length;j++)
                this.outputValues[i][j] = outputValues[i][j];

        return true;
    }

    public void setV(double v)
    {
        this.V = v;
    }

    public double[][] getOutputValues()
    {
        double[][] outputValues = new double[this.outputValues.length][this.outputValues.length];

        for(int i=0;i<outputValues.length;i++)
            for(int j=0;j<outputValues[i].length;j++)
                outputValues[i][j] = this.outputValues[i][j];

        return outputValues;
    }

    public double getV()
    {
        return this.V;
    }

    public double calculateExitConcentration(double x, double y, double h, int n)
    {
        //this.setGlobalVariables(opConditions,reactionConditions);

        double C = RK45.step(x,y,h,this,n);
        //double C = Euler.step(x,y,h,this,n);

        //this.resetGlobalVariables();

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

        if(cast.V != this.V)
            return false;

        if(cast.outputValues.length != this.outputValues.length)
            return false;

        for(int i=0;i<outputValues.length;i++)
            for(int j=0;j<outputValues[i].length;j++)
                if(this.outputValues[i][j] != cast.outputValues[i][j])
                    return false;

        return true;
    }

    public double calculateValue(double t, double C, int n)
    {
        if(n == 1)
        {
            // dC_A/dt = v_0*(C_A0 - C_A) - r_A
            return this.g_opConditions[0]/this.V*(this.g_reactionConditions[1]-C)-this.g_reactionConditions[0]*C;
        }

        if(n == 2)
        {
            // dC_B/dt = -v_0*C_B + k_A*C_A
            return -this.g_opConditions[0]*C;
        }

        else
            return 0;
    }

    public double[][] runCSTR(double runTime, double h) {
        double t = 0;
        double epsilon = 1.0;
        double[][] values = new double[100][3];

        while (t < runTime-1)
        {
            values[this.currentRun][0] = t;
            values[this.currentRun][1] = this.outputValues[this.currentRun][0];
            values[this.currentRun][2] = this.outputValues[this.currentRun][1];

            //System.out.println("C_A0 = " + this.outputValues[this.currentRun][0] + "; C_B0 = " + this.outputValues[this.currentRun][1]);
            this.outputValues[this.currentRun + 1][0] = this.calculateExitConcentration(t, this.outputValues[this.currentRun][0], h, 1);
            this.outputValues[this.currentRun + 1][1] = this.calculateExitConcentration(t, this.outputValues[this.currentRun][1], h, 2) + this.outputValues[this.currentRun][0]*this.g_reactionConditions[0];
            
            t += h;
            this.currentRun++;
        }
        this.currentRun = 0;

        return values;
    }

    public String toString() {
        return "C_A: " + this.outputValues[499][0] + " ; C_B: " + this.outputValues[499][1];
    }
}
