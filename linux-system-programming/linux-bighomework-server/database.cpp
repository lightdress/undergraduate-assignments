#include "database.h"
#include <ctime>
#include <cstring>
#include <iostream>

using namespace std;

const string DB_HOST = "localhost";
const string DB_USER = "Otto";
const string DB_PASSWD = "Apo";
const string DB_NAME = "HHH";


MYSQL *connect_db() {
    MYSQL *pconn;

    pconn = (MYSQL *) malloc(sizeof(MYSQL));
    mysql_init(pconn);
    if (mysql_real_connect(pconn, DB_HOST.c_str(), DB_USER.c_str(), DB_PASSWD.c_str(), DB_NAME.c_str(), 0, nullptr, 0)) {
        cerr << "Sucessfully" << endl;
    } else {
        cerr << "Failed" << endl;
        free(pconn);
        pconn = nullptr;
    }
    cerr << " connect to " << DB_NAME << " at " << DB_USER << "@" << DB_HOST << endl;
    return pconn;
}

bool isuser(const string& nick, const string& passwd) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool ans = false;

    if ((pconn = connect_db()) != nullptr) {

        string comm = "select * from `userinfo` where `nick` = '" + nick + "' and `passwd` = '" + passwd +"';";
        cout << comm << endl;
        if (!mysql_query(pconn, comm.c_str())) {
            res = mysql_store_result(pconn);
            ans = (mysql_num_rows(res) > 0);
            mysql_free_result(res);
        } else {
            cerr << "Failed to query while judging user!" << endl;
        }
    }
    mysql_close(pconn);
    free(pconn);
    return ans;
}
bool isuser(const int id, const char passwd[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool ans = false;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `userinfo` where `id` = %d and `passwd` = '%s';", id, passwd);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            ans = (mysql_num_rows(res) > 0);
            mysql_free_result(res);
        } else {
            fputs("Failed to query while judging user!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return ans;
}
int adduser(const char nick[], const char passwd[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    int id = -1;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `userinfo`;");
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            id = mysql_num_rows(res);
            sprintf(comm, "insert into `userinfo` values (%d, '%s', '%s', 1, '', curdate());", id, passwd, nick);
            puts(comm);
            if (mysql_query(pconn, comm)) {
                fputs("Failed to query while adding user!\n", stderr);
                id = -1;
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting users!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }

    return id;
}
userinfo getuser(const int id) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    userinfo ans = {id, "", "", 0, "", ""};

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `userinfo` where `id` = %d;", id);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.passwd = row[1];
                ans.nick = row[2];
                ans.avatar = atoi(row[3]);
                ans.bio = row[4];
                ans.birthday = row[5];
            } else {
                ans.id = -1;
            }
        } else {
            fputs("Failed to query while getting user!\n", stderr);
            ans.id = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}
userinfo getuser(const string& nick) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    userinfo ans = {0, "", "", 0, "", ""};

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `userinfo` where `nick` = '%s';", nick.c_str());
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.id = atoi(row[0]);
                ans.passwd = row[1];
                ans.nick = row[2];
                ans.avatar = atoi(row[3]);
                ans.bio = row[4];
                ans.birthday = row[5];
            } else {
                ans.id = -1;
            }
        } else {
            fputs("Failed to query while getting user!\n", stderr);
            ans.id = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}

int addgroup(const int ownerid, const char name[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    int id = -1;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `groupinfo`;");
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            id = mysql_num_rows(res);
            sprintf(comm, "insert into `groupinfo` values (%d, '%s', 1, curdate());", id, name);
            puts(comm);
            if (mysql_query(pconn, comm)) {
                fputs("Failed to query while adding group!\n", stderr);
                id = -1;
            } else {
                addmembership(id, ownerid);
                setpermission(ownerid, id, owner);
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting groups!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return id;
}
groupinfo getgroup(const string& nick) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    groupinfo ans = {0, "", 0, ""};

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `groupinfo` where `nick` = '%s';", nick.c_str());
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.id = atoi(row[0]);
                ans.nick = row[1];
                ans.avatar = atoi(row[2]);
                ans.anniversary = row[3];
            } else {
                ans.id = -1;
            }
        } else {
            fputs("Failed to query while getting group!\n", stderr);
            ans.id = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}

bool addfriendship(const int idA, const int idB) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `friendship` where (`idA` = %d and `idB` = '%d') or (`idA` = %d and `idB` = %d);",
                idA, idB, idB, idA);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                suc = true;
            } else {
                sprintf(comm, "insert into `friendship` values (%d, %d, curdate()), (%d, %d, curdate());", idA, idB,
                        idB, idA);
                puts(comm);
                if (!mysql_query(pconn, comm)) {
                    suc = true;
                } else {
                    fputs("Failed to query while adding friendship!\n", stderr);
                }
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting friendships!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}

