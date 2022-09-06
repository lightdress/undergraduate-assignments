drop database `HHH`;
create database `HHH`;
use `HHH`;
create table `userinfo`
(
    `id`       int,
    primary key (`id`),
    `passwd`   varchar(32)   default '' not null,
    `nick`     varchar(32)   default '' unique,
    `avatar`   int           default 1  not null,
    `bio`      varchar(1024) default '' not null,
    `birthday` date                     not null
);
create table `groupinfo`
(
    `id`          int,
    primary key (`id`),
    `nick`        varchar(32) default '' unique,
    `avatar`      int         default 1 not null,
    `anniversary` date                  not null
);

create table `friendship`
(
    `idA`         int,
    foreign key (`idA`) references `userinfo` (`id`),
    `idB`         int,
    foreign key (`idB`) references `userinfo` (`id`),
    primary key (`idA`, `idB`),
    `anniversary` date not null
);
create table `tag`
(
    `masterid` int,
    `goalid`   int,
    foreign key (`masterid`, `goalid`) references `friendship` (`idA`, `idB`),
    `text`     varchar(32) default '' not null
);
create table `membership`
(
    `gid`         int,
    foreign key (`gid`) references `groupinfo` (`id`),
    `uid`         int,
    foreign key (`uid`) references `userinfo` (`id`),
    primary key (`gid`, `uid`),
    `permission`  int default 2 not null,
    `anniversary` date          not null
);

create table `usermessage`
(
    `time`     timestamp     not null,
    `masterid` int,
    `goalid`   int,
    #foreign key (`masterid`, `goalid`) references `friendship` (`idA`, `idB`),
    `type`     varchar(1024) not null,
    `text`     varchar(4096) not null
);
create table `groupmessage`
(
    `time`     timestamp     not null,
    `masterid` int,
    `goalid`   int,
    foreign key (`masterid`, `goalid`) references `membership` (`uid`, `gid`),
    `type`     varchar(1024) not null,
    `text`     varchar(4096) not null
);
create table `issue`
(
    `id`    int,
    primary key (`id`),
    `title` varchar(512) default '' unique,
    `uid`    int,
    foreign key (`uid`) references `userinfo` (`id`),
    `text`  varchar(4096) default '',
    `time`  timestamp not null
);
create table `reply`
(
    `iid`   int,
    foreign key (`iid`) references `issue`(`id`),
    `floor` int,
    primary key (`iid`, `floor`),
    `uid`    int,
    foreign key (`uid`) references `userinfo` (`id`),
    `type`  varchar(1024) not null ,
    `text`  varchar(4096) not null ,
    `time`  timestamp not null
);