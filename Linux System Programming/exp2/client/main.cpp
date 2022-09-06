#include "main.h"

#include <iostream>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <cstring>
#include <string>
#include <unistd.h>
#include <thread>

using namespace std;

int connectfd;
void receiver() {
    while (true) {
        char recvbuf[BUFSIZE];
        char sendbuf[BUFSIZE];
        memset(recvbuf, '\0', sizeof(recvbuf));
        memset(sendbuf, '\0', sizeof(sendbuf));
        int len = recv(connectfd, recvbuf, sizeof(recvbuf), 0x0);
        if (len <= 0) {
            close(connectfd);
            break;
        }
        cout << recvbuf << " from connectfd " << connectfd << endl;
    }
}
int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "usage: %s <port>\n", argv[0]);
        cerr << "usage: "  << argv[0] << "<addr> <port>" << endl;
        exit(1);
    }
    connectfd = socket(AF_INET, SOCK_STREAM, 0);
    if (connectfd < 0) {
        cerr << "no socket" << endl;
        exit(2);
    }
    char *endptr;
    sockaddr_in serveraddr = sockaddr_in{AF_INET, htons(strtol(argv[2], &endptr, 10)), inet_addr(argv[1])};
    if (connect(connectfd, reinterpret_cast<sockaddr*>(&serveraddr), sizeof(serveraddr)) < 0) {
        cerr << "fail to connect" << endl;
        exit(3);
    }
    cout << "user id: " << endl;
    char recvbuf[BUFSIZE];
    char sendbuf[BUFSIZE];
    memset(recvbuf, '\0', sizeof(recvbuf));
    memset(sendbuf, '\0', sizeof(sendbuf));
    string id;
    if (getline(cin, id).eof()) {
        send(connectfd, nullptr, 0, 0x0);
        close(connectfd);
        exit(0);
    }
    strcpy(sendbuf, id.c_str());
    send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
    thread(receiver).detach();
    while (cout << "to: ", !getline(cin, id).eof()) {
        strcpy(sendbuf, id.c_str());
        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
        if (getline(cin, id).eof()) {
            send(connectfd, nullptr, 0, 0x0);
            close(connectfd);
            exit(0);
        }
        strcpy(sendbuf, id.c_str());
        send(connectfd, sendbuf, sizeof(sendbuf), 0x0);
    }
    send(connectfd, nullptr, 0, 0x0);
    close(connectfd);
    exit(0);
}