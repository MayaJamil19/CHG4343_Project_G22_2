public abstract class Controller implements Controllable
{
    private Reactor reactor;
    private double setPoint;
    private double[] controllerKs;
    private double integralError;
    private double[] errors= new double[2];
    private double[] time=new double [2];


    //constructor

    public Controller(Reactor reactor, double[] controllerKs)
    {
        //add checks

        this.reactor=reactor.clone();
        this.setPoint=setPoint;
        this.controllerKs=controllerKs.clone();
        this.integralError=0;
        this.errors=errors.clone();
        this.time=time.clone();
    }

    //copy constructor
    public Controller(Controller source)
    {
        if(source==null) System.exit(0);

        this.reactor=source.reactor.clone();
        this.setPoint=source.setPoint;
        this.controllerKs=source.controllerKs.clone();
        this.integralError=source.integralError;
        this.errors=source.errors.clone();
        this.time=source.time.clone();
    }
    //clone method
    public abstract Controller clone();


    //accessor and mutator

    public boolean setSetPoint(double setPoint)
    {
        if (this.setPoint>=0) return false;

        this.setPoint=setPoint;
        return true;
    }

    public double getSetPoint() {
        return this.setPoint;
    }

    public Reactor getReactor() {
        return this.reactor;
    }

    public double[] getControllerKs() {
        return this.controllerKs;
    }

    public double getIntegralError() {
        return this.integralError;
    }


    public abstract void calculateControl();
    //this will be the method that will be
    // different for each controller
    // (where we put the PID stuff in)

    //additional methods

    public double getTimeStep()
    {
        double currentTime=System.currentTimeMillis();
        double timeStep=(currentTime-this.time[0])/1000.0;
        this.time[1]=this.time[0];
        this.time[0]=currentTime;
        return timeStep;
    }

    public abstract double getProcessVariables();
    public abstract double setControlVariables();

  /* public double calculateError (Controllable control)
   {
       double currentValue=control.getCurrentValue();
       double error=setPoint-currentValue;

       double pTerm=controllerKs[0]*error;
       integralError+=error;
       double iTerm=controllerKs[1]*integralError;
       double dTerm=controllerKs[2]*(error-previousError);

       control.calculateOut(double setPoint);
       double calculateOut=pTerm+iTerm+dTerm;

       previousError=error;
   }
   */

    public void updateTerms(double error)
    {
        integralError+=error*(time[0]-time[1])/1000.0;
        errors[1]=errors[0];
        errors[0]=error;
    }

}//end of Controller Parent Class
