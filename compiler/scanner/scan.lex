%{
#include <iostream>
using namespace std;
int i = 1;//no. of line
int j = 0;//no. of column
int k = 0;//no. of character
int n = 0;//no. of token
}%

%%
auto	{ cout << "[@" << n << k << ":" << k + 4 - 1 << "='auto',<'auto'>," << i << ":" << j << "]\n"; j += 4, k += 4; n++ }
"+"	{ printf("[@%d,%d:%d='+',<'+'>,%d:%d]\n", n, j, j, i, j); j++; n++; }
"-"	{ printf("[@%d,%d:%d='-',<'-'>,%d:%d]\n", n, j, j, i, j); j++; n++; }
"*"	{ printf("[@%d,%d:%d='*',<'*'>,%d:%d]\n", n, j, j, i, j); j++; n++; }
"/"	{ printf("[@%d,%d:%d='/',<'/'>,%d:%d]\n", n, j, j, i, j); j++; n++; }
"|"	{ printf("[@%d,%d:%d='|',<'|'>,%d:%d]\n", n, j, j, i, j); j++; n++; }
[0-9]+	{ printf("[@%d,%d:%d='%s',<Constant>,%d:%d]\n", n, j, j + strlen(yytext) - 1, yytext, i, j); j+=strlen(yytext); n++; }
\n	{ i += 3; j = 0; }
[ \t]	{ j += 1; }
.	{ printf("Mystery character %s\n", yytext); }
%%

int yywrap ()
{
    return -1;
}
int main ()
{
    yylex();
    return 0;
}