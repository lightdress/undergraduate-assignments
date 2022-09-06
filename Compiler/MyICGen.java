package bit.minisys.minicc.icgen;

import bit.minisys.minicc.internal.util.MiniCCUtil;
import bit.minisys.minicc.icgen.internal.JSONVisitor;
import bit.minisys.minicc.icgen.internal.antlr.CLexer;
import bit.minisys.minicc.icgen.internal.antlr.CParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import bit.minisys.minicc.MiniCCCfg;
import bit.minisys.minicc.internal.util.MiniCCUtil;
import bit.minisys.minicc.parser.ast.ASTCompilationUnit;

public class MyICGen implements IMiniCCICGen {
    @Override
    public String run(String iFile) throws Exception {

        String oFile = MiniCCUtil.removeAllExt(iFile) + MiniCCCfg.MINICC_PARSER_OUTPUT_EXT;
        String oFileR = MiniCCUtil.removeAllExt(iFile) + MiniCCCfg.MINICC_ICGEN_OUTPUT_EXT;
        InputStream is = new FileInputStream(iFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CLexer lexer = new CLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser(tokens);
        ParseTree tree = parser.prog();

        FileOutputStream os = new FileOutputStream(oFile);
        FileOutputStream osR = new FileOutputStream(oFileR);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        OutputStreamWriter oswR = new OutputStreamWriter(osR);
        JSONVisitor json = new JSONVisitor(osw, oswR);
        json.visit(tree);
        osw.close();
        oswR.close();
        os.close();
        osR.close();

        System.out.println("5. ICGen finished!");
        return oFileR;
    }
}
