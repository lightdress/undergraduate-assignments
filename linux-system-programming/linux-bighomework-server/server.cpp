#include "include.h"
#include "database.h"

#include <thread>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <cstdlib>
#include <iostream>
#include <map>
#include <string>
#include <cstring>
#include <unistd.h>

#include <sstream>
#include <vector>
#include <fcntl.h>
#include <sys/stat.h>

using namespace std;

int listenfd;
sockaddr_in serveraddr;
socklen_t length;
map<string, int> users;

void kill(const string &senderid, int connectfd) {
    cout << senderid << " leaves" << " from connectfd " << connectfd << endl;
    close(connectfd);
    users.erase(senderid);
}

void user(const string &sendernick) {
    int connectfd = users.at(sendernick);
    while (true) {
        char recvbuf[BUFSIZE];
        char sendbuf[BUFSIZE];
        memset(recvbuf, '\0', sizeof(recvbuf));
        memset(sendbuf, '\0', sizeof(sendbuf));
        int len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
        if (len <= 0) {
            kill(sendernick, connectfd);
            break;
        }
        istringstream iss(recvbuf);
        string method;
        if (iss >> method) {
            if ("ESTABLISH" == method) {
                string destiantion;
                if (iss >> destiantion) {
                    int goalid = getgroup(destiantion).id;
                    if (goalid != -1) {
                        strcpy(sendbuf, "403\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }
                    addgroup(getuser(sendernick).id, destiantion.c_str());
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("JOIN" == method) {
                string desttype;
                string destiantion;
                if (iss >> desttype >> destiantion) {
                    bool group;
                    int goalid;

                    if ("USER" == desttype) {
                        group = false;
                        goalid = getuser(destiantion).id;
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                    }
                    else if ("GROUP" == desttype) {
                        group = true;
                        goalid = getgroup(destiantion).id;
                        if (ingroup(getuser(sendernick).id, goalid)) {
                            strcpy(sendbuf, "403\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }

                    }
                    else {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }



                    if (!group) {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    } else {
                        addmembership(goalid, getuser(sendernick).id);
                        strcpy(sendbuf, "200\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    }

                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("FETCH" == method) {
                string desttype;
                string destiantion;
                if (iss >> desttype >> destiantion) {
                    int goalid;
                    if ("USER" == desttype) {
                        goalid = getuser(destiantion).id;
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        auto messages = getusermessage(getuser(sendernick).id, goalid);
                        for (const auto &m: messages) {
                            strcpy(sendbuf, (m.time + " " + m.type + " FROM " + getuser(m.masterid).nick + "\n").c_str());
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            memset(sendbuf, '\0', sizeof(sendbuf));
                            strcpy(sendbuf, m.text.c_str());
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            memset(sendbuf, '\0', sizeof(sendbuf));
                        }
                    } else if ("GROUP" == desttype) {
                        goalid = getgroup(destiantion).id;
                        if (!ingroup(getuser(sendernick).id, goalid)) {
                            strcpy(sendbuf, "403\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        strcpy(sendbuf, "200\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        memset(sendbuf, '\0', sizeof(sendbuf));
                        auto messages = getgroupmessage(goalid);
                        for (const auto &m: messages) {
                            strcpy(sendbuf, (m.time + " " + m.type + " FROM " + getuser(m.masterid).nick + "\n").c_str());
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            memset(sendbuf, '\0', sizeof(sendbuf));
                            strcpy(sendbuf, m.text.c_str());
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            memset(sendbuf, '\0', sizeof(sendbuf));
                        }
                    } else {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }
                } else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("FORWARD" == method) {
                string desttype;
                string destiantion;
                string infotype;
                if (iss >> desttype >> destiantion >> infotype) {
                    bool group;
                    int goalid;
                    vector<int> dests;
                    bool file;
                    if ("USER" == desttype) {
                        group = false;
                        goalid = getuser(destiantion).id;
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        if (users.count(destiantion) > 0) {
                            dests.push_back(users.at(destiantion));
                        }
                    }
                    else if ("GROUP" == desttype) {
                        group = true;
                        goalid = getgroup(destiantion).id;
                        if (!ingroup(getuser(sendernick).id, goalid)) {
                            strcpy(sendbuf, "403\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        if (goalid == -1) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            continue;
                        }
                        for (auto uid:listmembership(goalid)) {
                            if (users.count(getuser(uid).nick) > 0) {
                                dests.push_back(users.at(getuser(uid).nick));
                            }
                        }
                    }
                    else {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }
                    memset(recvbuf, '\0', sizeof(recvbuf));
                    len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
                    if (len <= 0) {
                        kill(sendernick, connectfd);
                        continue;
                    }
                    strcpy(sendbuf, recvbuf);
                    if ("MSG" == infotype) {
                        file = false;
                    }
                    else if ("FILE" == infotype) {
                        file = true;
                        recvbuf[strlen(recvbuf) - 1] = '\0';
                        int fd = open(recvbuf, O_RDONLY);
                        if (fd < 0) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            close(connectfd);
                            close(fd);
                            continue;
                        }
                        close(fd);
                    }
                    else {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }

                    for (auto fd: dests) {
                        strcpy(sendbuf, ((file? "FILE": "MSG") + " FROM "s + sendernick + "\n").c_str());
                        send(fd, sendbuf, strlen(sendbuf) + 1, 0x0);
                        strcpy(sendbuf, recvbuf);
                        send(fd, sendbuf, strlen(sendbuf) + 1, 0x0);
                    }
                    if (!group) {
                        addusermessage(time(nullptr), getuser(sendernick).id, goalid, (file ? "FILE" : "MSG"), sendbuf);
                    } else {
                        addgroupmessage(time(nullptr), getuser(sendernick).id, goalid, (file ? "FILE" : "MSG"), sendbuf);
                    }

                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("POST" == method) {
                string title;
                if (iss >> title) {
                    if (getissue(title).id != -1) {
                        strcpy(sendbuf, "403\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                        continue;
                    }
                    memset(recvbuf, '\0', sizeof(recvbuf));
                    len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
                    if (len <= 0) {
                        kill(sendernick, connectfd);
                        continue;
                    }
                    addissue(title, getuser(sendernick).id, recvbuf, time(nullptr));
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("REPLY" == method) {
                string title;
                string infotype;
                if (iss >> title >> infotype) {
                    bool file;
                    if (getissue(title).id == -1) {
                        strcpy(sendbuf, "404\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                        continue;
                    }
                    memset(recvbuf, '\0', sizeof(recvbuf));
                    len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
                    if (len <= 0) {
                        kill(sendernick, connectfd);
                        continue;
                    }
                    if ("MSG" == infotype) {
                        file = false;
                    }
                    else if ("FILE" == infotype) {
                        file = true;
                        recvbuf[strlen(recvbuf) - 1] = '\0';
                        int fd = open(recvbuf, O_RDONLY);
                        if (fd < 0) {
                            strcpy(sendbuf, "404\n");
                            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                            close(connectfd);
                            close(fd);
                            continue;
                        }
                        close(fd);
                    }
                    else {
                        strcpy(sendbuf, "400\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        continue;
                    }
                    addreply(getissue(title).id, getuser(sendernick).id, (file ? "FILE" : "MSG"), recvbuf, time(nullptr));
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else if ("EXPLORE" == method) {
                strcpy(sendbuf, "200\n");
                send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                memset(sendbuf, '\0', sizeof(sendbuf));
                auto issues = listbbs();
                for (const auto &iid: issues) {
                    auto i = getissue(iid);
                    strcpy(sendbuf, (i.title + " FROM " + getuser(i.uid).nick + " AT " + i.time + "\n").c_str());
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    memset(sendbuf, '\0', sizeof(sendbuf));
                    strcpy(sendbuf, (i.text).c_str());
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    memset(sendbuf, '\0', sizeof(sendbuf));
                }
            }
            else if ("GET" == method) {
                string title;
                if (iss >> title) {
                    if (getissue(title).id == -1) {
                        strcpy(sendbuf, "404\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                        continue;
                    }
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    memset(sendbuf, '\0', sizeof(sendbuf));
                    auto replies = listissue(getissue(title).id);
                    for (const auto &floor: replies) {
                        auto r = getreply(getissue(title).id, floor);
                        strcpy(sendbuf, ("FLOOR " + to_string(r.floor) + r.type + " FROM " + getuser(r.uid).nick + " AT " + r.time + "\n").c_str());
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        memset(sendbuf, '\0', sizeof(sendbuf));
                        strcpy(sendbuf, r.text.c_str());
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        memset(sendbuf, '\0', sizeof(sendbuf));
                    }
                }
                else {
                    strcpy(sendbuf, "400\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    continue;
                }
            }
            else {
                strcpy(sendbuf, "400\n");
                send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                continue;
            }
        }
    }
}

void download(const int connectfd, const int fd) {
    char sendbuf[BUFSIZE];
    int len;
    while (true) {
        len = read(fd, sendbuf, sizeof(sendbuf));
        if (len <= 0) {
            break;
        }
        //cout << sendbuf << endl;
        send(connectfd, sendbuf, len, 0x0);
    }
    close(fd);
    close(connectfd);
}
void upload(const int connectfd, const int fd) {
    char recvbuf[BUFSIZE];
    int len;
    while (true) {
        len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
        if (len <= 0) {
            break;
        }
        //cout << recvbuf << endl;
        write(fd, recvbuf, len);
    }
    close(fd);
    close(connectfd);
}

int main(int argc, char *argv[]) {
    cout << getenv("PWD") << endl;
    if (argc != 2) {
        cerr << "usage: " << argv[0] << "<port>" << endl;
        exit(1);
    }
    listenfd = socket(AF_INET, SOCK_STREAM, 0);
    if (listenfd < 0) {
        cerr << "no socket" << endl;
        exit(2);
    }
    char *endptr;
    serveraddr = sockaddr_in{AF_INET, htons(strtol(argv[1], &endptr, 10)), htonl(INADDR_ANY)};
    if (bind(listenfd, reinterpret_cast<sockaddr *>(&serveraddr), sizeof(serveraddr)) < 0) {
        cerr << "fail to bind" << endl;
        exit(3);
    }
    if (listen(listenfd, QUENE) < 0) {
        cerr << "fail to listen" << endl;
        exit(4);
    }
    sockaddr_in clientaddr{};
    length = sizeof(clientaddr);
    while (true) {
        int connectfd = accept(listenfd, reinterpret_cast<sockaddr *>(&serveraddr), &length);
        if (connectfd < 0) {
            continue;
        }
        char recvbuf[BUFSIZE];
        char sendbuf[BUFSIZE];
        memset(recvbuf, '\0', sizeof(recvbuf));
        memset(sendbuf, '\0', sizeof(sendbuf));
        int len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
        if (len <= 0) {
            close(connectfd);
            continue;
        }
        istringstream iss(recvbuf);
        string method;
        string nick;
        string passwd;
        if (iss >> method >> nick >> passwd) {
            if (method == "SIGNIN") {
                if (users.count(nick) > 0) {
                    strcpy(sendbuf, "403\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    close(connectfd);
                } else {
                    if (isuser(nick, passwd)) {
                        strcpy(sendbuf, "200\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        cout << nick << " has loged in from connectfd " << connectfd << endl;
                        users[nick] = connectfd;
                        thread(user, nick).detach();
                    } else {
                        strcpy(sendbuf, "404\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                    }
                }
            }
            else if (method == "SIGNUP") {
                if (isuser(nick, passwd)) {
                    strcpy(sendbuf, "403\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    close(connectfd);
                } else {
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    close(connectfd);
                    adduser(nick.c_str(), passwd.c_str());
                }
            }
            else if (method == "DW") {
                if (isuser(nick, passwd)) {
                    memset(recvbuf, '\0', sizeof(recvbuf));
                    len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
                    if (len <= 0) {
                        close(connectfd);
                        continue;
                    }
                    recvbuf[strlen(recvbuf) - 1] = '\0';
                    int fd = open(recvbuf, O_RDONLY);
                    if (fd < 0) {
                        strcpy(sendbuf, "404\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                        close(fd);
                        continue;
                    }
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    cout << nick << " start to download" << recvbuf << " from connectfd " << connectfd << endl;
                    thread(download, connectfd, fd).detach();
                } else {
                    strcpy(sendbuf, "404\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    close(connectfd);
                }
            }
            else if (method == "UP") {
                if (isuser(nick, passwd)) {
                    memset(recvbuf, '\0', sizeof(recvbuf));
                    len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
                    if (len <= 0) {
                        close(connectfd);
                        continue;
                    }
                    recvbuf[strlen(recvbuf) - 1] = '\0';
                    int fd = open(recvbuf, O_RDONLY);
                    if (fd >= 0) {
                        strcpy(sendbuf, "403\n");
                        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                        close(connectfd);
                        close(fd);
                        continue;
                    }
                    close(fd);
                    struct stat status{};
                    stat(".", &status);
                    fd = creat(recvbuf, status.st_mode);
                    strcpy(sendbuf, "200\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    cout << nick << " start to upload" << recvbuf << " from connectfd " << connectfd << endl;
                    thread(upload, connectfd, fd).detach();
                } else {
                    strcpy(sendbuf, "404\n");
                    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                    close(connectfd);
                }
            }
            else {
                strcpy(sendbuf, "400\n");
                send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
                close(connectfd);
                continue;
            }
        }
    }
    //return 0;
}