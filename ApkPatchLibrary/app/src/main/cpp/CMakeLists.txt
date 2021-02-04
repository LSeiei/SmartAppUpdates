# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
# 指定cmake最小支持版本
cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("apkpatch")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
# 添加一个库，根据native-lib.cpp源文件编译一个native-lib的动态库
file(GLOB androidPatchLibrary "androidPatchLibrary/*")
add_library( # Sets the name of the library.
        ApkPatchLibrary

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${androidPatchLibrary} )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
# 查找系统库，这里查找的是系统日志库，并赋值给变量log-lib
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
# 关联要编译的库和系统库（第一个参数必须为目标模块，顺序不能换）
target_link_libraries( # Specifies the target library.
        ApkPatchLibrary

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )