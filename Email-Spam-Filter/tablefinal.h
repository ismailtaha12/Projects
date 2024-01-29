#ifndef TABLEFINAL_H
#define TABLEFINAL_H
#pragma once
#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <vector>
#include <list>
#include <algorithm>
//#include <iterator>
using namespace std;
const int TableSize = 10000;
//const int DoubleHashVal = 7;
class HTable
{
public:

    class Node {
    public:
        int key;
        string Category;
        string value;
        Node(int K = -1, string category = "", string val = "") {
            key = K; Category = category; value = val;
        }
    };
    HTable();
    int numberOfElements;
    Node table[TableSize];
    int hash(string ID);
    int GetQuadLoc(int Ndx, int& Sign);
    int GetDubLoc(string Category, int Val, int Cnt);
    bool checkmail(string mailaddress);
    void checkAndCategorizeMail(const string& file_path, HTable& hashTable);
    bool checkmailspam(string file_path);
    // Linear Probing
    void insertLinear(string Category, string spamword);
    void removeLinear(string Category);
    string getcategoryinstring(string spamword);
    int searchLinear(string Category);
    string searchLinearforcat(string spamword);
    void checkAndCategorizeMail(string file_path, HTable& hashTable);
    void getcategory(string spamword);
    void getword(string spamword);
    string getwordstring(string spamword);
    string toLowerCase(const string& str) const;
    bool isVowel(char c);
    bool isConsonant(char c);
    bool endsWith(const string& str, string suffix);
    int measure(const string& str);
    bool containsVowel(string& str);
    void replaceSuffix(string& word, string oldSuffix, string newSuffix);
    string porterStemmer(string word);
    void processReceivedFiles(const string& receivedFolderPath);

    // Quadratic Probing
    void insertQuadratic(string Category, string spamword);
    void removeQuadratic(string Category);
    int searchQuadratic(string Category);
    // Double Hashing
    void insertDouble(string Category, string spamword);
    void removeDouble(string Category);
    int searchDouble(string Category);
    // Chaining Hashing
    /*void insertChaining(string Category, string spamword);
    void removeChaining(string Category);
    int searchChaining(string Category);*/
    void readFromFile(const string& file_path);
    void display(ostream& out);
    void categoryofemail(string file_path);
    void checkAndCategorizeMail(string file_path);
};
ostream& operator<< (ostream& out, HTable aHash);

#endif // TABLEFINAL_H
