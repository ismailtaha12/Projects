#include "mainwindowtest.h"
#include "ui_mainwindowtest.h"
#include <QDir>
#include <QLabel>
#include <QVBoxLayout>
#include"tablefinal.h"
#include <QFileSystemWatcher>
#include <string>
using namespace std;
MainWindowtest::MainWindowtest(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindowtest)
{
    ui->setupUi(this);

    ;

}


MainWindowtest::~MainWindowtest()
{
    delete ui;
}






void MainWindowtest::onButtonClicked()
{
    QPushButton* clickedButton = qobject_cast<QPushButton*>(sender());
    QString filename = clickedButton->text();
    string hashfilename = filename.toStdString();  // Convert QString to std::string

    ui->label_2->setText(clickedButton->text());

    QFile file("C:/Users/MM/Downloads/c++ miu/emails/inbox/" + filename + ".txt");
    if (file.open(QIODevice::ReadOnly | QIODevice::Text)) {
        QTextStream in(&file);
        QString fileContent = in.readAll();
        file.close();
        ui->emailcontent->setText(fileContent);
    } else {
        ui->emailcontent->setText("Check code of file");
    }
}
void MainWindowtest::onButtonClickedreceved()
{
    QPushButton* clickedButton = qobject_cast<QPushButton*>(sender());

    QString filename = clickedButton->text();
    string hashfilename = filename.toStdString();  // Convert QString to std::string
    ui->label_2->setText(clickedButton->text());
    QFile file("C:/Users/MM/Downloads/c++ miu/emails/Receved/" + filename + ".txt");
    if (file.open(QIODevice::ReadOnly | QIODevice::Text)) {
        QTextStream in(&file);
        QString fileContent = in.readAll();
        file.close();
        ui->emailcontent->setText(fileContent);
    } else {
        ui->emailcontent->setText("Check code of file");
    }
}
void MainWindowtest::onButtonClickedspam()
{
    QPushButton* clickedButton = qobject_cast<QPushButton*>(sender());
    QString filename = clickedButton->text();
    string hashfilename = filename.toStdString();  // Convert QString to std::string
    ui->label_2->setText(clickedButton->text());

    QFile file("C:/Users/MM/Downloads/c++ miu/emails/spam/" + filename + ".txt");
    if (file.open(QIODevice::ReadOnly | QIODevice::Text)) {
        QTextStream in(&file);
        QString fileContent = in.readAll();
        file.close();
        ui->emailcontent->setText(fileContent);
    } else {
        ui->emailcontent->setText("Check code of file");
    }
}



void MainWindowtest::on_inboxbutton_clicked()
{

    QLayoutItem* item;
    while ((item = ui->verticalLayout_3->takeAt(0)) != nullptr) {
        QWidget* widget = item->widget();

        // If the item is a widget, remove it from the layout and delete it
        ui->verticalLayout_3->removeWidget(widget);
        delete widget;
    }
    directoryChanged("C:/Users/MM/Downloads/c++ miu/emails/inbox/");




}


void MainWindowtest::on_spambutton_clicked()
{    QLayoutItem* item;
    while ((item = ui->verticalLayout_3->takeAt(0)) != nullptr) {
        QWidget* widget = item->widget();

        // If the item is a widget, remove it from the layout and delete it
        ui->verticalLayout_3->removeWidget(widget);
        delete widget;
    }
    directoryChangedspam("C:/Users/MM/Downloads/c++ miu/emails/spam/");
}




//for inbox
void MainWindowtest::createButton(const QString& fileName)
{QString buttonText = fileName.left(fileName.length() - 4);
    QPushButton* button = new QPushButton(buttonText);
    //layout->addWidget(button);
    ui->verticalLayout_3->addWidget(button);
    connect(button, &QPushButton::clicked, this, &MainWindowtest::onButtonClicked);
}
void MainWindowtest::createButtonspam(const QString& fileName)
{QString buttonText = fileName.left(fileName.length() - 4);
    QPushButton* button = new QPushButton(buttonText);

    ui->verticalLayout_3->addWidget(button);
    connect(button, &QPushButton::clicked, this, &MainWindowtest::onButtonClickedspam);
}
void MainWindowtest::createButtonreceved(const QString& fileName)
{QString buttonText = fileName.left(fileName.length() - 4);
    QPushButton* button = new QPushButton(buttonText);

    ui->verticalLayout_3->addWidget(button);
    connect(button, &QPushButton::clicked, this, &MainWindowtest::onButtonClickedreceved);
}
void MainWindowtest::directoryChangedreceved(const QString& path)
{
    QDir dir(path);
    QStringList files = dir.entryList(QDir::Files);

    for (const QString& file : files)
    {
        createButtonreceved(file);
    }
}
void MainWindowtest::directoryChanged(const QString& path)
{
    QDir dir(path);
    QStringList files = dir.entryList(QDir::Files);

    for (const QString& file : files)
    {
        createButton(file);
    }
}
void MainWindowtest::directoryChangedspam(const QString& path)
{
    QDir dir(path);
    QStringList files = dir.entryList(QDir::Files);

    for (const QString& file : files)
    {
        createButtonspam(file);
    }
}






void MainWindowtest::on_pushButton_7_clicked()
{

    QLayoutItem* item;
    while ((item = ui->verticalLayout_3->takeAt(0)) != nullptr) {
        QWidget* widget = item->widget();

        // If the item is a widget, remove it from the layout and delete it
        ui->verticalLayout_3->removeWidget(widget);
        delete widget;
    }
    directoryChangedreceved("C:/Users/MM/Downloads/c++ miu/emails/Receved/");
}


void MainWindowtest::on_pushButton_clicked()
{
    HTable myHashTable;

    myHashTable.readFromFile("C:/Users/MM/Downloads/Adult Content spam.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Financial spam.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/General Spam Terms.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Legal and Compliance spam.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Lottery and Winning.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Personal Enhancement.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Pharmaceuticals and Medicines.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Privacy and Security.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Tech and Internet spam.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Urgency and Actio.txt");
    myHashTable.readFromFile("C:/Users/MM/Downloads/Weight Loss and Fitness.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/amazingoffers@dealsforyou.net.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/freegifts@amazonhelp.art.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/youssefashrafdem@gmail.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/lotterywinner@claimprize.biz.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/noreply@gmail.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/promotions9@websitepromoticons.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/rmohamed1403@gmail.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/spamm@spamm.spam.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/info@uber.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/security@facebook.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/support@cib.com.txt");
    myHashTable.checkAndCategorizeMail("C:/Users/MM/Downloads/c++ miu/emails/Receved/support@microsoftservices.com.txt");
}

