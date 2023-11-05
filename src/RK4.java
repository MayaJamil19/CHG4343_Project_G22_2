public class RK4
{
    public static double integrate(double x_0, double y_0, double h, Function f)
    {
        double k1, k2, k3, k4;

        double y=y_0;
        double x = x_0;

        int n = (int)((x-x_0)/h);

        for(int i=1;i<=n;i++)
        {
            k1 = h*f.calculateValue(x,y);
            k2 = h*f.calculateValue(x_0+0.5*h,y+0.5*k1);
            k3 = h*f.calculateValue(x_0+0.5*h,y+0.5*k2);
            k4 = h*f.calculateValue(x_0+h,y+k3);

            y = y+(k1/6)+(k2/3)+(k3/3)+(k4/6);
            x_0 = x_0 + h;
        }

        return y;
    }
}
