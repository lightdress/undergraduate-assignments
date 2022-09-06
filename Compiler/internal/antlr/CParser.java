// Generated from C.g4 by ANTLR 4.8
package bit.minisys.minicc.icgen.internal.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, INTYPE=26, ID=27, INT=28, FLO=29, CHRC=30, STRL=31, SOP=32, 
		LOP=33, LLOP=34, LLLOP=35, WS=36;
	public static final int
		RULE_prog = 0, RULE_eprog = 1, RULE_funcdecl = 2, RULE_cstat = 3, RULE_spec = 4, 
		RULE_decl = 5, RULE_iden = 6, RULE_parmdecl = 7, RULE_expr = 8, RULE_ic = 9, 
		RULE_sc = 10, RULE_fc = 11, RULE_stat = 12, RULE_forinit = 13, RULE_forcond = 14, 
		RULE_forstep = 15, RULE_dstat = 16, RULE_inli = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "eprog", "funcdecl", "cstat", "spec", "decl", "iden", "parmdecl", 
			"expr", "ic", "sc", "fc", "stat", "forinit", "forcond", "forstep", "dstat", 
			"inli"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "')'", "','", "'{'", "'}'", "'['", "']'", "'++'", 
			"'--'", "'*'", "'/'", "'%'", "'+'", "'-'", "'<'", "'>'", "'<='", "'>='", 
			"'='", "'*='", "'break'", "'return'", "'for'", "'if'", null, null, null, 
			null, null, null, null, null, null, "'%:%:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "INTYPE", "ID", "INT", "FLO", "CHRC", "STRL", "SOP", "LOP", 
			"LLOP", "LLLOP", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "C.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<EprogContext> eprog() {
			return getRuleContexts(EprogContext.class);
		}
		public EprogContext eprog(int i) {
			return getRuleContext(EprogContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(36);
				eprog();
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTYPE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EprogContext extends ParserRuleContext {
		public EprogContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eprog; }
	 
		public EprogContext() { }
		public void copyFrom(EprogContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FUNCDContext extends EprogContext {
		public FuncdeclContext funcdecl() {
			return getRuleContext(FuncdeclContext.class,0);
		}
		public List<SpecContext> spec() {
			return getRuleContexts(SpecContext.class);
		}
		public SpecContext spec(int i) {
			return getRuleContext(SpecContext.class,i);
		}
		public FUNCDContext(EprogContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFUNCD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FUNCContext extends EprogContext {
		public FuncdeclContext funcdecl() {
			return getRuleContext(FuncdeclContext.class,0);
		}
		public CstatContext cstat() {
			return getRuleContext(CstatContext.class,0);
		}
		public List<SpecContext> spec() {
			return getRuleContexts(SpecContext.class);
		}
		public SpecContext spec(int i) {
			return getRuleContext(SpecContext.class,i);
		}
		public FUNCContext(EprogContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFUNC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GDECLContext extends EprogContext {
		public DstatContext dstat() {
			return getRuleContext(DstatContext.class,0);
		}
		public GDECLContext(EprogContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitGDECL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EprogContext eprog() throws RecognitionException {
		EprogContext _localctx = new EprogContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_eprog);
		int _la;
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new FUNCContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(41);
					spec();
					}
					}
					setState(44); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==INTYPE );
				setState(46);
				funcdecl();
				setState(47);
				cstat();
				}
				break;
			case 2:
				_localctx = new GDECLContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				dstat();
				setState(50);
				match(T__0);
				}
				break;
			case 3:
				_localctx = new FUNCDContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(52);
					spec();
					}
					}
					setState(55); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==INTYPE );
				setState(57);
				funcdecl();
				setState(58);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdeclContext extends ParserRuleContext {
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public List<ParmdeclContext> parmdecl() {
			return getRuleContexts(ParmdeclContext.class);
		}
		public ParmdeclContext parmdecl(int i) {
			return getRuleContext(ParmdeclContext.class,i);
		}
		public FuncdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFuncdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdeclContext funcdecl() throws RecognitionException {
		FuncdeclContext _localctx = new FuncdeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcdecl);
		int _la;
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				iden();
				setState(63);
				match(T__1);
				setState(64);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				iden();
				setState(67);
				match(T__1);
				setState(68);
				parmdecl();
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(69);
					match(T__3);
					setState(70);
					parmdecl();
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(76);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CstatContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public CstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cstat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitCstat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CstatContext cstat() throws RecognitionException {
		CstatContext _localctx = new CstatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__4);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__4) | (1L << T__8) | (1L << T__9) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << INTYPE) | (1L << ID) | (1L << INT) | (1L << FLO) | (1L << STRL))) != 0)) {
				{
				{
				setState(81);
				stat();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpecContext extends ParserRuleContext {
		public TerminalNode INTYPE() { return getToken(CParser.INTYPE, 0); }
		public SpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecContext spec() throws RecognitionException {
		SpecContext _localctx = new SpecContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(INTYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	 
		public DeclContext() { }
		public void copyFrom(DeclContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ADECLContext extends DeclContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ADECLContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitADECL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SDECLContext extends DeclContext {
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public SDECLContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSDECL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		return decl(0);
	}

	private DeclContext decl(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DeclContext _localctx = new DeclContext(_ctx, _parentState);
		DeclContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_decl, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new SDECLContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(92);
			iden();
			}
			_ctx.stop = _input.LT(-1);
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ADECLContext(new DeclContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_decl);
					setState(94);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(95);
					match(T__6);
					setState(96);
					expr(0);
					setState(97);
					match(T__7);
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IdenContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CParser.ID, 0); }
		public IdenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iden; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitIden(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdenContext iden() throws RecognitionException {
		IdenContext _localctx = new IdenContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_iden);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParmdeclContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public List<SpecContext> spec() {
			return getRuleContexts(SpecContext.class);
		}
		public SpecContext spec(int i) {
			return getRuleContext(SpecContext.class,i);
		}
		public ParmdeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parmdecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitParmdecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParmdeclContext parmdecl() throws RecognitionException {
		ParmdeclContext _localctx = new ParmdeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parmdecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				spec();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTYPE );
			setState(111);
			decl(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FEXPRContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FEXPRContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFEXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BCALLContext extends ExprContext {
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public BCALLContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBCALL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CALLContext extends ExprContext {
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CALLContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitCALL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SSCContext extends ExprContext {
		public ScContext sc() {
			return getRuleContext(ScContext.class,0);
		}
		public SSCContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSSC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SIDENContext extends ExprContext {
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public SIDENContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSIDEN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BUCKETContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BUCKETContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBUCKET(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SICContext extends ExprContext {
		public IcContext ic() {
			return getRuleContext(IcContext.class,0);
		}
		public SICContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSIC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PEXPRContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PEXPRContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitPEXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SFCContext extends ExprContext {
		public FcContext fc() {
			return getRuleContext(FcContext.class,0);
		}
		public SFCContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSFC(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IASSIGNContext extends ExprContext {
		public Token op;
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IASSIGNContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitIASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BEXPRContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BEXPRContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBEXPR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DASSIGNContext extends ExprContext {
		public Token op;
		public IdenContext iden() {
			return getRuleContext(IdenContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DASSIGNContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitDASSIGN(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ARREContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ARREContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitARRE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new BUCKETContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(114);
				match(T__1);
				setState(115);
				expr(0);
				setState(116);
				match(T__2);
				}
				break;
			case 2:
				{
				_localctx = new BCALLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				iden();
				setState(119);
				match(T__1);
				setState(120);
				match(T__2);
				}
				break;
			case 3:
				{
				_localctx = new CALLContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				iden();
				setState(123);
				match(T__1);
				setState(124);
				expr(0);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(125);
					match(T__3);
					setState(126);
					expr(0);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				match(T__2);
				}
				break;
			case 4:
				{
				_localctx = new FEXPRContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				((FEXPRContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__9) ) {
					((FEXPRContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(135);
				expr(10);
				}
				break;
			case 5:
				{
				_localctx = new DASSIGNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				iden();
				setState(137);
				((DASSIGNContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
					((DASSIGNContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(138);
				expr(6);
				}
				break;
			case 6:
				{
				_localctx = new IASSIGNContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(140);
				iden();
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(141);
					match(T__6);
					setState(142);
					expr(0);
					setState(143);
					match(T__7);
					}
					}
					setState(147); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__6 );
				setState(149);
				((IASSIGNContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__19 || _la==T__20) ) {
					((IASSIGNContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(150);
				expr(5);
				}
				break;
			case 7:
				{
				_localctx = new SIDENContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				iden();
				}
				break;
			case 8:
				{
				_localctx = new SICContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				ic();
				}
				break;
			case 9:
				{
				_localctx = new SSCContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				sc();
				}
				break;
			case 10:
				{
				_localctx = new SFCContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(155);
				fc();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(180);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(178);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new BEXPRContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(159);
						((BEXPRContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
							((BEXPRContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(160);
						expr(10);
						}
						break;
					case 2:
						{
						_localctx = new BEXPRContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(161);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(162);
						((BEXPRContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
							((BEXPRContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(163);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new BEXPRContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(165);
						((BEXPRContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((BEXPRContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(166);
						expr(8);
						}
						break;
					case 4:
						{
						_localctx = new ARREContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(172); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(168);
								match(T__6);
								setState(169);
								expr(0);
								setState(170);
								match(T__7);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(174); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 5:
						{
						_localctx = new PEXPRContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(177);
						((PEXPRContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
							((PEXPRContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class IcContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CParser.INT, 0); }
		public IcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ic; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitIc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IcContext ic() throws RecognitionException {
		IcContext _localctx = new IcContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScContext extends ParserRuleContext {
		public TerminalNode STRL() { return getToken(CParser.STRL, 0); }
		public ScContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScContext sc() throws RecognitionException {
		ScContext _localctx = new ScContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(STRL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FcContext extends ParserRuleContext {
		public TerminalNode FLO() { return getToken(CParser.FLO, 0); }
		public FcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FcContext fc() throws RecognitionException {
		FcContext _localctx = new FcContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(FLO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SIFContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public SIFContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSIF(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RTContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitRT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CSTATEContext extends StatContext {
		public CstatContext cstat() {
			return getRuleContext(CstatContext.class,0);
		}
		public CSTATEContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitCSTATE(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BRTContext extends StatContext {
		public BRTContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBRT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BREAKContext extends StatContext {
		public BREAKContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBREAK(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FORContext extends StatContext {
		public ForinitContext forinit() {
			return getRuleContext(ForinitContext.class,0);
		}
		public ForcondContext forcond() {
			return getRuleContext(ForcondContext.class,0);
		}
		public ForstepContext forstep() {
			return getRuleContext(ForstepContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public FORContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFOR(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ESTATContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ESTATContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitESTAT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FORDContext extends StatContext {
		public DstatContext dstat() {
			return getRuleContext(DstatContext.class,0);
		}
		public ForcondContext forcond() {
			return getRuleContext(ForcondContext.class,0);
		}
		public ForstepContext forstep() {
			return getRuleContext(ForstepContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public FORDContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitFORD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DSTATContext extends StatContext {
		public DstatContext dstat() {
			return getRuleContext(DstatContext.class,0);
		}
		public DSTATContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitDSTAT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BSTATContext extends StatContext {
		public BSTATContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBSTAT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stat);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new BREAKContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(T__21);
				setState(190);
				match(T__0);
				}
				break;
			case 2:
				_localctx = new CSTATEContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				cstat();
				}
				break;
			case 3:
				_localctx = new RTContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(192);
				match(T__22);
				setState(193);
				expr(0);
				setState(194);
				match(T__0);
				}
				break;
			case 4:
				_localctx = new BRTContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(T__22);
				setState(197);
				match(T__0);
				}
				break;
			case 5:
				_localctx = new BSTATContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(198);
				match(T__0);
				}
				break;
			case 6:
				_localctx = new ESTATContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(199);
				expr(0);
				setState(200);
				match(T__0);
				}
				break;
			case 7:
				_localctx = new DSTATContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(202);
				dstat();
				setState(203);
				match(T__0);
				}
				break;
			case 8:
				_localctx = new FORContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(205);
				match(T__23);
				setState(206);
				match(T__1);
				setState(207);
				forinit();
				setState(208);
				forcond();
				setState(209);
				forstep();
				setState(210);
				stat();
				}
				break;
			case 9:
				_localctx = new FORDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(212);
				match(T__23);
				setState(213);
				match(T__1);
				setState(214);
				dstat();
				setState(215);
				match(T__0);
				setState(216);
				forcond();
				setState(217);
				forstep();
				setState(218);
				stat();
				}
				break;
			case 10:
				_localctx = new SIFContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(220);
				match(T__24);
				setState(221);
				match(T__1);
				setState(222);
				expr(0);
				setState(223);
				match(T__2);
				setState(224);
				stat();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForinitContext extends ParserRuleContext {
		public ForinitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forinit; }
	 
		public ForinitContext() { }
		public void copyFrom(ForinitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class INITContext extends ForinitContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public INITContext(ForinitContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitINIT(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BINITContext extends ForinitContext {
		public BINITContext(ForinitContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBINIT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForinitContext forinit() throws RecognitionException {
		ForinitContext _localctx = new ForinitContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forinit);
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__8:
			case T__9:
			case ID:
			case INT:
			case FLO:
			case STRL:
				_localctx = new INITContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				expr(0);
				setState(229);
				match(T__0);
				}
				break;
			case T__0:
				_localctx = new BINITContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForcondContext extends ParserRuleContext {
		public ForcondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forcond; }
	 
		public ForcondContext() { }
		public void copyFrom(ForcondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BCONDContext extends ForcondContext {
		public BCONDContext(ForcondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBCOND(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CONDContext extends ForcondContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CONDContext(ForcondContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitCOND(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForcondContext forcond() throws RecognitionException {
		ForcondContext _localctx = new ForcondContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forcond);
		try {
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__8:
			case T__9:
			case ID:
			case INT:
			case FLO:
			case STRL:
				_localctx = new CONDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				expr(0);
				setState(235);
				match(T__0);
				}
				break;
			case T__0:
				_localctx = new BCONDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForstepContext extends ParserRuleContext {
		public ForstepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forstep; }
	 
		public ForstepContext() { }
		public void copyFrom(ForstepContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BSTEPContext extends ForstepContext {
		public BSTEPContext(ForstepContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitBSTEP(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class STEPContext extends ForstepContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public STEPContext(ForstepContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitSTEP(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForstepContext forstep() throws RecognitionException {
		ForstepContext _localctx = new ForstepContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_forstep);
		try {
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__8:
			case T__9:
			case ID:
			case INT:
			case FLO:
			case STRL:
				_localctx = new STEPContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				expr(0);
				setState(241);
				match(T__2);
				}
				break;
			case T__2:
				_localctx = new BSTEPContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DstatContext extends ParserRuleContext {
		public List<InliContext> inli() {
			return getRuleContexts(InliContext.class);
		}
		public InliContext inli(int i) {
			return getRuleContext(InliContext.class,i);
		}
		public List<SpecContext> spec() {
			return getRuleContexts(SpecContext.class);
		}
		public SpecContext spec(int i) {
			return getRuleContext(SpecContext.class,i);
		}
		public DstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dstat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitDstat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DstatContext dstat() throws RecognitionException {
		DstatContext _localctx = new DstatContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(246);
				spec();
				}
				}
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INTYPE );
			setState(251);
			inli();
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(252);
				match(T__3);
				setState(253);
				inli();
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InliContext extends ParserRuleContext {
		public InliContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inli; }
	 
		public InliContext() { }
		public void copyFrom(InliContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MILDContext extends InliContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MILDContext(InliContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitMILD(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WILDContext extends InliContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public WILDContext(InliContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CVisitor ) return ((CVisitor<? extends T>)visitor).visitWILD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InliContext inli() throws RecognitionException {
		InliContext _localctx = new InliContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_inli);
		try {
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new WILDContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				decl(0);
				}
				break;
			case 2:
				_localctx = new MILDContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				decl(0);
				setState(261);
				match(T__19);
				setState(262);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return decl_sempred((DeclContext)_localctx, predIndex);
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean decl_sempred(DeclContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 9);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 7);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u010d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\6\2(\n\2\r\2\16\2)\3\3\6\3-\n\3\r\3\16\3.\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\6\38\n\3\r\3\16\39\3\3\3\3\3\3\5\3?\n\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\5\4Q\n\4\3\5\3\5"+
		"\7\5U\n\5\f\5\16\5X\13\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\7\7f\n\7\f\7\16\7i\13\7\3\b\3\b\3\t\6\tn\n\t\r\t\16\to\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0082\n\n\f\n"+
		"\16\n\u0085\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6"+
		"\n\u0094\n\n\r\n\16\n\u0095\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u009f\n\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u00af\n\n"+
		"\r\n\16\n\u00b0\3\n\3\n\7\n\u00b5\n\n\f\n\16\n\u00b8\13\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e5"+
		"\n\16\3\17\3\17\3\17\3\17\5\17\u00eb\n\17\3\20\3\20\3\20\3\20\5\20\u00f1"+
		"\n\20\3\21\3\21\3\21\3\21\5\21\u00f7\n\21\3\22\6\22\u00fa\n\22\r\22\16"+
		"\22\u00fb\3\22\3\22\3\22\7\22\u0101\n\22\f\22\16\22\u0104\13\22\3\23\3"+
		"\23\3\23\3\23\3\23\5\23\u010b\n\23\3\23\2\4\f\22\24\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$\2\7\3\2\13\f\3\2\26\27\3\2\r\17\3\2\20\21\3"+
		"\2\22\25\2\u0124\2\'\3\2\2\2\4>\3\2\2\2\6P\3\2\2\2\bR\3\2\2\2\n[\3\2\2"+
		"\2\f]\3\2\2\2\16j\3\2\2\2\20m\3\2\2\2\22\u009e\3\2\2\2\24\u00b9\3\2\2"+
		"\2\26\u00bb\3\2\2\2\30\u00bd\3\2\2\2\32\u00e4\3\2\2\2\34\u00ea\3\2\2\2"+
		"\36\u00f0\3\2\2\2 \u00f6\3\2\2\2\"\u00f9\3\2\2\2$\u010a\3\2\2\2&(\5\4"+
		"\3\2\'&\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\3\3\2\2\2+-\5\n\6\2,+"+
		"\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\5\6\4\2\61\62"+
		"\5\b\5\2\62?\3\2\2\2\63\64\5\"\22\2\64\65\7\3\2\2\65?\3\2\2\2\668\5\n"+
		"\6\2\67\66\3\2\2\289\3\2\2\29\67\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\5\6\4\2"+
		"<=\7\3\2\2=?\3\2\2\2>,\3\2\2\2>\63\3\2\2\2>\67\3\2\2\2?\5\3\2\2\2@A\5"+
		"\16\b\2AB\7\4\2\2BC\7\5\2\2CQ\3\2\2\2DE\5\16\b\2EF\7\4\2\2FK\5\20\t\2"+
		"GH\7\6\2\2HJ\5\20\t\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2"+
		"\2MK\3\2\2\2NO\7\5\2\2OQ\3\2\2\2P@\3\2\2\2PD\3\2\2\2Q\7\3\2\2\2RV\7\7"+
		"\2\2SU\5\32\16\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3"+
		"\2\2\2YZ\7\b\2\2Z\t\3\2\2\2[\\\7\34\2\2\\\13\3\2\2\2]^\b\7\1\2^_\5\16"+
		"\b\2_g\3\2\2\2`a\f\3\2\2ab\7\t\2\2bc\5\22\n\2cd\7\n\2\2df\3\2\2\2e`\3"+
		"\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\r\3\2\2\2ig\3\2\2\2jk\7\35\2\2k"+
		"\17\3\2\2\2ln\5\n\6\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pq\3\2\2"+
		"\2qr\5\f\7\2r\21\3\2\2\2st\b\n\1\2tu\7\4\2\2uv\5\22\n\2vw\7\5\2\2w\u009f"+
		"\3\2\2\2xy\5\16\b\2yz\7\4\2\2z{\7\5\2\2{\u009f\3\2\2\2|}\5\16\b\2}~\7"+
		"\4\2\2~\u0083\5\22\n\2\177\u0080\7\6\2\2\u0080\u0082\5\22\n\2\u0081\177"+
		"\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\5\2\2\u0087\u009f\3\2"+
		"\2\2\u0088\u0089\t\2\2\2\u0089\u009f\5\22\n\f\u008a\u008b\5\16\b\2\u008b"+
		"\u008c\t\3\2\2\u008c\u008d\5\22\n\b\u008d\u009f\3\2\2\2\u008e\u0093\5"+
		"\16\b\2\u008f\u0090\7\t\2\2\u0090\u0091\5\22\n\2\u0091\u0092\7\n\2\2\u0092"+
		"\u0094\3\2\2\2\u0093\u008f\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\t\3\2\2\u0098"+
		"\u0099\5\22\n\7\u0099\u009f\3\2\2\2\u009a\u009f\5\16\b\2\u009b\u009f\5"+
		"\24\13\2\u009c\u009f\5\26\f\2\u009d\u009f\5\30\r\2\u009es\3\2\2\2\u009e"+
		"x\3\2\2\2\u009e|\3\2\2\2\u009e\u0088\3\2\2\2\u009e\u008a\3\2\2\2\u009e"+
		"\u008e\3\2\2\2\u009e\u009a\3\2\2\2\u009e\u009b\3\2\2\2\u009e\u009c\3\2"+
		"\2\2\u009e\u009d\3\2\2\2\u009f\u00b6\3\2\2\2\u00a0\u00a1\f\13\2\2\u00a1"+
		"\u00a2\t\4\2\2\u00a2\u00b5\5\22\n\f\u00a3\u00a4\f\n\2\2\u00a4\u00a5\t"+
		"\5\2\2\u00a5\u00b5\5\22\n\13\u00a6\u00a7\f\t\2\2\u00a7\u00a8\t\6\2\2\u00a8"+
		"\u00b5\5\22\n\n\u00a9\u00ae\f\20\2\2\u00aa\u00ab\7\t\2\2\u00ab\u00ac\5"+
		"\22\n\2\u00ac\u00ad\7\n\2\2\u00ad\u00af\3\2\2\2\u00ae\u00aa\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b5\3\2"+
		"\2\2\u00b2\u00b3\f\r\2\2\u00b3\u00b5\t\2\2\2\u00b4\u00a0\3\2\2\2\u00b4"+
		"\u00a3\3\2\2\2\u00b4\u00a6\3\2\2\2\u00b4\u00a9\3\2\2\2\u00b4\u00b2\3\2"+
		"\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\23\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7\36\2\2\u00ba\25\3\2\2\2"+
		"\u00bb\u00bc\7!\2\2\u00bc\27\3\2\2\2\u00bd\u00be\7\37\2\2\u00be\31\3\2"+
		"\2\2\u00bf\u00c0\7\30\2\2\u00c0\u00e5\7\3\2\2\u00c1\u00e5\5\b\5\2\u00c2"+
		"\u00c3\7\31\2\2\u00c3\u00c4\5\22\n\2\u00c4\u00c5\7\3\2\2\u00c5\u00e5\3"+
		"\2\2\2\u00c6\u00c7\7\31\2\2\u00c7\u00e5\7\3\2\2\u00c8\u00e5\7\3\2\2\u00c9"+
		"\u00ca\5\22\n\2\u00ca\u00cb\7\3\2\2\u00cb\u00e5\3\2\2\2\u00cc\u00cd\5"+
		"\"\22\2\u00cd\u00ce\7\3\2\2\u00ce\u00e5\3\2\2\2\u00cf\u00d0\7\32\2\2\u00d0"+
		"\u00d1\7\4\2\2\u00d1\u00d2\5\34\17\2\u00d2\u00d3\5\36\20\2\u00d3\u00d4"+
		"\5 \21\2\u00d4\u00d5\5\32\16\2\u00d5\u00e5\3\2\2\2\u00d6\u00d7\7\32\2"+
		"\2\u00d7\u00d8\7\4\2\2\u00d8\u00d9\5\"\22\2\u00d9\u00da\7\3\2\2\u00da"+
		"\u00db\5\36\20\2\u00db\u00dc\5 \21\2\u00dc\u00dd\5\32\16\2\u00dd\u00e5"+
		"\3\2\2\2\u00de\u00df\7\33\2\2\u00df\u00e0\7\4\2\2\u00e0\u00e1\5\22\n\2"+
		"\u00e1\u00e2\7\5\2\2\u00e2\u00e3\5\32\16\2\u00e3\u00e5\3\2\2\2\u00e4\u00bf"+
		"\3\2\2\2\u00e4\u00c1\3\2\2\2\u00e4\u00c2\3\2\2\2\u00e4\u00c6\3\2\2\2\u00e4"+
		"\u00c8\3\2\2\2\u00e4\u00c9\3\2\2\2\u00e4\u00cc\3\2\2\2\u00e4\u00cf\3\2"+
		"\2\2\u00e4\u00d6\3\2\2\2\u00e4\u00de\3\2\2\2\u00e5\33\3\2\2\2\u00e6\u00e7"+
		"\5\22\n\2\u00e7\u00e8\7\3\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00eb\7\3\2\2"+
		"\u00ea\u00e6\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb\35\3\2\2\2\u00ec\u00ed"+
		"\5\22\n\2\u00ed\u00ee\7\3\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00f1\7\3\2\2"+
		"\u00f0\u00ec\3\2\2\2\u00f0\u00ef\3\2\2\2\u00f1\37\3\2\2\2\u00f2\u00f3"+
		"\5\22\n\2\u00f3\u00f4\7\5\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f7\7\5\2\2"+
		"\u00f6\u00f2\3\2\2\2\u00f6\u00f5\3\2\2\2\u00f7!\3\2\2\2\u00f8\u00fa\5"+
		"\n\6\2\u00f9\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0102\5$\23\2\u00fe\u00ff\7\6"+
		"\2\2\u00ff\u0101\5$\23\2\u0100\u00fe\3\2\2\2\u0101\u0104\3\2\2\2\u0102"+
		"\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103#\3\2\2\2\u0104\u0102\3\2\2\2"+
		"\u0105\u010b\5\f\7\2\u0106\u0107\5\f\7\2\u0107\u0108\7\26\2\2\u0108\u0109"+
		"\5\22\n\2\u0109\u010b\3\2\2\2\u010a\u0105\3\2\2\2\u010a\u0106\3\2\2\2"+
		"\u010b%\3\2\2\2\30).9>KPVgo\u0083\u0095\u009e\u00b0\u00b4\u00b6\u00e4"+
		"\u00ea\u00f0\u00f6\u00fb\u0102\u010a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}