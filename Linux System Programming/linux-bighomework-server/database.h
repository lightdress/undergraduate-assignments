#include <mysql/mysql.h>
#include <string>
#include <vector>

struct userinfo {
    int id;
    std::string passwd;
    std::string nick;
    int avatar;
    std::string bio;
    std::string birthday;

};
struct groupinfo {
    int id;
    std::string nick;
    int avatar;
    std::string anniversary;
};
struct usermessage {
    std::string time;
    int masterid;
    int goalid;
    std::string type;
    std::string text;
};
struct groupmessage {
    std::string time;
    int masterid;
    int goalid;
    std::string type;
    std::string text;
};
enum Permission {
    owner,
    admin,
    none
};

struct issue {
    int id;
    std::string title;
    int uid;
    std::string text;
    std::string time;
};
struct reply {
    int iid;
    int floor;
    int uid;
    std::string type;
    std::string text;
    std::string time;
};

MYSQL *connect_db();

bool isuser(const std::string &nick, const std::string &passwd);

bool isuser(const int id, const char passwd[]);

int adduser(const char nick[], const char passwd[]);

userinfo getuser(const std::string &nick);

userinfo getuser(const int id);

int addgroup(const int ownerid, const char name[]);
groupinfo getgroup(const std::string& nick);

bool addfriendship(const int idA, const int idB);

bool addtag(const int masterid, const int goalid, const char text[]);

std::vector<int> listfriendship(const int id);

bool deletefriendship(const int idA, const int idB);

bool addmembership(const int gid, const int uid);

bool setpermission(const int gid, const int uid, const Permission permission);

std::vector<int> listmembership(const int id);
bool ingroup(const int uid, const int gid);
std::vector<int> listgroup(const int uid);

std::vector<int> listadministrator(const int gid);

int getowner(const int gid);

bool deletemembership(const int gid, const int uid);


bool addusermessage(const time_t t, const int masterid, const int goalid, const char type[], const char text[]);
std::vector<usermessage> getusermessage(const int idA, const int idB);

bool addgroupmessage(const time_t t, const int masterid, const int goalid, const char type[], const char text[]);
std::vector<groupmessage> getgroupmessage(const int gid);

issue getissue(const int id);
issue getissue(const std::string& title);
int addissue(const std::string& title, const int uid, const std::string& text, const time_t time);
std::vector<int> listbbs();

int addreply(const int iid, const int uid, const std::string& type, const std::string& text, const time_t time);
reply getreply(const int iid, const int floor);
std::vector<int> listissue(const int iid);