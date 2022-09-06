lexer grammar CLexerRules;
INTYPE	:	'char'|'double'|'float'|'int'|'long'|'restrict'|'short'|'signed'|'unsigned'|'void';
ID      :   ALP_ ALPDIG_*;
INT	    :	SIGN? NUM SUF?;
FLO	    :	DFLO|XFLO;
CHRC	:	[LuU]?'\''CHR'\'';
STRL	:	PRE?'"'STR'"';
SOP	:	[[\](){}.&*+\-~!/%<>^|?:;=,#];
LOP	:	'->'|'++'|'--'|'<<'|'>>'|'<='|'>='|'=='|'!='|'&&'|'||'|'*='|'/='|'%='|'+='|'-='|'&='|'^='|' c|='|'##'|'<:'|':>'|'<%'|'%>'|'%:';
LLOP	:	'...'|'<<='|'>>=';
LLLOP	:	'%:%:';


WS	:	[ \t\r\n] -> skip;

fragment CONTYPE	:	'enum'|'struct'|'union'|'typedef';
fragment PRETYPE	:	'auto'|'const'|'extern'|'inline'|'register'|'static'|'volatile';

fragment FLOW	:	'break'|'case'|'continue'|'default'|'do'|'else'|'for'|'goto'|'if'|'return'|'switch'|'while';
fragment KEYWD	:	CONTYPE|PRETYPE|INTYPE|FLOW|'sizeof';

fragment ALP_	:	[a-zA-Z_];
fragment DIG	    :	[0-9];
fragment DIG1	:	[1-9];
fragment ALPDIG_ :   ALP_|DIG;


fragment ODIG	:	[0-7];
fragment XDIG	:	DIG|[a-fA-F];
fragment DNUM	:	DIG1 DIG*;
fragment ONUM	:	'0' ODIG*;
fragment XNUM	:	'0'[xX] XDIG*;
fragment NUM	:	DNUM|ONUM|XNUM;
fragment USUF	:	[u|U];
fragment LSUF	:	'l'|'L'|'ll'|'LL';
fragment SUF	:	(USUF LSUF?)|(LSUF USUF?);


fragment FSUF	:	[flFL];
fragment SIGN	:	[+-];
fragment DFRAC	:	DIG+'.'DIG*;
fragment DEXP	:	[eE]SIGN?DIG+;
fragment DFLO	:	((DFRAC DEXP?)|(DIG+DEXP))FSUF?;
fragment XFRAC	:	XNUM'.'XDIG*;
fragment BEXP	:	[pP]SIGN?DIG+;
fragment XFLO	:	((XFRAC BEXP?)|(XNUM BEXP))FSUF?;


fragment SESC	:	'\\\''|'\\?'|'\\\\'|'\\a'|'\\b'|'\\f'|'\\n'|'\\r'|'\\t'|'\\v';
fragment ESC	:	SESC|('\\'NUM);
fragment CHR	:	(ESC|.);


fragment PRE	:	'u8'|'u'|'U'|'L';
fragment STR	:	CHR*;