bool addtag(const int masterid, const int goalid, const char text[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "insert into `tag` values (%d, %d, '%s');", masterid, goalid, text);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            suc = true;
        } else {
            fputs("Failed to query while adding tag.\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}



vector<int> listfriendship(const int id) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> friends;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `idB` from `friendship` where `idA` = %d;", id);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (std::size_t i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                friends.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting friendships!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return friends;
}

bool deletefriendship(const int idA, const int idB) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `friendship` where (`idA` = %d and `idB` = '%d') or (`idA` = %d and `idB` = %d);",
                idA, idB, idB, idA);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) == 0) {
                suc = true;
            } else {
                sprintf(comm,
                        "delete from `usermessage` where (`masterid` = %d and `goalid` = %d) or (`masterid` = %d and `goalid` = %d);",
                        idA, idB, idB, idA);
                puts(comm);
                if (!mysql_query(pconn, comm)) {
                    sprintf(comm,
                            "delete from `tag` where (`masterid` = %d and `goalid` = %d) or (`masterid` = %d and `goalid` = %d);",
                            idA, idB, idB, idA);
                    puts(comm);
                    if (!mysql_query(pconn, comm)) {
                        sprintf(comm,
                                "delete from `friendship` where (`idA` = %d and `idB` = '%d') or (`idA` = %d and `idB` = %d);",
                                idA, idB, idB, idA);
                        puts(comm);
                        if (!mysql_query(pconn, comm)) {
                            suc = true;
                        } else {
                            fputs("Failed to query while deleting friendships!\n", stderr);
                        }
                    } else {
                        fputs("Failed to query while deleting tags!\n", stderr);
                    }
                } else {
                    fputs("Failed to query while deleting usermessages!\n", stderr);
                }
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting friendships!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}

bool addmembership(const int gid, const int uid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `membership` where `gid` = %d and `uid` = '%d';", gid, uid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                suc = true;
            } else {
                sprintf(comm, "insert into `membership` values (%d, %d, %d, curdate());", gid, uid, none);
                puts(comm);
                if (!mysql_query(pconn, comm)) {
                    suc = true;
                } else {
                    fputs("Failed to query while adding membership!\n", stderr);
                }
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting memberships!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}

bool setpermission(const int gid, const int uid, const Permission permission) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `membership` where `gid` = %d and `uid` = '%d';", gid, uid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) == 0) {
                suc = true;
            } else {
                sprintf(comm, "update `membership` set permission = %d where `gid` = %d and `uid` = %d;", permission,
                        gid, uid);
                puts(comm);
                if (!mysql_query(pconn, comm)) {
                    suc = true;
                } else {
                    fputs("Failed to query while setting permission!\n", stderr);
                }
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting memberships!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}



