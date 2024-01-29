#ifndef MAINWINDOWTEST_H
#define MAINWINDOWTEST_H

#include "qscrollarea.h"
#include "qscrollbar.h"
#include <QMainWindow>
#include"tablefinal.h"

QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindowtest;
}
QT_END_NAMESPACE
class SpamWidget; // Forward declaration

class MainWindowtest : public QMainWindow
{
    Q_OBJECT

public:
    MainWindowtest(QWidget *parent = nullptr);
    ~MainWindowtest();

private slots:
    void on_inboxbutton_clicked();

    void on_spambutton_clicked();



    void createButtonspam(const QString& fileName);
    void createButton(const QString& fileName);
    void directoryChanged(const QString& path);
    void onButtonClicked();
    void onButtonClickedspam();
    void directoryChangedspam(const QString& path);
    void onButtonClickedreceved();
    void directoryChangedreceved(const QString& path);
    void createButtonreceved(const QString& fileName);
    void on_pushButton_7_clicked();

    void on_pushButton_clicked();

private:
    Ui::MainWindowtest *ui;
    SpamWidget *spamWidget;
    QScrollBar *scrollBar;
    QWidget *scrollContent;
    QScrollArea *scrollArea;
};
#endif // MAINWINDOWTEST_H
