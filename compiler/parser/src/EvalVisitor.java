public class EvalVisitor extends ExprBaseVisitor<Integer> {
    @Override
    public Integer visitAssign(ExprParser.AssignContext ctx) {
        System.out.println(ctx.ID());
        return 0;
    }
}
