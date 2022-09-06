#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <sys/wait.h>
#include <cstring>
#include <fcntl.h>

using namespace std;

string PWD;
string getpos(const string& pos) {
    string ans;
    if (pos.at(0) == '/') {
        ans = pos;
    } else {
        ans = PWD + "/" + pos;
    }
    if (ans.at(ans.size() - 1) == '/') {
        ans.pop_back();
    }
    return ans;
}
string getname(string pos) {
    string unflash = pos;
    if (unflash.at(unflash.size() - 1) == '/') {
        unflash.pop_back();
    }
    size_t afterflash = 0;
    size_t found = unflash.find("/");
    while (found != string::npos) {
        afterflash = found + 1;
        found = unflash.find("/", found + 1);
    }
    return unflash.substr(afterflash);
}
vector<string> getargv(const string& line) {
    istringstream iss(line);
    string arg;
    vector<string> ans;
    while (iss >> arg) {
        ans.push_back(arg);
    }
    return ans;
}

void lsreg(const string& pos) {
    cout << pos << endl;
}
void lsdir(const string& pos, bool multi) {
    if (multi) {
        cout << pos << (pos.at(pos.size() - 1) == '/' ? ":" : "/:") << endl;
    }
    DIR *dir = opendir(getpos(pos).c_str());
    struct dirent *entry;
    while ((entry = readdir(dir)) != nullptr) {
        if (entry->d_name == "."s || entry->d_name == ".."s) {
            ;
        }
        cout << entry->d_name << endl;
    }
}
void myls(const string& pos, bool multi) {
    struct stat status{};
    memset(&status, '\0', sizeof(status));
    if (stat(getpos(pos).c_str(), &status) < 0) {
        cerr << "ls: cannot access '" << pos << "': No such file or directory" << endl;
        return;
    }
    if (1 == S_ISDIR(status.st_mode)) {
        lsdir(pos, multi);
    } else {
        lsreg(pos);
    }
}

void cpreg(const char *sem, const char *target) {
    struct stat status{};
    stat(sem, &status);
    char *buf = (char*)calloc(status.st_size, sizeof(char));
    int fin = open(sem, O_RDONLY, S_IRUSR);
    int fout = creat(target, status.st_mode);
    int flag;
    while ((flag = read(fin, buf, status.st_size)) > 0) {
        write(fout, buf, flag);
    }
    free(buf);
    close(fin);
    close(fout);
}
void cpdir(const char *sem, const char *target) {
    DIR *dir = opendir(sem);
    char newsem[MAX_INPUT];
    char newtarget[MAX_INPUT];
    struct dirent *entry;
    while ((entry = readdir(dir)) != nullptr) {
        if (strcmp(entry->d_name, ".") == 0 ||
            strcmp(entry->d_name, "..") == 0) {
            continue;
        }

        strcpy(newsem, sem);
        strcat(newsem, "/");
        strcat(newsem, entry->d_name);
        strcpy(newtarget, target);
        strcat(newtarget, "/");
        strcat(newtarget, entry->d_name);

        if (entry->d_type == DT_LNK) {
            char lntarget[MAX_INPUT];
            readlink(newsem, lntarget, sizeof(lntarget));
            symlink(lntarget, newtarget);
        } else if (entry->d_type == DT_REG) {
            cpreg(newsem, newtarget);
        } else if (entry->d_type == DT_DIR) {
            struct stat status{};
            stat(newsem, &status);
            mkdir(newtarget, status.st_mode);
            cpdir(newsem, newtarget);
        }
    }
}
void mycp(const string& sem, const string& target) {
    struct stat status{};
    memset(&status, '\0', sizeof(status));
    if (stat(getpos(sem).c_str(), &status) < 0) {
        cerr << "cp: cannot access '" << sem << "': No such file or directory" << endl;
        return;
    }
    if (1 == S_ISDIR(status.st_mode)) {
        if (stat(getpos(target).c_str(), &status) < 0) {
            mkdir(target.c_str(), status.st_mode);
            cpdir(sem.c_str(), target.c_str());
        } else {
            cerr << "cp: target '" << target << "' is not a directory" << endl;
        }
    } else {
        cpreg(sem.c_str(), target.c_str());
    }
}

void rmreg(const string& pos) {
    unlink(pos.c_str());
}
void rmdir(const string& pos) {
    DIR *dir = opendir(pos.c_str());
    char newsem[MAX_INPUT];
    char newtarget[MAX_INPUT];
    string newpos;
    struct dirent *entry;
    while ((entry = readdir(dir)) != nullptr) {
        if (strcmp(entry->d_name, ".") == 0 ||
            strcmp(entry->d_name, "..") == 0) {
            continue;
        }
        newpos = pos + "/" + entry->d_name;
        if (entry->d_type == DT_DIR) {
            rmdir(newpos);
            rmdir(newpos.c_str());
        } else {
            rmreg(newpos);
        }
    }
}
void myrm(string pos) {
    struct stat status{};
    memset(&status, '\0', sizeof(status));
    if (stat(getpos(pos).c_str(), &status) < 0) {
        cerr << "rm: cannot access '" << pos << "': No such file or directory" << endl;
        return;
    }
    if (1 == S_ISDIR(status.st_mode)) {
        rmdir(pos);
    } else {
        rmreg(pos);
    }
}