vector<int> listmembership(const int gid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> members;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `uid` from `membership` where `gid` = %d;", gid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                members.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting memberships!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return members;
}
bool ingroup(const int uid, const int gid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;

    bool ans = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `membership` where `uid` = %d and `gid` = %d;", uid, gid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            ans = (mysql_num_rows(res) > 0);
        } else {
            fputs("Failed to query while counting memberships!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return ans;
}
vector<int> listgroup(const int uid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> groups;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `gid` from `membership` where `uid` = %d;", uid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                groups.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting groups!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return groups;
}


vector<int> listadministrator(const int gid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> administrators;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `uid` from `membership` where `gid` = %d and `permission` < %d;", gid, none);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                administrators.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting administrators!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return administrators;
}

int getowner(const int gid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    int ans;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `uid` from `membership` where `gid` = %d and `permission` = %d;", gid, owner);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            row = mysql_fetch_row(res);
            ans = atoi(row[0]);
            mysql_free_result(res);
        } else {
            fputs("Failed to query while searching owwner!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return ans;
}

bool deletemembership(const int gid, const int uid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `membership` where `gid` = %d and `uid` = '%d';", gid, uid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) == 0) {
                suc = true;
            } else {
                sprintf(comm, "delete from `groupmessage` where (`masterid` = %d and `goalid` = %d);", uid, gid);
                puts(comm);
                if (!mysql_query(pconn, comm)) {
                    sprintf(comm, "delete from `membership` where `gid` = %d and `uid` = %d;", gid, uid);
                    puts(comm);
                    if (!mysql_query(pconn, comm)) {
                        suc = true;
                    } else {
                        fputs("Failed to query while deleting memberships!\n", stderr);
                    }
                } else {
                    fputs("Failed to query while deleting groupmessages!\n", stderr);
                }
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting memberships!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}

bool addusermessage(const time_t t, const int masterid, const int goalid, const char type[], const char text[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "insert into `usermessage` values (from_unixtime(%ld), %d, %d, '%s', '%s');", t, masterid, goalid, type,
                text);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            suc = true;
        } else {
            fputs("Failed to query while adding user message.\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}
vector<usermessage> getusermessage(const int idA, const int idB) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<usermessage> messages;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `usermessage` where (`masterid` = %d and `goalid` = %d) || (`masterid` = %d and `goalid` = %d) order by `time`;", idA, idB, idB, idA);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                messages.push_back({string(row[0]), atoi(row[1]), atoi(row[2]), string(row[3]), string(row[4])});
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while getting usermessages!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return messages;
}

bool addgroupmessage(const time_t t, const int masterid, const int goalid, const char type[], const char text[]) {
    MYSQL *pconn;
    MYSQL_RES *res;
    bool suc = false;
    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "insert into `groupmessage` values (from_unixtime(%ld), %d, %d, '%s', '%s');", t, masterid, goalid, type,
                text);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            suc = true;
        } else {
            fputs("Failed to query while adding group message.\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return suc;
}
vector<groupmessage> getgroupmessage(const int gid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<groupmessage> messages;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `groupmessage` where `goalid` = %d order by `time`;", gid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                messages.push_back({string(row[0]), atoi(row[1]), atoi(row[2]), string(row[3]), string(row[4])});
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while getting groupmessages!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return messages;
}

issue getissue(const int id) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    issue ans = {0, "", 0, "", ""};
    if ((pconn = connect_db()) != nullptr) {
        string comm = "select * from `issue` where `id` = " + to_string(id) + ";";

        cout << comm << endl;
        if (!mysql_query(pconn, comm.c_str())) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.id = atoi(row[0]);
                ans.title = row[1];
                ans.uid = atoi(row[2]);
                ans.text = row[3];
                ans.time = row[4];
            } else {
                ans.id = -1;
            }
        } else {
            cerr << "Failed to query while getting issue!" << endl;
            ans.id = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}
issue getissue(const string& title) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    issue ans = {0, "", 0, "", ""};
    if ((pconn = connect_db()) != nullptr) {
        string comm = "select * from `issue` where `title` = '" + title + "';";

        cout << comm << endl;
        if (!mysql_query(pconn, comm.c_str())) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.id = atoi(row[0]);
                ans.title = row[1];
                ans.uid = atoi(row[2]);
                ans.text = row[3];
                ans.time = row[4];
            } else {
                ans.id = -1;
            }
        } else {
            cerr << "Failed to query while getting issue!" << endl;
            ans.id = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}
int addissue(const string& title, const int uid, const string& text, const time_t time) {
    MYSQL *pconn;
    MYSQL_RES *res;
    int id = -1;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `issue`;");
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            id = mysql_num_rows(res);
            sprintf(comm, "insert into `issue` values (%d, '%s', %d, '%s', from_unixtime(%ld));", id, title.c_str(), uid, text.c_str(), time);
            puts(comm);
            if (mysql_query(pconn, comm)) {
                fputs("Failed to query while adding issue!\n", stderr);
                id = -1;
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting issues!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return id;
}
vector<int> listbbs() {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> issues;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `id` from `issue` order by `time`;");
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                issues.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting issues!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return issues;
}

int addreply(const int iid, const int uid, const std::string& type, const std::string& text, const time_t time) {
    MYSQL *pconn;
    MYSQL_RES *res;
    int floor = -1;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select * from `reply` where `iid` = %d;", iid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            floor = mysql_num_rows(res);
            sprintf(comm, "insert into `reply` values (%d, %d, %d, '%s', '%s', from_unixtime(%ld));", iid, floor, uid, type.c_str(), text.c_str(), time);
            puts(comm);
            if (mysql_query(pconn, comm)) {
                fputs("Failed to query while adding reply!\n", stderr);
                floor = -1;
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting replies!\n", stderr);
        }
        mysql_close(pconn);
        free(pconn);
    }
    return floor;
}
reply getreply(const int iid, const int floor) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    reply ans = {0, 0, 0, "", ""};
    if ((pconn = connect_db()) != nullptr) {
        string comm = "select * from `reply` where `iid` = " + to_string(iid) + " and `floor` = " + to_string(floor) + ";";

        cout << comm << endl;
        if (!mysql_query(pconn, comm.c_str())) {
            res = mysql_store_result(pconn);
            if (mysql_num_rows(res) > 0) {
                row = mysql_fetch_row(res);
                mysql_free_result(res);

                ans.iid = atoi(row[0]);
                ans.floor = atoi(row[1]);
                ans.uid = atoi(row[2]);
                ans.type = row[3];
                ans.text = row[4];
                ans.time = row[5];
            } else {
                ans.floor = -1;
            }
        } else {
            cerr << "Failed to query while getting reply!" << endl;
            ans.floor = -1;
        }
        mysql_close(pconn);
        free(pconn);
    }
    return ans;
}
vector<int> listissue(const int iid) {
    MYSQL *pconn;
    MYSQL_RES *res;
    MYSQL_ROW row;
    vector<int> replies;

    if ((pconn = connect_db()) != nullptr) {
        char comm[1024] = "\0";
        sprintf(comm, "select `floor` from `reply` where `iid` = %d order by `time`;", iid);
        puts(comm);
        if (!mysql_query(pconn, comm)) {
            res = mysql_store_result(pconn);
            for (int i = 0; i < mysql_num_rows(res); i++) {
                row = mysql_fetch_row(res);
                replies.push_back(atoi(row[0]));
            }
            mysql_free_result(res);
        } else {
            fputs("Failed to query while counting replies!\n", stderr);
        }
    }
    mysql_close(pconn);
    free(pconn);
    return replies;
}