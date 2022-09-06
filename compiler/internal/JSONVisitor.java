package bit.minisys.minicc.icgen.internal;

import bit.minisys.minicc.icgen.internal.antlr.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Sympol {
    String name_;
    int scope_;
    String type_;
    int arrn_;
    List<String> arrsize_;
    boolean isfunc_;
    boolean isdecl_;

    public Sympol(String name, int scope, String type, int arrn, boolean isfunc, boolean isdecl) {
        name_ = name;
        scope_ = scope;
        type_ = type;
        arrn_ = arrn;
        arrsize_ = new ArrayList<String >();
        isfunc_ = isfunc;
        isdecl_ = isdecl;
    }
}

public class JSONVisitor extends CBaseVisitor<Integer> {
    public JSONVisitor() {
        osw = new OutputStreamWriter(System.out);
        oswR = new OutputStreamWriter(System.out);
    }
    public JSONVisitor(OutputStreamWriter outputStreamWriter, OutputStreamWriter outputStreamWriterR) {
        osw = outputStreamWriter;
        oswR = outputStreamWriterR;
    }
    OutputStreamWriter osw = null;
    OutputStreamWriter oswR = null;

    int errn = 0;
    private void printError(String s) {
        errn++;
        if (errn == 1) {
            System.out.println("errors:");
            System.out.println("------------------------------------");
        }
        System.out.println(s);
    }

    List<Sympol> data = new ArrayList<Sympol>();
    List<Sympol> stack = new ArrayList<Sympol>();
    boolean isfunc = false;
    boolean isdecl = false;
    int scope = 0;
    int loop = 0;
    boolean pushSymbol = false;
    int arrn = 0;
    String type = null;
    Integer tokenId = 0;

    Integer tab = 0;
    private void printTab() throws IOException {
        for (Integer i = 0; i < tab; i++) {
            osw.append('\t');
        }
    }
    private void head(String s) throws IOException {
        printTab();
        if (s != null) {
            osw.append("\"" + s + "\"" + ": ");
        }
        osw.append('{');
        osw.append('\n');
        tab++;
    }
    private void tail() throws IOException {

        tab--;
        printTab();
        osw.append('}');
    }
    private void ahead(String s) throws IOException {
        printTab();
        if (s != null) {
            osw.append("\"" + s + "\"" + ": ");
        }
        osw.append('[');
        osw.append('\n');
        tab++;
    }
    private void atail() throws IOException {
        tab--;
        printTab();
        osw.append(']');
    }
    private void end(boolean fin) throws IOException {
        if (fin) {
            osw.append("\n");
        }
        else {
            osw.append(",\n");
        }
    }
    private void printType(String tp) throws IOException {
        printTab();
        osw.append("\"type\": \"" + tp +"\"");
    }
    private void printValue(String v) throws IOException {
        printTab();
        osw.append("\"value\": \"" + v +"\",\n");
    }
    private void printValueD(String v) throws IOException {
        printTab();
        osw.append("\"value\": " + v +",\n");
    }
    private void printTokenId() throws IOException {
        printTab();
        osw.append("\"tokenId\": ");
        osw.append(tokenId.toString());
        tokenId++;
        osw.append("\n");
    }
    private void printNULL(String v) throws IOException {
        printTab();
        osw.append("\""+ v + "\": null");
    }
    void list(List<ParseTree> arr) throws IOException {
        Integer n = 0;
        for (ParseTree e:arr
        ) {
            visit(e);
            n++;
            end(n == arr.size());
        }
    }
    boolean eprog = false;

    private void printIC(String code) throws IOException {
        oswR.append(code);
    }
    boolean rightvalue = false;
    private String mapType(String type) {
        String ans = "";
        if (type.equals("int")) {
            ans = "i32";
        } else if (type.equals("void")) {
            ans = "void";
        } else if (type.equals("float")) {
            ans = "f32";
        }
        return ans;
    }
    private String mapOp(String op) {
        String ans = "";
        if (op.equals("+")) {
            ans = ("add");
        } else if (op.equals("-")) {
            ans = ("sub");
        } else if (op.equals("*")) {
            ans = ("mul");
        } else if (op.equals("/")) {
            ans = ("div");
        } else if (op.equals("%")) {
            ans = ("rem");
        } else if (op.equals("<")) {
            ans = "lt";
        } else if (op.equals(">")) {
            ans = "gt";
        } else if (op.equals("==")) {
            ans = "eq";
        } else if (op.equals("!=")) {
            ans = "ne";
        } else if (op.equals(">=")) {
            ans = "ge";
        } else if (op.equals("<=")) {
            ans = "le";
        } else if (op.equals("=")) {
            ans = "dassign ";
        }
        return ans;
    }
    private String calcType(String op, String type1, String type2) {
        String ans = "";
        if (op.equals("==") || op.equals("!=") || op.equals(">") || op.equals("<") || op.equals(">=") || op.equals("<=")) {
            ans = type1 + " " + type2;
        } else if (type1.equals(type2)) {
            ans = type1;
        }
        return ans;
    }
    private String calcType(String op, String type) {
        String ans;
        ans = type;
        return ans;
    }
    String exprtype = null;

