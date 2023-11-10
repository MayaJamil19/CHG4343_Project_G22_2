public class RK45
{
    public static double step(double x, double y, double h, Function f, int n)
    {
        double[] k = {0.,0.,0.,0.,0.,0.};

        for(int i=1;i<=n;i++)
        {
            k[0] = h*f.calculateValue(x,y,i);
            k[1] = h*f.calculateValue(x+0.25*h,y+0.25*k[0],i);
            k[2] = h*f.calculateValue(x+(3./8.)*h,y+(3./32.)*k[0]+(9./32.)*k[1],i);
            k[3] = h*f.calculateValue(x+(12./13.)*h,y+(1932./2197.)*k[0]-(7200./2197.)*k[1]+(7296./2197.)*k[2],i);
            k[4] = h*f.calculateValue(x+h,y+(439./216.)*k[0]-8*k[1]+(3680./513.)*k[2]-(845./4104.)*k[3],i);
            k[5] = h*f.calculateValue(x+0.5*h,y-(8./27.)*k[0]+2*k[1]-(3544./2565.)*k[2]+(1859./4104.)*k[3]-(11./40.)*k[4],i);
        }

        return y+(16./135.)*k[0]+(6656./12825.)*k[2]+(28561./56430.)*k[3]-(9./50.)*k[4]+(2./55.)*k[5];
    }
}