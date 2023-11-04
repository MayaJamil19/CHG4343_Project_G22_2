public class UncontrolledCSTR extends Reactor implements Function
{
    private double[] g_opConditions;
    private double[] g_reactionConditions;
    private double g_V; //Volume

    public UncontrolledCSTR()
    {
        super();
        this.resetGlobalVariables();
    }

    public UncontrolledCSTR(UncontrolledCSTR source)
    {
        super(source);
        this.g_V = source.g_V;

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
    }

    protected void setGlobalVariables(double v, double[] opConditions, double[] reactionConditions, Reaction r)
    {
        super.setGlobalVariables(r);
        this.g_V = g_V;

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

    public double calculateExitConcentration(double[] opConditions, double[] reactionConditions, double t)
    {

    }
}
