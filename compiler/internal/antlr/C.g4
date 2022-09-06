grammar C;
import CLexerRules;
prog        :   eprog+
            ;
eprog       :   spec+ funcdecl cstat    # FUNC
            |   dstat ';'               # GDECL
            |   spec+ funcdecl ';'      # FUNCD
            ;

funcdecl    :   iden '('  ')'
            |   iden '('parmdecl (',' parmdecl)*')'
            ;
cstat       :   '{' (stat)* '}';
spec        :   INTYPE;
decl        :   iden                # SDECL
            |   decl '[' expr ']'   # ADECL
            ;
iden        :   ID;
parmdecl    :   spec+ decl;

expr        :   '(' expr ')'                        # BUCKET
            |   expr('['expr']')+                   # ARRE
            |   iden '(' ')'                        # BCALL
            |   iden '(' expr (',' expr)* ')'       # CALL
            |   expr op=('++'|'--')                 # PEXPR
            |   op=('++'|'--') expr                 # FEXPR
            |   expr op=('*'|'/'|'%') expr          # BEXPR
            |   expr op=('+'|'-') expr              # BEXPR
            |   expr op=('<'|'>'|'<='|'>=') expr    # BEXPR
            |   iden op=('='|'*=') expr             # DASSIGN
            |   iden('['expr']')+ op=('='|'*=') expr    # IASSIGN
            |   iden                                # SIDEN
            |   ic                                  # SIC
            |   sc                                  # SSC
            |   fc                                  # SFC
            ;
ic          :   INT
//            |   op=('+'|'-') INT
            ;
sc          :   STRL;
fc          :   FLO;
stat        :   'break' ';'                                    # BREAK
            |   cstat                                       # CSTATE
            |   'return' expr ';'                           # RT
            |   'return' ';'                                # BRT
            |   ';'                                         # BSTAT
            |   expr ';'                                    # ESTAT
            |   dstat ';'                                   # DSTAT
            |   'for' '(' forinit forcond forstep stat   # FOR
            |   'for' '(' dstat ';' forcond forstep stat  # FORD
            |   'if' '(' expr ')' stat                      # SIF
            ;
forinit     :   expr ';'    # INIT
            |   ';'         # BINIT
            ;
forcond     :   expr ';'    # COND
            |   ';'         # BCOND
            ;
forstep     :   expr ')'    # STEP
            |   ')'         # BSTEP
                                      ;
dstat       :   spec+ inli (',' inli)*;
inli        :   decl                # WILD
            |   decl '=' expr       # MILD
            ;