    @Override
    public Integer visitProg(CParser.ProgContext ctx) {
        try {
            scope = 0;

            eprog = true;
            head(null);

            printType("Program");
            end(false);
            ahead("items");
            Integer nf = 0;
            for (ParseTree f:ctx.eprog()
                 ) {
                visit(f);
                nf++;
                end(nf == ctx.eprog().size());
            }
            atail();
            end(true);
            tail();

            end(true);
            eprog = false;

            if (errn != 0) {
                System.out.println("------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitProg(ctx);
    }


    boolean spec = false;
    boolean funcdecl = false;
    boolean cstat = false;
    String cstatname = null;


    Integer stackspec = 0;
    @Override
    public Integer visitFUNC(CParser.FUNCContext ctx) {
        if (!eprog) {
            return 0;
        }
        try {

            printIC("func ");
            OutputStreamWriter roswR = oswR;
            stackspec++;
            oswR = new OutputStreamWriter(new FileOutputStream("spec" + stackspec.toString()));



            head(null);
            printType("FunctionDefine");
            end(false);
            spec = true;
            ahead("specifiers");
            Integer ns = 0;
            for (ParseTree s:ctx.spec()
            ) {
                visit(s);
                ns++;
                end(ns == ctx.spec().size());
            }
            spec = false;
            atail();
            end(false);

            oswR.close();
            oswR = roswR;

            funcdecl = true;
            visit(ctx.funcdecl());
            funcdecl = false;
            end(false);

            InputStreamReader iswR = new InputStreamReader(new FileInputStream("spec" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            stackspec--;
            printIC("{");

            cstat = true;
            cstatname = "body";
            visit(ctx.cstat());
            cstat = false;
            end(true);

            tail();

            printIC("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFUNC(ctx);
    }

    @Override
    public Integer visitFUNCD(CParser.FUNCDContext ctx) {
        if (!eprog) {
            return 0;
        }
        try {

            head(null);
            printType("Declaration");
            end(false);
            spec = true;
            ahead("specifiers");
            Integer ns = 0;
            for (ParseTree s:ctx.spec()
            ) {
                visit(s);
                ns++;
                end(ns == ctx.spec().size());
            }
            spec = false;
            atail();
            end(false);

            isdecl = true;
            funcdecl = true;
            visit(ctx.funcdecl());
            funcdecl = false;
            isdecl = false;
            end(false);

            tokenId++;

            tail();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFUNCD(ctx);
    }

    @Override
    public Integer visitGDECL(CParser.GDECLContext ctx) {
        if (!eprog) {
            return 0;
        }
        dstat = true;
        dstatname = "Declaration";
        visit(ctx.dstat());
        dstat = false;
        tokenId++;
        return super.visitGDECL(ctx);
    }

    @Override
    public Integer visitSpec(CParser.SpecContext ctx) {
        if (!spec) {
            return 0;
        }
        try {
            type = ctx.INTYPE().getText();

            head(null);
            printType("Token");
            end(false);
            printValue(ctx.INTYPE().getText());
            printTokenId();
            tail();

            printIC(" " + mapType(type));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSpec(ctx);
    }

    boolean decl = false;
    boolean parmdecl = true;
    @Override
    public Integer visitFuncdecl(CParser.FuncdeclContext ctx) {
        if (!funcdecl) {
            return 0;
        }
        try {
            head("declarator");
            printType("FunctionDeclarator");
            end(false);

            isfunc = true;
            pushSymbol = true;
            iden = true;
            visit(ctx.iden());
            iden = false;
            pushSymbol = false;
            isfunc = false;
            end(false);

            printIC("(");

            scope++;
            tokenId++;
            if(ctx.parmdecl().size() > 0) {
                parmdecl = true;
                ahead("params");
                Integer np = 0;
                for (ParseTree s:ctx.parmdecl()
                ) {
                    visit(s);
                    np++;
                    end(np == ctx.parmdecl().size());
                    if (np != ctx.parmdecl().size()) {
                        tokenId++;

                        printIC(", ");
                    }
                }
                parmdecl = false;
                atail();
                end(true);
            }
            else {
                printNULL("params");
                end(true);
            }
            tokenId++;


            scope--;
            tail();

            printIC(")");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFuncdecl(ctx);
    }

    boolean stat = false;
    @Override
    public Integer visitCstat(CParser.CstatContext ctx) {
        if (!cstat) {
            return 0;
        }
        try {
            scope++;

            head(cstatname);
            tokenId++;

            printType("CompoundStatement");
            end(false);


            ahead("blockItems");
            Integer ns = 0;
            for (ParseTree s:ctx.stat()
            ) {
                printIC("\n");

                stat = true;
                statname = null;
                visit(s);
                ns++;
                end(ns == ctx.stat().size());
                stat = false;
            }

            atail();

            end(true);

            List<Sympol> table = (scope == 0? data: stack);
            for (int i = table.size() - 1; i >= 0; i--) {
                if (table.get(i).scope_ != scope) {
                    break;
                } else {
                    table.remove(i);
                }
            }
            scope--;
            tail();
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitCstat(ctx);
    }


    boolean iden = false;
    String idenname = null;

    @Override
    public Integer visitSDECL(CParser.SDECLContext ctx) {
        if (!decl) {
            return 0;
        }
        try {
            head("declarator");
            printType("VariableDeclarator");
            end(false);
            isfunc = false;
            pushSymbol = true;
            iden = true;
            idenname = "identifier";
            visit(ctx.iden());
            iden = false;
            pushSymbol = false;
            end(true);
            tail();

            beginadecl = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSDECL(ctx);
    }

    List<String> arrsize = new ArrayList<String>();
    @Override
    public Integer visitADECL(CParser.ADECLContext ctx) {
        if (!decl) {
            return 0;
        }
        try {
            boolean end = endadecl;
            endadecl = false;

            head("declarator");
            printType("ArrayDeclarator");
            end(false);
            decl = true;
            arrn++;
            visit(ctx.decl());
            arrn--;
            end(false);
            decl = false;

            if (beginadecl) {
                printIC(" <");
            }
            beginadecl = false;

            tokenId++;
            printIC("[");

            rightvalue = false;

            expr = true;
            exprname = "expr";
            visit(ctx.expr());
            arrsize.add(ctx.expr().getText());
            end(true);
            expr = false;

            tokenId++;
            printIC("]");

            tail();

            if (end) {
                isarr = true;
                (globalarr? data: stack).get(arrp).arrsize_ = arrsize;
                arrsize = new ArrayList<>();
                //printIC(">");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitADECL(ctx);
    }

    @Override
    public Integer visitIden(CParser.IdenContext ctx) {
        if (!iden) {
            return 0;
        }
        try {
            List<Sympol> table = (scope == 0? data: stack);
            String name = ctx.ID().getText();
            if (pushSymbol) {

                boolean newSymbol = true;
                for (int i = table.size() - 1; i >= 0; i--) {
                    if (table.get(i).scope_ != scope) {
                        newSymbol = true;
                        break;
                    } else if (table.get(i).name_.equals(name)) {
                        newSymbol = false;
                        if (table.get(i).isdecl_) {
                            if (isdecl) {
                                ;
                            } else {
                                table.get(i).isdecl_ = false;
                            }
                        } else {
                            if (isdecl) {
                                ;
                            } else {
                                if (isfunc) {
                                    printError("ES2 >> FunctionDefine:" + name + " is defined.");
                                } else {
                                    printError("ES2 >> Declaration:" + name + " has been declarated.");
                                }
                            }
                        }
                        break;
                    }
                }
                if (newSymbol) {

                    table.add(new Sympol(name, scope, type, arrn, isfunc, isdecl));
                    globalarr = (scope == 0);
                    arrp = table.size() - 1;


                    if (isfunc) {
                        printIC("&" + name);
                    } else if (scope == 0) {
                        printIC("$" + name);
                    } else {
                        printIC("%" + name);
                    }
                }
            } else {
                boolean valid = false;
                for (int i = table.size() - 1; i >= 0; i--) {
                    if (table.get(i).name_.equals(name)) {
                        valid = true;

                        if (table.get(i).isfunc_) {
                            printIC("&" + name);
                        } else {
                            if (rightvalue) {
                                printIC("dread " + mapType(table.get(i).type_) + " ");
                            } else {
                                globalarr = (scope == 0);
                                arrp = i;
                            }
                            if (table.get(i).scope_ == 0) {
                                printIC("$" + name);
                            } else {
                                printIC("%" + name);
                            }
                        }
                        exprtype = mapType(table.get(i).type_);
                        break;
                    }
                }
                if (!valid && scope != 0) {
                    table = data;
                    for (int i = table.size() - 1; i >= 0; i--) {
                        if (table.get(i).name_.equals(name)) {
                            valid = true;

                            if (table.get(i).isfunc_) {
                                printIC("&" + name);
                            } else {
                                if (rightvalue) {
                                    printIC("dread " + mapType(table.get(i).type_) + " ");
                                } else {
                                    globalarr = true;
                                    arrp = i;
                                }
                                if (table.get(i).scope_ == 0) {
                                    printIC("$" + name);
                                } else {
                                    printIC("%" + name);
                                }
                            }
                            exprtype = mapType(table.get(i).type_);
                            break;
                        }
                    }
                }
                if (!valid) {
                    if (isfunc) {
                        printError("ES1 >> FunctionCall:" + name + " is not declarated.");
                    } else {
                        printError("ES1 >> Identifier " + name + " is not defined.");
                    }
                }
            }
            head(idenname);
            printType("Identifier");
            end(false);
            printValue(ctx.ID().getText());
            printTokenId();
            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitIden(ctx);
    }

    @Override
    public Integer visitParmdecl(CParser.ParmdeclContext ctx) {
        if(!parmdecl){
            return 0;
        }
        try {
            head(null);
            printType("ParamsDeclarator");
            end(false);

            OutputStreamWriter roswR = oswR;
            stackspec++;
            oswR = new OutputStreamWriter(new FileOutputStream("spec" + stackspec.toString()));


            spec = true;
            ahead("specifiers");
            Integer ns = 0;
            for (ParseTree s:ctx.spec()
            ) {
                visit(s);
                ns++;
                end(ns == ctx.spec().size());
            }
            spec = false;
            atail();
            end(false);

            oswR.close();
            oswR = roswR;

            decl = true;
            visit(ctx.decl());
            decl = false;
            end(true);

            tail();

            InputStreamReader iswR = new InputStreamReader(new FileInputStream("spec" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            stackspec--;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitParmdecl(ctx);
    }


    boolean dstat = false;
    String dstatname = null;

    boolean endadecl = false;
    boolean beginadecl = false;
    boolean isarr = false;
    @Override
    public Integer visitDstat(CParser.DstatContext ctx) {
        if (!dstat) {
            return 0;
        }
        try {
            head(dstatname);
            printType("Declaration");
            end(false);
            spec = true;
            ahead("specifiers");
            Integer ns = 0;

            OutputStreamWriter roswR = oswR;
            stackspec++;
            oswR = new OutputStreamWriter(new FileOutputStream("spec" + stackspec.toString()));


            for (ParseTree s:ctx.spec()
            ) {
                visit(s);
                ns++;
                end(ns == ctx.spec().size());
            }
            spec = false;
            atail();
            end(false);

            oswR.close();
            oswR = roswR;

            inli = true;
            ahead("initLists");
            Integer ni = 0;
            for (ParseTree i:ctx.inli()
            ) {
                endadecl = true;
                beginadecl = false;

                visit(i);

                endadecl = false;
                beginadecl = false;

                ni++;
                end(ni == ctx.inli().size());
                if (ni != ctx.inli().size()) {
                    tokenId++;
                }
            }
            inli = false;
            atail();
            end(true);

            //tokenId++;
            tail();

            stackspec--;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitDstat(ctx);
    }

    boolean expr = false;
    String exprname = null;
    String statname = null;
    @Override
    public Integer visitRT(CParser.RTContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);
            printType("ReturnStatement");
            end(false);
            tokenId++;

            printIC("return(");
            rightvalue = true;

            expr = true;
            ahead("expr");
            exprname = null;
            visit(ctx.expr());
            end(true);
            expr = false;
            atail();
            end(true);

            tokenId++;
            tail();

            printIC(")");
            rightvalue = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitRT(ctx);
    }

    @Override
    public Integer visitBRT(CParser.BRTContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);
            printType("ReturnStatement");
            end(false);
            tokenId++;
            printNULL("expr");
            end(true);
            tokenId++;
            tail();

            printIC("return()");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBRT(ctx);
    }

    boolean inli = false;
    @Override
    public Integer visitDSTAT(CParser.DSTATContext ctx) {
        if (!stat) {
            return 0;
        }
        dstat = true;
        dstatname = statname;
        visit(ctx.dstat());
        dstat = false;
        tokenId++;
        return super.visitDSTAT(ctx);
    }

    @Override
    public Integer visitESTAT(CParser.ESTATContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);
            printType("ExpressionStatement");
            end(false);
            expr = true;
            ahead("exprs");
            exprname = null;
            visit(ctx.expr());
            expr = false;
            end(true);
            expr = false;
            atail();
            end(true);
            tokenId++;
            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitESTAT(ctx);
    }

    @Override
    public Integer visitCSTATE(CParser.CSTATEContext ctx) {
        if (!stat) {
            return 0;
        }
        cstatname = statname;
        cstat = true;
        visit(ctx.cstat());
        cstat = false;
        return super.visitCSTATE(ctx);
    }

    @Override
    public Integer visitINIT(CParser.INITContext ctx) {
        if (!forinit) {
            return 0;
        }
        try {
            ahead("init");
            expr = true;
            exprname = null;
            visit(ctx.expr());
            end(true);
            expr = false;
            atail();
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitINIT(ctx);
    }

    @Override
    public Integer visitBINIT(CParser.BINITContext ctx) {
        if (!forinit) {
            return 0;
        }
        try {
            printNULL("init");
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBINIT(ctx);
    }

    @Override
    public Integer visitCOND(CParser.CONDContext ctx) {
        if (!forcond) {
            return 0;
        }
        try {
            ahead("cond");
            expr = true;
            exprname = null;
            visit(ctx.expr());
            end(true);
            expr = false;
            atail();;
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitCOND(ctx);
    }

    @Override
    public Integer visitBCOND(CParser.BCONDContext ctx) {
        if (!forcond) {
            return 0;
        }
        try {
            printNULL("cond");
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBCOND(ctx);
    }

    @Override
    public Integer visitSTEP(CParser.STEPContext ctx) {
        if (!forstep) {
            return 0;
        }
        try {
            ahead("step");
            expr = true;
            exprname = null;
            visit(ctx.expr());
            end(true);
            expr = false;
            atail();
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSTEP(ctx);
    }

    @Override
    public Integer visitBSTEP(CParser.BSTEPContext ctx) {
        if (!forstep) {
            return 0;
        }
        try {
            printNULL("step");
            tokenId++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBSTEP(ctx);
    }

    boolean forinit = false, forcond = false, forstep = false;
    Integer stackforstep = 0;
    @Override
    public Integer visitFOR(CParser.FORContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);

            printType("IterationStatement");
            end(false);
            tokenId++;

            tokenId++;

            forinit = true;
            visit(ctx.forinit());
            end(false);
            forinit = false;

            printIC("\nwhile(");

            forcond = true;
            visit(ctx.forcond());
            end(false);
            forcond = false;

            stackforstep++;

            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("forstep" + stackforstep.toString()));

            forstep = true;
            visit(ctx.forstep());
            end(false);
            forstep = false;
            oswR.close();
            oswR = roswR;
            printIC("{");

            loop++;
            stat = true;
            statname = "stat";
            visit(ctx.stat());
            end(true);
            stat = false;
            loop--;

            tail();
            printIC("\n");
            InputStreamReader iswR = new InputStreamReader(new FileInputStream("forstep" + stackforstep.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            stackforstep--;
            printIC("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFOR(ctx);
    }

    @Override
    public Integer visitFORD(CParser.FORDContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);

            printType("IterationDeclaredStatement");
            end(false);
            tokenId++;

            tokenId++;

            scope++;

            dstat = true;
            dstatname = "init";
            visit(ctx.dstat());
            end(false);
            dstat = false;
            tokenId++;

            forcond = true;
            visit(ctx.forcond());
            end(false);
            forcond = false;

            forstep = true;
            visit(ctx.forstep());
            end(false);
            forstep = false;
            scope--;

            loop++;
            stat = true;
            statname = "stat";
            visit(ctx.stat());
            end(true);
            stat = false;
            loop--;

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFORD(ctx);
    }

    @Override
    public Integer visitBREAK(CParser.BREAKContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);

            printType("BreakStatement");
            end(true);
            tokenId++;
            if (loop == 0) {
                printError("ES3 >> BreakStatement:must be in a LoopStatement.");
            }
            tokenId++;

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.visitBREAK(ctx);
    }

    @Override
    public Integer visitSIF(CParser.SIFContext ctx) {
        if (!stat) {
            return 0;
        }
        try {
            head(statname);

            printType("SelectionStatement");
            end(false);
            tokenId++;

            tokenId++;

            ahead("cond");
            expr = true;
            exprname = null;
            visit(ctx.expr());
            end(true);
            expr = false;
            atail();
            end(false);

            tokenId++;

            stat = true;
            statname = "then";
            visit(ctx.stat());
            end(false);
            stat = false;

            printNULL("otherwise");
            end(true);

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSIF(ctx);
    }


    @Override
    public Integer visitWILD(CParser.WILDContext ctx) {
        if (!inli) {
            return 0;
        }
        try {
            printIC("var ");

            head(null);
            printType("InitList");
            end(false);

            decl = true;
            visit(ctx.decl());
            decl = false;
            end(false);
            printNULL("exprs");
            end(true);

            tail();

            InputStreamReader iswR = new InputStreamReader(new FileInputStream("spec" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            if (isarr) {
                printIC(">");
                isarr = false;
            }
            printIC("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitWILD(ctx);
    }

    Integer stackdecl = 0;
    @Override
    public Integer visitMILD(CParser.MILDContext ctx) {
        if (!inli) {
            return 0;
        }
        try {
            printIC("var ");

            head(null);
            printType("InitList");
            end(false);

            decl = true;


            stackdecl++;
            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("decl" + stackdecl.toString()));

            visit(ctx.decl());
            decl = false;
            end(false);

            oswR.close();
            oswR = roswR;

            InputStreamReader iswR = new InputStreamReader(new FileInputStream("decl" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();


            iswR = new InputStreamReader(new FileInputStream("spec" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            if (isarr) {
                printIC(">");
                isarr = false;
            }
            printIC("\n");

            printIC("dassign ");
            iswR = new InputStreamReader(new FileInputStream("decl" + stackspec.toString()));
            while (iswR.ready()) {
                oswR.write(iswR.read());
            }
            iswR.close();
            stackdecl--;
            printIC("(");
            rightvalue = true;

            tokenId++;
            expr = true;
            ahead("exprs");
            exprname = null;
            visit(ctx.expr());
            expr = false;
            end(true);
            expr = false;
            atail();
            end(true);

            tail();

            rightvalue = false;
            printIC(")\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitMILD(ctx);
    }


    @Override
    public Integer visitSIC(CParser.SICContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("IntegerConstant");
            end(false);
            printValueD(ctx.ic().INT().getText());
            printTokenId();
            tail();

            printIC((rightvalue ? "constval i32 " : "") + ctx.ic().INT().getText());
            exprtype = "i32";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSIC(ctx);
    }

    @Override
    public Integer visitSSC(CParser.SSCContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("StringConstant");
            end(false);
            printValueD("\"" + ctx.sc().STRL().getText().replaceAll("\\\\", "\\\\\\\\").replaceAll("\\\"", "\\\\\\\"") + "\"");
            printTokenId();
            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSSC(ctx);
    }

    @Override
    public Integer visitSFC(CParser.SFCContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("FloatConstant");
            end(false);
            printValueD(ctx.fc().FLO().getText());
            printTokenId();
            tail();

            printIC((rightvalue ? "constval f32 " : "") + ctx.fc().FLO().getText());
            exprtype = "f32";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitSFC(ctx);
    }

    @Override
    public Integer visitSIDEN(CParser.SIDENContext ctx) {
        if (!expr) {
            return 0;
        }
        pushSymbol = false;
        iden = true;
        idenname = exprname;
        visit(ctx.iden());
        iden = false;
        return super.visitSIDEN(ctx);
    }

    @Override
    public Integer visitBUCKET(CParser.BUCKETContext ctx) {
        if (!expr) {
            return 0;
        }
        try {

            exprname = "expr";
            expr = true;
            visit(ctx.expr());
            expr = false;
            end(false);


            tail();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBUCKET(ctx);
    }

    Integer stackexpr = 0;
    @Override
    public Integer visitPEXPR(CParser.PEXPRContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("PostfixExpression");
            end(false);

            stackexpr++;

            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("exprR" + stackexpr.toString()));

            exprname = "expr";
            expr = true;
            visit(ctx.expr());
            expr = false;
            end(false);
            oswR.close();

            oswR = roswR;
            head("op");
            printType("Token");
            end(false);
            printValue(ctx.op.getText());
            printTokenId();
            tail();
            end(true);

            tail();

            if (ctx.op.getText().equals("++")) {
                printIC("dassign ");
                InputStreamReader iswR = new InputStreamReader(new FileInputStream("exprR" + stackexpr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                printIC("(");
                printIC("add ");
                printIC(mapType(type));
                printIC("(dread ");
                printIC(mapType(type));
                printIC(" ");
                iswR = new InputStreamReader(new FileInputStream("exprR" + stackexpr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                printIC(", constval i32 1))");
                stackexpr--;
            } else if (ctx.op.getText().equals("--")) {
                printIC("dassign ");
                InputStreamReader iswR = new InputStreamReader(new FileInputStream("exprR" + stackexpr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                printIC("(");
                printIC("sub ");
                printIC(exprtype);
                printIC("(dread ");
                printIC(exprtype);
                printIC(" ");
                iswR = new InputStreamReader(new FileInputStream("exprR" + stackexpr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                printIC(", constval i32 1))");
                stackexpr--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitPEXPR(ctx);
    }

    Integer stack1expr = 0;
    Integer stack2expr = 0;
    @Override
    public Integer visitBEXPR(CParser.BEXPRContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("BinaryExpression");
            end(false);
            stack1expr++;
            OutputStreamWriter rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("1expr" + stack1expr.toString()));

            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("1exprR" + stack1expr.toString()));

            exprname = "expr1";
            expr = true;

            rightvalue = true;

            visit(ctx.expr(0));

            rightvalue = false;

            String e1type = exprtype;
            expr = false;
            end(false);
            osw.close();
            oswR.close();

            stack2expr++;
            //rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("2expr" + stack2expr.toString()));

            //roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("2exprR" + stack2expr.toString()));

            exprname = "expr2";
            expr = true;

            rightvalue = true;

            visit(ctx.expr(1));

            rightvalue = false;

            String e2type = exprtype;
            expr = false;
            end(true);
            osw.close();
            oswR.close();

            osw = rosw;
            oswR = roswR;
            head("op");
            printType("Token");
            end(false);
            printValue(ctx.op.getText());
            printTokenId();
            tail();
            end(false);

            InputStreamReader isw = new InputStreamReader(new FileInputStream("1expr" + stack1expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            isw = new InputStreamReader(new FileInputStream("2expr" + stack2expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            tail();

            {
                printIC(mapOp(ctx.op.getText()) + " ");

                exprtype = calcType(ctx.op.getText(), e1type, e2type);
                printIC(exprtype);

                printIC("(");
                InputStreamReader iswR = new InputStreamReader(new FileInputStream("1exprR" + stack1expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack1expr--;

                printIC(",");
                iswR = new InputStreamReader(new FileInputStream("2exprR" + stack2expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack2expr--;
                printIC(")");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBEXPR(ctx);
    }

    @Override
    public Integer visitDASSIGN(CParser.DASSIGNContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("BinaryExpression");
            end(false);
            stack1expr++;
            OutputStreamWriter rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("1expr" + stack1expr.toString()));

            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("1exprR" + stack1expr.toString()));

            exprname = "expr1";
            iden = true;

            rightvalue = false;

            visit(ctx.iden());


            String e1type = exprtype;
            iden = false;
            end(false);
            osw.close();
            oswR.close();

            stack2expr++;
            //rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("2expr" + stack2expr.toString()));

            //roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("2exprR" + stack2expr.toString()));

            exprname = "expr2";
            expr = true;

            rightvalue = true;

            visit(ctx.expr());

            rightvalue = false;

            String e2type = exprtype;
            expr = false;
            end(true);
            osw.close();
            oswR.close();

            osw = rosw;
            oswR = roswR;
            head("op");
            printType("Token");
            end(false);
            printValue(ctx.op.getText());
            printTokenId();
            tail();
            end(false);

            InputStreamReader isw = new InputStreamReader(new FileInputStream("1expr" + stack1expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            isw = new InputStreamReader(new FileInputStream("2expr" + stack2expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            tail();

            if (ctx.op.getText().equals("=")) {
                printIC("dassign ");
                exprtype = e1type;
                rightvalue = false;
                InputStreamReader iswR = new InputStreamReader(new FileInputStream("1exprR" + stack1expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack1expr--;
                printIC("(");
                iswR = new InputStreamReader(new FileInputStream("2exprR" + stack2expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack2expr--;
                printIC(")");
            } else {
                printIC(mapOp(ctx.op.getText()) + " ");

                exprtype = calcType(ctx.op.getText(), e1type, e2type);
                printIC(exprtype);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitDASSIGN(ctx);
    }

    boolean globalarr = false;
    Integer arrp = 0;
    @Override
    public Integer visitIASSIGN(CParser.IASSIGNContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("BinaryExpression");
            end(false);
            stack1expr++;
            OutputStreamWriter rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("1expr" + stack1expr.toString()));

            OutputStreamWriter roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("1exprR" + stack1expr.toString()));

            exprname = "expr1";
            iden = true;

            rightvalue = false;

            visit(ctx.iden());

            rightvalue = false;

            String e1type = exprtype;
            iden = false;
            end(false);
            for (Integer i = 0; i < ctx.expr().size() - 1; i++) {
                printIC(",");
                expr = true;
                rightvalue = true;
                visit(ctx.expr(i));
                rightvalue = false;
                expr = false;
            }
            osw.close();
            oswR.close();

            stack2expr++;
            //rosw = osw;
            osw = new OutputStreamWriter(new FileOutputStream("2expr" + stack2expr.toString()));

            //roswR = oswR;
            oswR = new OutputStreamWriter(new FileOutputStream("2exprR" + stack2expr.toString()));

            exprname = "expr2";
            expr = true;

            rightvalue = true;

            visit(ctx.expr(ctx.expr().size() - 1));

            rightvalue = false;

            String e2type = exprtype;
            expr = false;
            end(true);
            osw.close();
            oswR.close();

            osw = rosw;
            oswR = roswR;
            head("op");
            printType("Token");
            end(false);
            printValue(ctx.op.getText());
            printTokenId();
            tail();
            end(false);

            InputStreamReader isw = new InputStreamReader(new FileInputStream("1expr" + stack1expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            isw = new InputStreamReader(new FileInputStream("2expr" + stack2expr.toString()));
            while (isw.ready()) {
                osw.write(isw.read());
            }
            isw.close();

            tail();

            if (ctx.op.getText().equals("=")) {
                printIC("iassign<*");
                exprtype = e1type;
                List<Sympol> table = (globalarr? data: stack);

                for (String c:table.get(arrp).arrsize_
                ) {
                    printIC("[");
                    printIC(c);
                    printIC("]");
                }
                printIC(" ");
                printIC(e1type);
                printIC(">");

                printIC("(");

                printIC("array a32<*");
                exprtype = e1type;

                for (String c:table.get(arrp).arrsize_
                ) {
                    printIC("[");
                    printIC(c);
                    printIC("]");
                }
                printIC(" ");
                printIC(e1type);
                printIC(">");

                printIC("(addrof a32 ");
                InputStreamReader iswR = new InputStreamReader(new FileInputStream("1exprR" + stack1expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack1expr--;


                printIC("),\n");
                iswR = new InputStreamReader(new FileInputStream("2exprR" + stack2expr.toString()));
                while (iswR.ready()) {
                    oswR.write(iswR.read());
                }
                iswR.close();
                stack2expr--;
                printIC(")");
            } else {
                printIC(mapOp(ctx.op.getText()) + " ");

                exprtype = calcType(ctx.op.getText(), e1type, e2type);
                printIC(exprtype);


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitIASSIGN(ctx);
    }

    @Override
    public Integer visitBCALL(CParser.BCALLContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("FunctionCall");
            end(false);

            printIC("call ");

            isfunc = true;
            iden = true;
            idenname = "funcname";
            visit(ctx.iden());
            iden = false;
            isfunc = false;
            end(false);

            printNULL("argList");
            end(true);
            tokenId+=2;
            printIC("()");

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitBCALL(ctx);
    }

    @Override
    public Integer visitCALL(CParser.CALLContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("FunctionCall");
            end(false);

            printIC("call ");

            isfunc = true;
            iden = true;
            idenname = "funcname";
            visit(ctx.iden());
            iden = false;
            isfunc = false;
            end(false);

            tokenId++;
            printIC("(");
            ahead("argList");
            //expr = true;
            Integer ne = 0;
            for (ParseTree e:ctx.expr()
            ) {
                expr = true;
                rightvalue = true;
                visit(e);
                expr = false;
                rightvalue = false;
                ne++;
                end(ne == ctx.expr().size());
                if (ne != ctx.expr().size()) {
                    tokenId++;
                    printIC(",");
                }
            }
            //expr = false;
            atail();
            end(true);
            tokenId++;
            printIC(")");

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitCALL(ctx);
    }

    @Override
    public Integer visitARRE(CParser.ARREContext ctx) {
        if (!expr) {
            return 0;
        }
        try {
            head(exprname);
            printType("ArrayAccess");
            end(false);

            expr = true;
            exprname = "arrayName";
            visit(ctx.expr(0));
            end(false);
            expr = false;

            ahead("elements");
            exprname = null;
            for (int i = 1; i < ctx.expr().size(); i++) {

                tokenId++;

                expr = true;
                visit(ctx.expr(i));
                if (i != ctx.expr().size() - 1) {
                    end(false);
                } else {
                    end(true);
                }
                expr = false;

                tokenId++;
            }
            atail();
            end(true);

            tail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitARRE(ctx);
    }
}
