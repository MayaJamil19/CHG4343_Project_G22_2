public abstract class Reaction
{

    public abstract Reaction clone();

    public boolean equals(Object comparator)
    {
        if(comparator == null)
            return false;
        else return (this.getClass() == comparator.getClass());
    }
}
