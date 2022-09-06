// Generated from C.g4 by ANTLR 4.8
package bit.minisys.minicc.icgen.internal.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNC}
	 * labeled alternative in {@link CParser#eprog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNC(CParser.FUNCContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GDECL}
	 * labeled alternative in {@link CParser#eprog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGDECL(CParser.GDECLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNCD}
	 * labeled alternative in {@link CParser#eprog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNCD(CParser.FUNCDContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#funcdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdecl(CParser.FuncdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#cstat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCstat(CParser.CstatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpec(CParser.SpecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ADECL}
	 * labeled alternative in {@link CParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitADECL(CParser.ADECLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SDECL}
	 * labeled alternative in {@link CParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSDECL(CParser.SDECLContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#iden}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIden(CParser.IdenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#parmdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParmdecl(CParser.ParmdeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FEXPR}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFEXPR(CParser.FEXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BCALL}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBCALL(CParser.BCALLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CALL}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCALL(CParser.CALLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SSC}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSSC(CParser.SSCContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SIDEN}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSIDEN(CParser.SIDENContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BUCKET}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBUCKET(CParser.BUCKETContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SIC}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSIC(CParser.SICContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PEXPR}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPEXPR(CParser.PEXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SFC}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSFC(CParser.SFCContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IASSIGN}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIASSIGN(CParser.IASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BEXPR}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBEXPR(CParser.BEXPRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DASSIGN}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDASSIGN(CParser.DASSIGNContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ARRE}
	 * labeled alternative in {@link CParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitARRE(CParser.ARREContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#ic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIc(CParser.IcContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#sc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSc(CParser.ScContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#fc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFc(CParser.FcContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BREAK}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBREAK(CParser.BREAKContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CSTATE}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCSTATE(CParser.CSTATEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RT}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRT(CParser.RTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BRT}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBRT(CParser.BRTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BSTAT}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBSTAT(CParser.BSTATContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ESTAT}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitESTAT(CParser.ESTATContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DSTAT}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDSTAT(CParser.DSTATContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FOR}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFOR(CParser.FORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FORD}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFORD(CParser.FORDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SIF}
	 * labeled alternative in {@link CParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSIF(CParser.SIFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INIT}
	 * labeled alternative in {@link CParser#forinit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINIT(CParser.INITContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BINIT}
	 * labeled alternative in {@link CParser#forinit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBINIT(CParser.BINITContext ctx);
	/**
	 * Visit a parse tree produced by the {@code COND}
	 * labeled alternative in {@link CParser#forcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCOND(CParser.CONDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BCOND}
	 * labeled alternative in {@link CParser#forcond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBCOND(CParser.BCONDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STEP}
	 * labeled alternative in {@link CParser#forstep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTEP(CParser.STEPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BSTEP}
	 * labeled alternative in {@link CParser#forstep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBSTEP(CParser.BSTEPContext ctx);
	/**
	 * Visit a parse tree produced by {@link CParser#dstat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDstat(CParser.DstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WILD}
	 * labeled alternative in {@link CParser#inli}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWILD(CParser.WILDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MILD}
	 * labeled alternative in {@link CParser#inli}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMILD(CParser.MILDContext ctx);
}