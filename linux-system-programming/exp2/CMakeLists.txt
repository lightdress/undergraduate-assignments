cmake_minimum_required(VERSION 3.16)
project(exp2)

set(CMAKE_CXX_STANDARD 14)

add_executable(server server/main.cpp)
add_executable(client client/main.cpp)
target_link_libraries(server pthread)
target_link_libraries(client pthread)