#include "mainwindowtest.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindowtest w;
    w.show();
    return a.exec();
}
