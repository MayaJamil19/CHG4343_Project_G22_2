import java.text.DecimalFormat;

public class TestCase1_Driver
{
    public static void main(String[] args)
    {
        UncontrolledCSTR cstr = new UncontrolledCSTR();

        DecimalFormat df_t = new DecimalFormat("#.##");
        DecimalFormat df_C = new DecimalFormat("0.###E0");

        double C_A0 = 0.2; //mol/m^3
        double k_A = 0.2; //min^-1
        double v_0 = 0.05; //m^3/min

        double[] reactionConditions = {k_A,C_A0};
        double[] opConditions = {v_0};

        double C_Ai = 0.;
        double C_Bi = 0.;
        double t = 0;
        double h = 0.01;
        int i=0;
        double epsilon;

        double[] C_A_Array = new double[i];
        double[] C_B_Array = new double[i];

        System.out.println("x\ty_A\ty_B");

        do
        {
            double[] tmpC_A_Array = new double[i+1];
            double[] tmpC_B_Array = new double[i+1];

            for(int j = 0; j < i; j++)
            {
                tmpC_A_Array[j] = C_A_Array[j];
            }

            tmpC_A_Array[i] = cstr.calculateExitConcentration(t,C_Ai,h,opConditions,reactionConditions,1);
            C_A_Array = tmpC_A_Array;

            for(int j = 0; j < i; j++)
            {
                tmpC_B_Array[j] = C_B_Array[j];
            }

            tmpC_B_Array[i] = cstr.calculateExitConcentration(t,C_Bi,h,opConditions,reactionConditions,2);
            C_B_Array = tmpC_B_Array;

            System.out.println(df_t.format(t)+"\t"+df_C.format(C_A_Array[i])+"\t"+df_C.format(C_B_Array[i]));

            if(i<3)
            {
                epsilon = 1;
            }
            else
            {
                epsilon = 100*Math.abs(C_B_Array[i]-C_B_Array[i-1])/Math.abs(C_B_Array[i-1]);
            }

            C_Ai = C_A_Array[i];
            C_Bi = C_B_Array[i];
            t=t+h;
            i++;
        }
        while(epsilon>0.01);

    }
}
