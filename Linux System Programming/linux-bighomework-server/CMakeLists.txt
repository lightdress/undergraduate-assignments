cmake_minimum_required(VERSION 3.16)
project(bighomework_server)

set(CMAKE_CXX_STANDARD 14)

add_executable(server server.cpp include.h database.cpp database.h)
target_link_libraries(server libmysqlclient.so pthread)

add_executable(client client.cpp include.h)
target_link_libraries(client pthread)