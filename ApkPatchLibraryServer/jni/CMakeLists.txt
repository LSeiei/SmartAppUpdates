cmake_minimum_required (VERSION 3.19.2)
project(ApkPatchLibraryServer)
MESSAGE(STATUS "PROJECT_NAME: " ${PROJECT_NAME})
   
# 仅支持 Linux
if (NOT CMAKE_HOST_UNIX)
  message(FATAL_ERROR "not linux")
endif()
   
# 需要设置 JAVA_HOME
if(NOT DEFINED ENV{JAVA_HOME})
  message(FATAL_ERROR "not defined environment variable:JAVA_HOME")
endif()
   
MESSAGE( STATUS "JAVA_HOME: " $ENV{JAVA_HOME})
   
SET(JAVA_INCLUDE "$ENV{JAVA_HOME}/include/")
SET(JAVA_INCLUDE_OS "$ENV{JAVA_HOME}/include/linux/")
# SET(JAVA_INCLUDE_OS "$ENV{JAVA_HOME}/include/darwin/")
   
# 添加 include 头文件搜索路径
include_directories("${PROJECT_SOURCE_DIR}" "${JAVA_INCLUDE}" "${JAVA_INCLUDE_OS}")
   
# 添加编译文件
file(GLOB_RECURSE files "*")
add_library(ApkPatchLibraryServer SHARED ${files})
   
set(CMAKE_INSTALL_PREFIX "${PROJECT_SOURCE_DIR}/../")
set(MAVEN_PATH "resources/native")
   
# 编辑生成的 .so 安装到 resources 目录
install (TARGETS ApkPatchLibraryServer DESTINATION "${CMAKE_INSTALL_PREFIX}${MAVEN_PATH}/linux")