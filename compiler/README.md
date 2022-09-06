This course took place in the second semester of Grade 3.

The teacher was a young and talented man.
He wrote [a simple C programing language compiler](https://github.com/jiweixing/BIT-MiniCC.git) in Java 1.8.
The compiler consists of 7 parts,
and students can replace any part with their own Java class or binary file.

My scanner part works with [The Fast Lexical Analyzer](https://github.com/westes/flex).
Since flex supports C++, I wrote the lex file in C++ language.
I don't think it's a good choice now.
It is too weird to mix C++ codes with so many printf functions.

Although the teacher also taught us how to use [GNU Bision tool](https://www.gnu.org/software/bison/), I didn't understand it and so used [ANTLR](https://www.antlr.org/) generator to help generate the parser.

After that, I wrote codes directly based on the codes of BIT-MiniCC.
The final codes should be placed in [icgen](https://github.com/jiweixing/BIT-MiniCC/tree/master/src/bit/minisys/minicc/icgen) folder.
I remember that, at the stage of semantics, I could still keep my codes clean, but after that, the codes became more and more chaotic and hard to read.
