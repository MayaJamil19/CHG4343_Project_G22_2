public abstract class Reactor
{
    public abstract Reactor clone();

    public abstract double calculateExitConcentration(double x, double y, double h, double[] opConditions, double[] reactionConditions, int n);

    public boolean equals(Object comparator)
    {
        if(comparator==null) return false;
        else return (this.getClass()==comparator.getClass());
    }
}
