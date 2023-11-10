public abstract class Reactor
{
    public abstract Reactor clone();

    public abstract double calculateExitConcentration(double x, double y, double h, int n);

    public boolean equals(Object comparator)
    {
        if(comparator==null)
        {
            System.out.println("Object is null");
            return false;
        }

        if(this.getClass()!=comparator.getClass())
            return false;

        return true;
    }
}
