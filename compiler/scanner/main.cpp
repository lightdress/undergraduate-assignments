#include <iostream>
#include <fstream>
#include <string>
using namespace std;
int i = 1;
int j = 0;
int n = 0;
int k = 0;
ifstream ifs;
ofstream ofs;
int main(int argc, char *argv[]) {
    std::cout << "Hello, World!" << std::endl;
    string c = argv[1];
    ifs = cin;
    ifs.open(c);
    ofs.open(c.substr(0, c.length()) + "token");
    return 0;
}
