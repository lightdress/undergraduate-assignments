This course took place in the second semester of Grade 3.

Because of COVID-19, all curricula this semester are taught online.
Interestingly, the teacher didn't appear during the whole semester and only threw us three presentations and the homework.

Honestly, I was lazy that time, too.
As a compact, I did my homework several days before the deadline.

I didn't know the existence of [boost C++ libraries](https://www.boost.org) at that time.
As a result, my programming homework is a mixture of C++ codes and Linux C APIs, which is very inelegant.

The first task is a command line experiment, which is very easy.

The third task is to implement a Unix shell.
The "main" function is too long. Maybe I should reconstruct it with OOP technology.

The second task is about concurrency and socket programming.
Since this course is Linux system programming, I should utilise Linux multiprocess technologies like semaphores.
Nevertheless, I was so lazy that choice to use multithread technology.

The final project is an enhanced version of the second task, whereas it is a team project.
It is a social platform with both IM and BBS functions.
This time, I was the captain.

This project was not the first Linux IM project I had joined.
The first project, called "FootBook", took place in the first semester of Grade 3,
and I was mainly responsible for completing MySQL-related codes.
"FootBook" team used GitHub to manage [the project](https://github.com/ProjGO/Popnil.git),
and I submitted my codes with [another GitHub account](https://github.com/ProjGO/Popnil/commits?author=zhir660).
I reused those MySQL-related codes in this project.
Maybe using [MySQL Connector/C++](https://dev.mysql.com/doc/connector-cpp/8.0/en/) was better than including "mysql.h".
Nevertheless, since [a well-known open-source project](https://github.com/trojan-gfw/trojan.git) also acts like this, I don't think it was a defect.

Whereas the "FootBook" team consisted of some outstanding members,
while working on that project, they were too fidgety.
I suggested they develop a communication protocol at first,
but they didn't do that and just chose to send C structs between clients and the server,
and the choice finally made it hard to debug.

In this project, I only developed the server.
I ensured that data transported between clients and the server could be either strings or files.
As a result, I could debug the program with the help of [the "Netcat" tool](https://nc110.sourceforge.io/).
I recorded the communication protocol in the [README file](linux-bighomework-server/README.md).

My group members finally failed to develop the client.
There were several reasons:

- I was too slow. After I finished the development of the server, there were only a few days left.
- They were not good enough at Linux GUI development.
- I did not explain my design ideas and communication protocol to them clearly.
