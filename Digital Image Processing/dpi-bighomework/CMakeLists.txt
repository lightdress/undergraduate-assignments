cmake_minimum_required(VERSION 3.13)
project(bighomework)

set(CMAKE_CXX_STANDARD 14)

add_executable(bighomework main.cpp)

find_package( OpenCV REQUIRED )
include_directories( ${OpenCV_INCLUDE_DIRS} )

target_link_libraries( bighomework ${OpenCV_LIBS} )