int main() {
    PWD = getenv("PWD");
    string history;
    int pid;
    while (true) {
        cout << getenv("USER") << ":" << PWD << ("root"s == getenv("USER")? "# "s: "$ "s);
        string line;
        getline(cin, line);
        vector<string> myargv = getargv(line);
        if (myargv.empty()) {
            continue;
        }
        if (myargv.at(0) == "exit") {
            ofstream ofs(getenv("HOME") + "/.mysh_history"s, ios_base::ate);
            ofs << history << flush;
            ofs.close();
            break;
        } else {
            history += line + "\n";
            if (myargv.at(0) == "pwd") {
                cout << PWD << endl;
            } else if (myargv.at(0) == "cd") {
                switch (myargv.size()) {
                    case 1: {
                        PWD = ("root"s == getenv("USER") ? "/root"s : "/home/"s + getenv("USER"));
                        break;
                    }
                    case 2: {
                        string newPWD = getpos(myargv.at(1));
                        if (opendir(newPWD.c_str()) == nullptr) {
                            cerr << "mysh: cd: " << myargv.at(1) << ": No such file or directory" << endl;
                        } else {
                            PWD = newPWD;
                        }
                        break;
                    }
                    default: {
                        cerr << "mysh: cd: too many arguments" << endl;
                        break;
                    }

                }
            } else if (myargv.at(0) == "ls") {
                switch (myargv.size()) {
                    case 1: {
                        myls(".", false);
                        break;
                    }
                    case 2: {
                        myls(myargv.at(1), false);
                        break;
                    }
                    default: {
                        for (auto it = myargv.begin() + 1; it != myargv.end(); it++) {
                            myls(*it, true);
                        }
                        break;
                    }

                }
            } else if (myargv.at(0) == "rm") {
                switch (myargv.size()) {
                    case 1: {
                        cerr << "rm: missing operand" << endl;
                        break;
                    }
                    default: {
                        for (auto it = myargv.begin() + 1; it != myargv.end(); it++) {
                            struct stat status{};
                            memset(&status, '\0', sizeof(status));
                            if (stat(getpos(*it).c_str(), &status) < 0) {
                                cerr << "rm: cannot access '" << *it << "': No such file or directory" << endl;
                                break;
                            }
                            remove(getpos(*it).c_str());
                        }
                        break;
                    }

                }
            } else if (myargv.at(0) == "mkdir") {
                switch (myargv.size()) {
                    case 1: {
                        cerr << "mkdir: missing operand" << endl;
                        break;
                    }
                    default: {
                        for (auto it = myargv.begin() + 1; it != myargv.end(); it++) {
                            struct stat status{};
                            stat(PWD.c_str(), &status);
                            if (opendir(getpos(*it).c_str()) == nullptr) {
                                mkdir(getpos(*it).c_str(), status.st_mode);
                            } else {
                                cerr << "mkdir: cannot create directory ‘mkdir: cannot create directory ‘" << *it << "’: File exists" << endl;
                            }
                        }
                        break;
                    }

                }
            } else if (myargv.at(0) == "mv") {
                switch (myargv.size()) {
                    case 1: {
                        cerr << "mv: missing file operand" << endl;
                        break;
                    }
                    case 2: {
                        cerr << "mv: missing destination file operand after '" << myargv.at(1) << "'" << endl;
                        break;
                    }
                    case 3: {
                        string dirname = getpos(myargv.at(2));
                        struct stat status{};
                        if (opendir(dirname.c_str()) == nullptr) {
                            rename(getpos(myargv.at(1)).c_str(), dirname.c_str());
                            break;
                        }
                    }
                    default: {
                        string dirname = getpos(myargv.at(myargv.size() - 1));
                        if (opendir(dirname.c_str()) == nullptr) {
                            cerr << "cp: target '" << myargv.at(myargv.size() - 1) << "' is not a directory" << endl;
                            break;
                        }
                        for (auto it = myargv.begin() + 1; it != myargv.end() - 1; it++) {
                            rename(getpos(*it).c_str(), (dirname + "/" + getname(*it)).c_str());
                        }
                        break;
                    }
                }
            } else if (myargv.at(0) == "cp") {
                switch (myargv.size()) {
                    case 1: {
                        cerr << "cp: missing file operand" << endl;
                        break;
                    }
                    case 2: {
                        cerr << "cp: missing destination file operand after '" << myargv.at(1) << "'" << endl;
                        break;
                    }
                    case 3: {
                        string dirname = getpos(myargv.at(2));
                        struct stat status{};
                        if (opendir(dirname.c_str()) == nullptr) {
                            mycp(getpos(myargv.at(1)), dirname);
                            break;
                        }
                    }
                    default: {
                        string dirname = getpos(myargv.at(myargv.size() - 1));
                        if (opendir(dirname.c_str()) == nullptr) {
                            cerr << "cp: target '" << myargv.at(myargv.size() - 1) << "' is not a directory" << endl;
                            break;
                        }
                        for (auto it = myargv.begin() + 1; it != myargv.end() - 1; it++) {
                            mycp(getpos(*it), dirname + "/" + getname(*it));
                        }
                        break;
                    }
                }
            } else if (myargv.at(0) == "history") {
                ifstream ifs(getenv("HOME") + "/.mysh_history"s);
                string tmp;
                while (getline(ifs, tmp)) {
                    cout << tmp << endl;
                }
                ifs.close();
                cout << history << flush;
            } else {
                pid = fork();
                if (0 == pid) {
                    string arg;
                    for (auto it = myargv.begin() + 1; it != myargv.end(); it++) {
                        arg += *it + " ";
                    }
                    execlp(myargv.at(0).c_str(), arg.c_str());
                }
                waitpid(pid, nullptr, 0);
            }
        }
    }
    return 0;
}
