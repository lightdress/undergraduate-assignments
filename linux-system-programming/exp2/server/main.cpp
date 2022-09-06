#include "main.h"

#include <thread>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <cstdlib>
#include <iostream>
#include <map>
#include <string>
#include <cstring>
#include <unistd.h>

using namespace std;

int listenfd;
sockaddr_in serveraddr;
socklen_t length;
map<string, int> users;
void kill(const string& senderid, int connectfd) {
    cout << senderid << " leaves" << " from connectfd " << connectfd << endl;
    close(connectfd);
    users.erase(senderid);
}
void user(const string& senderid) {
    int connectfd = users.at(senderid);
    while (true) {
        char recvbuf[BUFSIZE];
        char sendbuf[BUFSIZE];
        memset(recvbuf, '\0', sizeof(recvbuf));
        memset(sendbuf, '\0', sizeof(sendbuf));
        int len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
        if (len <= 0) {
            kill(senderid, connectfd);
            break;
        }
        string recverid = recvbuf;
        cout << senderid << " asks " << recvbuf << " from connectfd " << connectfd << endl;
        if (users.find(recverid) == users.end()) {
            cout << "no " << recverid << " from connectfd" << connectfd << endl;
            strcpy(sendbuf, ("no" + recverid).c_str());
            send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
        } else {
            len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
            if (len <= 0) {
                kill(senderid, connectfd);
                break;
            }
            strcpy(sendbuf, ("from " + senderid + ": " + recvbuf).c_str());
            send(users.at(recverid), sendbuf, sizeof(sendbuf), 0x0);
        }
    }
}
int main(int argc, char *argv[]) {
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
    if (bind(listenfd, reinterpret_cast<sockaddr*>(&serveraddr), sizeof(serveraddr)) < 0) {
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
        int connectfd = accept(listenfd, reinterpret_cast<sockaddr*>(&serveraddr), &length);
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
        cout << recvbuf << " has loged in from connectfd " << connectfd << endl;
        string id = recvbuf;
        users[id] = connectfd;
        thread(user, id).detach();
    }
    //return 0;
}