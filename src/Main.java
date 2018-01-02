import Jama.Matrix;

/**
 * Created by Michał on 2017-12-13.
 */
public class Main {

    public static void main(String[] args) {
        Solve sa = new Solve(args);
        sa.makeOneCriterion();
        sa.solve();

    }
}
