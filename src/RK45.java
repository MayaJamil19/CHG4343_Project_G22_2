public class RK45 {
    private double[] g_k;

    public RK45()
    {
        super();
        this.resetGlobalVariables();
    }

    public RK45(RK45 source)
    {
        if(source.g_k == null)
            this.g_k = null;
        else
            this.g_k = new double[source.g_k.length];

        for(int i=0;i<source.g_k.length;i++)
        {
            this.g_k[i]=source.g_k[i];
        }
    }

    public RK45 clone()
    {
        return new RK45(this);
    }

    protected void resetGlobalVariables()
    {
        this.g_k = null;
    }

    protected void setGlobalVariables(double[] k)
    {
        this.g_k = new double[k.length];

        for(int i=0;i<k.length;i++)
        {
            this.g_k[i] = k[i];
        }
    }

    public double performStep(double x, double y, double h, Function f)
    {
        this.g_k[0] = h*f.calculateValue(x,y);
        this.g_k[1] = h*f.calculateValue(x+0.25*h,y+0.25*this.g_k[0]);
        this.g_k[2] = h*f.calculateValue(x+(3./8.)*h,y+(3./32.)*this.g_k[0]+(9./32.)*this.g_k[1]);
        this.g_k[3] = h*f.calculateValue(x+(12./13.)*h,y+(1932./2197.)*this.g_k[0]-(7200./2197.)*this.g_k[1]+(7296./2197.)*this.g_k[3]);
        this.g_k[4] = h*f.calculateValue(x+h,y+(439./216.)*this.g_k[0]-8*this.g_k[1]+(3680./513.)*this.g_k[2]-(845./4104.)*this.g_k[3]);
        this.g_k[5] = h*f.calculateValue(x+0.5*h,y-(8./27.)*this.g_k[0]+2*this.g_k[1]-(3544./2565.)*this.g_k[2]+(1859./4104.)*this.g_k[3]-(11./40.)*this.g_k[4]);

        return y+(25./216.)*this.g_k[0]+(6656./12825.)*this.g_k[2]+(28561./56430.)*this.g_k[3]-(9./50.)*this.g_k[4]+(2./55.)*this.g_k[5];
    }
}
