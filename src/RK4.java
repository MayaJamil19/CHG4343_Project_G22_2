public class RK4
{
    public static double integrate(double x_0, double y_0, double h, Function f)
    {
        double k1, k2, k3, k4;

        double y = y_0;
        double x = x_0;

        for (int i=1; ;i++)
        {
            k1 = h*f.calculateValue(x,y);
            k2 = h*f.calculateValue(x+0.5*h,y+0.5*k1);
            k3 = h*f.calculateValue(x+0.5*h,y+0.5*k2);
            k4 = h*f.calculateValue(x+h,y+k3);

            double newY = y+(k1/6)+(k2/3)+(k3/3)+(k4/6);

            // Calculate error at each step
            double epsilon = 100*Math.abs(newY-y)/Math.abs(y);

            if (epsilon<0.01)
            {
                System.out.println("Ɛ < 0.01 reached at x = "+x);
                return y;
            }

            y = newY;
            x += h;
        }
    }
}
