public abstract class Reactor
{
    private Reaction g_r;

    public Reactor()
    {
        this.resetGlobalVariables();
    }

    protected void resetGlobalVariables()
    {
        this.g_r=null;
    }

    protected void setGlobalVariables(Reaction r)
    {
        this.g_r=r.clone();
    }

    public Reactor(Reactor source)
    {
        if(source == null) System.exit(-1); //Replace with thrown exception

        if(source.g_r == null) this.g_r = null;
        else this.g_r = g_r.clone();
    }

    public abstract Reactor clone();

    public abstract double calculateExitConcentration(double[] opConditions, double[] reactionConditions, double t);

    public boolean equals(Object comparator)
    {
        if(comparator==null)
        {
            System.out.println("Object is null");
            return false;
        }

        if(this.getClass()!=comparator.getClass())
            return false;

        else return this.g_r.equals(((Reactor)comparator).g_r);
    }
}
