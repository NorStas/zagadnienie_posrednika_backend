package control;

import entities.JsonEntity;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.linear.LinearConstraint;
import org.apache.commons.math3.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optimization.linear.Relationship;
import org.apache.commons.math3.optimization.linear.SimplexSolver;

import java.util.ArrayList;
import java.util.Collection;

public class Logic {
    private InputModel inputModel;
    private JsonEntity json;

    public Logic(JsonEntity json) {
        this.json = json;
    }

    public PointValuePair calculateMiddleman() {

        inputModel = new InputModel(json);


        LinearObjectiveFunction f = new LinearObjectiveFunction(inputModel.getProfitArr(), 0);

        Collection<LinearConstraint> constraints = new ArrayList<>();

        constraints.add(new LinearConstraint(new double[]{1, 1, 0, 0, 0, 0, 0, 0}, Relationship.EQ, inputModel.getSupply()[0]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 1, 0, 0, 0, 0}, Relationship.EQ, inputModel.getSupply()[1]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 1, 1, 0, 0}, Relationship.EQ, inputModel.getSupply()[2]));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 0, 0, 1, 1}, Relationship.EQ, inputModel.getSupply()[3]));

        constraints.add(new LinearConstraint(new double[]{1, 0, 1, 0, 1, 0, 1, 0}, Relationship.EQ, inputModel.getDemand()[0]));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 1, 0, 1, 0, 1}, Relationship.EQ, inputModel.getDemand()[1]));


            SimplexSolver solver = new SimplexSolver();
            PointValuePair solution = solver.optimize(f, constraints, GoalType.MAXIMIZE, true);
            return solution;
    }
}
