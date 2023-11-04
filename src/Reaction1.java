//Not sure if this class is good, I shouldn't put k_A, v_A and C_AO into an array maybe?

public class Reaction1 extends Reaction
{
    private double[] reactionConditions;

    //reactionConditions[0] = k_A
    //reactionConditions[1] = v_A
    //reactionConditions[2] = C_A0

    public Reaction1(double[] reactionConditions)
    {
        if(reactionConditions == null)
        {
            System.out.println("Array of reaction conditions is null");
            System.exit(0);
        }

        //put if loop nested in for loop to check that values > 0

        this.reactionConditions = new double[reactionConditions.length];

        for(int i=0;i<reactionConditions.length;i++)
        {
            this.reactionConditions[i]=reactionConditions[i];
        }
    }

    public Reaction1(Reaction1 source)
    {
        if(source == null)
        {
            System.out.println("Object is null");
            System.exit(0);
        }

        for(int i=0;i<reactionConditions.length;i++)
        {
            this.reactionConditions[i]=source.reactionConditions[i];
        }
    }

    public Reaction1 clone()
    {
        return new Reaction1(this);
    }

    public double[] getReactionConditions()
    {
        double[] rxnCondCopy = new double[this.reactionConditions.length];

        for(int i=0;i<this.reactionConditions.length;i++)
        {
            this.reactionConditions[i]=rxnCondCopy[i];
        }

        return rxnCondCopy;
    }

    public boolean setReactionConditions(double[] reactionConditions)
    {
        if(reactionConditions == null)
        {
            return false;
        }

        this.reactionConditions = new double[reactionConditions.length];

        for(int i=0;i<reactionConditions.length;i++)
        {
            this.reactionConditions[i]=reactionConditions[i];
        }

        return true;
    }

    public boolean equals(Object comparator)
    {
        if(comparator == null)
        {
            System.out.println("Object is null");
            return false;
        }

        if(comparator.getClass()!=this.getClass())
            return false;

        Reaction1 cast = ((Reaction1)comparator);

        if(cast.reactionConditions.length!=this.reactionConditions.length)
            return false;

        for(int i=0;i<reactionConditions.length;i++)
        {
            if(this.reactionConditions[i]!=cast.reactionConditions[i])
                return false;
        }

        return true;
    }
}
