//Child class PIController of the parent Controller
public class PIController extends Controller
{

    public PIController(Reactor reactor, double[] controllerKs)
    {
        super(reactor, controllerKs);
        //add to this
    }
    //copy constructor 
    
    public Controller clone()
    {
        return new PIController(this.getReactor(), this.getControllerKs());
    }


    //this is the method for the controller, it gets the type of control (see end of outline for project
    //updates the flowrate=control varible = manipulated
    public void calculateControl()
    {
        double error=this.getSetPoint()-this.getReactor().getProcessVariables();
        double timeStep=this.getTimeStep();

        this.updateTerms(error);

        double[] controllerKs=this.getControllerKs();
        double proportionalTerm=controllerKs[0]*error;
        double integralTerm=controllerKs[1]*this.getIntegralError();

        double controlVariable=proportionalTerm+integralTerm;

        this.getReactor().setControlVariables(controlVariable);
    }

    //below need to figure out how to implement
    @Override
    public double setControlVariable(double controlVariable)
    {
        this.getReactor().setControlVariable(controlVariable);
        return controlVariable;
    }

    @Override
    public double getProcessVariable()
    {

        return this.getReactor().getProcessVariable();

    }
}//end of PI controller class


