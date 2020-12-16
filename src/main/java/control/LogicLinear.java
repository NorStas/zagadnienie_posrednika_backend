package control;

import entities.JsonEntityLinear;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optimization.linear.Relationship;
import org.apache.commons.math3.optimization.linear.SimplexSolver;

import java.util.ArrayList;
import java.util.Collection;
public class LogicLinear {
    private InputModel inputModel;
    private JsonEntityLinear json;

    public LogicLinear(JsonEntityLinear json) {
        this.json = json;
    }

    public PointValuePair calculateLinear(){
        inputModel = new InputModel(json);

        LinearObjectiveFunction f2 = new LinearObjectiveFunction(inputModel.getProfitArr(), 0);
        Collection<LinearConstraint> constraints = new ArrayList<>();

        constraints.add( new LinearConstraint( new double[]{1, 1, 0, 0, 0, 0, 0, 0}, Relationship.EQ, inputModel.getSupply()[0] ) );
        constraints.add( new LinearConstraint( new double[]{0, 0, 1, 1, 0, 0, 0, 0}, Relationship.EQ, inputModel.getSupply()[1] ) );
        constraints.add( new LinearConstraint( new double[]{0, 0, 0, 0, 1, 1, 0, 0}, Relationship.LEQ, json.getMax()));
        constraints.add( new LinearConstraint( new double[]{0, 0, 0, 0, 0, 0, 1, 1}, Relationship.EQ, inputModel.getSupply()[3] ) );

        constraints.add( new LinearConstraint( new double[]{1, 0, 1, 0, 1, 0, 1, 0}, Relationship.GEQ, json.getMin()));
        constraints.add( new LinearConstraint( new double[]{0, 1, 0, 1, 0, 1, 0, 1}, Relationship.EQ, inputModel.getDemand()[1] ) );


        PointValuePair solution = new SimplexSolver().optimize(f2, constraints, GoalType.MINIMIZE, true);
        return solution;
    }
}
