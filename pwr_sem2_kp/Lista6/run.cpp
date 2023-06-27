#include <iostream>

using namespace std;

int main() 
{
    static int numberOfFiles = 1;
    string filesNames[1] = {"Demo"};

    system("del *.class");
    string PATH_TO_FX = "D:\\Apps\\Programowanie\\Java\\javafx-sdk-18.0.1\\lib";
    string compileCommand = "javac --module-path " + PATH_TO_FX + " --add-modules javafx.controls ";
    string runCommand = "java --module-path " + PATH_TO_FX + " --add-modules javafx.controls ";
    runCommand += filesNames[numberOfFiles - 1];
    for(int i = 0; i < numberOfFiles; i++)
    {
        string compileTemp = compileCommand + filesNames[i] + ".java";
        string debug = "echo " + compileTemp;
        system(debug.c_str());
        system(compileTemp.c_str());
    }
    system(runCommand.c_str());
    return 0;
}