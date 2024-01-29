#define _SILENCE_EXPERIMENTAL_FILESYSTEM_DEPRECATION_WARNING
#include<iostream>
#include<regex>
#include<fstream>
#include<sstream>
#include <cstdlib>
#include<cstring>
#include"tablefinal.h"
#include <experimental/filesystem>
using namespace std;
namespace fs = experimental::filesystem;
HTable::HTable() {
    numberOfElements = 0;
    for (int i = 0; i < TableSize; i++) {
        table[i] = Node();
    }
}
int HTable::hash(string spamword) {
    int ASCII = 0;
    for (char ch : spamword) {
        ASCII += static_cast<int>(ch);
    }
    return ASCII % TableSize;
}
int HTable::GetQuadLoc(int Ndx, int& Sign) {
    return Ndx + Sign * (Sign * Sign);
}

void HTable::readFromFile(const string& file_path) {
    // Open the file
    ifstream infile(file_path);

    // Check if the file is open successfully
    if (!infile.is_open()) {
        cerr << "Error opening file: " << file_path << endl;
        exit(1);
    }

    // Extract the category (file name) from the file path
    // Assuming the file path contains the category information
    string line;
    while (getline(infile, line)) {
        // Extract the category (file name) from the file path
        // Assuming the file path contains the category information
        size_t lastSlash = file_path.find_last_of("/\\");
        size_t lastDot = file_path.find_last_of(".");
        string Category = file_path.substr(lastSlash + 1, lastDot - lastSlash - 1);
        string lowerdd = toLowerCase(line);
        string pot = porterStemmer(lowerdd);
        // Insert the data into the hash table
        insertLinear(Category, pot);
        //cout << line << endl;   // Change this to the desired insert function
    }
    // Close the file
    infile.close();

    // Insert the data into the hash table
    // Change this to the desired insert function
}
bool HTable::checkmail(string mailaddress) {
    regex pattern("^(?=.*[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})(?=.*@(protonmail\\.com|zoho\\.com|gmx\\.com|mailfence\\.com|outlook\\.com|yahoo\\.com|aol\\.com|gmail\\.com|icloud\\.com|tutanota\\.com|yandex\\.com|hubspot\\.com|titan\\.com|mozilla-thunderbird\\.org|sendinblue\\.com|uber\\.com|facebook\\.com|cib\\.com|microsoftservices\\.com|instagram\\.com)$).*");
    return regex_match(mailaddress, pattern);
}

void HTable::insertLinear(string Category, string spamword) {
    int index = hash(spamword);
    int actualindex = index;
    while (table[index].key != -1 && table[index].Category != "" && table[index].value != "") {
        index = (index + 1) % TableSize;
        if (index == actualindex) {
            cerr << "Error Unable To Insert The Table is Full" << endl;
            exit(1);
        }
    }
    table[index].key = index;
    table[index].Category = Category;
    table[index].value = spamword;
    numberOfElements++;
}
void HTable::removeLinear(string spamword) {
    int index = searchLinear(spamword);
    if (index != -1) {
        table[index].key = -1;
        table[index].Category = "";
        table[index].value = "";
        numberOfElements--;
    }
}
string HTable::getcategoryinstring(string spamword) {
    int index = searchLinear(spamword);
    if (index != -1) {
        return table[index].Category;
    }
    else {
        cout << "not found";
    }
    return NULL;
}
void HTable::getcategory(string spamword) {
    int index = searchLinear(spamword);
    if (index != -1) {
        cout << table[index].Category;
    }
    else {
        cout << "not found";
    }
}
string HTable::getwordstring(string spamword) {
    int index = searchLinear(spamword);
    if (index != -1) {
        return  table[index].value;
    }
    else {
        cout << "not found";
    }
    return NULL;
}
void HTable::getword(string spamword) {
    int index = searchLinear(spamword);
    if (index != -1) {
        cout << table[index].value;
    }
    else {
        cout << "not found";
    }
}
string HTable::toLowerCase(const string& str) const
{
    string result = str;
    for (char& c : result) {
        c = tolower(c);
    }
    return result;
}


void moveFile(const string& source, const string& destination) {
    ifstream sourcefile(source);
    if (!sourcefile.is_open()) {
        cerr << "Error: Source file does not exist or cannot be opened." << std::endl;
        return;
    }
    sourcefile.close();
    if (rename(source.c_str(), destination.c_str()) != 0) {
        cerr << "Error while moving the file" << endl;
    }
    else {
        cout << "File Moved Successfully" << endl;
    }
}

void HTable::categoryofemail(string file_path) {
    int Adult = 0;
    int Financial = 0;
    int General = 0;
    int legal = 0;
    int lottery = 0;
    int personal = 0;
    int pharmaceuticals = 0;
    int privacy = 0;
    int weight = 0;
    int tech = 0;
    int urgency = 0;
    ifstream mailfile(file_path);
    string word;
    while (mailfile >> word)
    {
        string lowerd = toLowerCase(word);
        string pot = porterStemmer(lowerd);
        if (searchLinear(pot) != -1) {


            if (getcategoryinstring(pot) == "Adult Content spam")
            {
                Adult++;
            }
            else if (getcategoryinstring(pot) == "Financial spam") {
                Financial++;
            }
            else if (getcategoryinstring(pot) == "General Spam Terms") {
                General++;
            }
            else if (getcategoryinstring(pot) == "Legal and Compliance spam") {
                legal++;
            }
            else if (getcategoryinstring(pot) == "Lottery and Winning") {
                lottery++;
            }
            else if (getcategoryinstring(pot) == "Personal Enhancement") {
                personal++;
            }
            else if (getcategoryinstring(pot) == "Pharmaceuticals and Medicines") {
                pharmaceuticals++;
            }
            else if (getcategoryinstring(pot) == "Privacy and Security") {
                privacy++;
            }
            else if (getcategoryinstring(pot) == "Tech and Internet spam") {
                tech++;
            }
            else if (getcategoryinstring(pot) == "Urgency and Actio") {
                urgency++;
            }
            else if (getcategoryinstring(pot) == "Weight Loss and Fitness") {
                weight++;
            }

        }
    }   mailfile.close();

    int maxCount = 0;
    string maxCategory;

    if (Adult > maxCount) {
        maxCount = Adult;
        maxCategory = "Adult Content";
    }
    if (Financial > maxCount) {
        maxCount = Financial;
        maxCategory = "Financial";
    }
    if (General > maxCount) {
        maxCount = General;
        maxCategory = "General";
    }
    if (legal > maxCount) {
        maxCount = legal;
        maxCategory = "Legal";
    }
    if (lottery > maxCount) {
        maxCount = lottery;
        maxCategory = "Lottery";
    }
    if (personal > maxCount) {
        maxCount = personal;
        maxCategory = "Personal";
    }
    if (pharmaceuticals > maxCount) {
        maxCount = pharmaceuticals;
        maxCategory = "Pharmaceuticals";
    }
    if (privacy > maxCount) {
        maxCount = privacy;
        maxCategory = "Privacy";
    }
    if (tech > maxCount) {
        maxCount = tech;
        maxCategory = "Tech";
    }
    if (urgency > maxCount) {
        maxCount = urgency;
        maxCategory = "Urgency";
    }
    if (weight > maxCount) {
        maxCount = weight;
        maxCategory = "Weight";
    }

    if (maxCategory.empty()) {
        cout << "No category has a positive count." << endl;
    }
    else {
        cout << "The highest count is in " << maxCategory << " spam category." << endl;
    }

}
void HTable::checkAndCategorizeMail(string file_path) {
    // Extract the sender's address from the file name
    cout<<"im here";
    size_t lastSlash = file_path.find_last_of("/\\");
    size_t lastDot = file_path.find_last_of(".");
    string senderAddress = file_path.substr(lastSlash + 1, lastDot - lastSlash - 1);
    //string toedite = file_path.substr(0, lastSlash+1);
    string inbox = "C:/Users/MM/Downloads/c++ miu/emails/inbox/";
    string spamed = "C:/Users/MM/Downloads/c++ miu/emails/spam/";
    double wordcount = 0;
    double spamcount = 0;
    //size_t lastSlash_mov = file_path.find_last_of("\//");
    if (!checkmail(senderAddress)) {
        cout << "Mail from " << senderAddress << " is spam" << endl;
        ofstream outputFiles(spamed+"reason of spam "+senderAddress+".txt");
        string destinationPath = spamed + file_path.substr(lastSlash+1);
        outputFiles<<"The Email Address is Spamed";
        moveFile(file_path, destinationPath);
    }
    else {
        ofstream outputFile(spamed+"reason of spam "+senderAddress+".txt");
        cout << "Mail Adress " << senderAddress << " is safe" << endl;
        ifstream mailfile(file_path);
        string word;
        while (mailfile >> word)
        {
            string lowerd = toLowerCase(word);
            string pot = porterStemmer(lowerd);
            if (searchLinear(pot) != -1)
            {
                outputFile << "The word is ";
                outputFile<<getwordstring(pot);
                outputFile << "  ";
                outputFile << "The spam category is ";
                outputFile<<getcategoryinstring(pot);
                outputFile << endl;
                // Get the reason of spam and the file name
                wordcount++;
                spamcount++;
            }
        }
        mailfile.close();
        outputFile.close();
        if (spamcount / wordcount <= 0.25)
        {
            cout << "no spam"<<endl;
            //categoryofemail(file_path);
            string destinationPathf = inbox + file_path.substr(lastSlash + 1);
            moveFile(file_path, destinationPathf);
            return;
        }
        else if ((spamcount / wordcount > 0.25) && (spamcount / wordcount < 0.3))
        {
            cout << "spam level 1"<<endl;
        }
        else if ((spamcount / wordcount > 0.3 )&& (spamcount / wordcount < 0.5))
        {
            cout << "spam level 2"<<endl;
        }
        else if (spamcount / wordcount > 0.5) {
            cout << "totally spam"<<endl;
        }
        if (!checkmailspam(file_path)){
            categoryofemail(file_path);
            string destinationPath = inbox + file_path.substr(lastSlash + 1);
            moveFile(file_path, destinationPath);
        }
        else {
            categoryofemail(file_path);
            string destinationPath = spamed + file_path.substr(lastSlash + 1);
            moveFile(file_path, destinationPath);
        }
      }
}
bool HTable::checkmailspam(string file_path) {
    int counter = 0;
    ifstream mailfile(file_path);
    if (!mailfile.is_open()) {
        cerr << "Error opening file: " << file_path << endl;
        exit(1);
    }
    string word;
    while (mailfile >> word)
    {
        string lowerd = toLowerCase(word);
        string pot = porterStemmer(lowerd);
        if (searchLinear(pot) != -1) {
            counter++;
        }
    }
    mailfile.close();
    if (counter > 4) {
        return true;
    }
    return false;
}
string HTable::porterStemmer(string word) {
    // Step 1a
    if (endsWith(word, "sses")) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "ies")) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "ss")) {
        // do nothing, since the word is already stemmed
    }
    else if (endsWith(word, "s")) {
        word = word.substr(0, word.length() - 1);
    }


    if (endsWith(word, "eed")) {
        if (measure(word.substr(0, word.length() - 3)) > 0) {
            word = word.substr(0, word.length() - 1);
        }
    }
    else if ((endsWith(word, "ed") || endsWith(word, "ing")) && containsVowel(word)) {
        // Remove "ed" or "ing" if the word contains a vowel
        replaceSuffix(word, "ing", "");
        replaceSuffix(word, "ed", "");
    }

    if (endsWith(word, "y") && isConsonant(word[word.length() - 2])) {
        word = word.substr(0, word.length() - 1) + "i";
    }


    if (endsWith(word, "ational")) {
        replaceSuffix(word, "ational", "ate");
    }
    else if (endsWith(word, "tional")) {
        replaceSuffix(word, "tional", "tion");
    }
    else if (endsWith(word, "enci")) {
        replaceSuffix(word, "enci", "ence");
    }
    else if (endsWith(word, "anci")) {
        replaceSuffix(word, "anci", "ance");
    }
    else if (endsWith(word, "izer")) {
        replaceSuffix(word, "izer", "ize");
    }
    else if (endsWith(word, "abli")) {
        replaceSuffix(word, "abli", "able");
    }
    else if (endsWith(word, "alli")) {
        replaceSuffix(word, "alli", "al");
    }
    else if (endsWith(word, "entli")) {
        replaceSuffix(word, "entli", "ent");
    }
    else if (endsWith(word, "eli")) {
        replaceSuffix(word, "eli", "e");
    }
    else if (endsWith(word, "ousli")) {
        replaceSuffix(word, "ousli", "ous");
    }
    else if (endsWith(word, "ization")) {
        replaceSuffix(word, "ization", "ize");
    }
    else if (endsWith(word, "ation")) {
        replaceSuffix(word, "ation", "ate");
    }
    else if (endsWith(word, "ator")) {
        replaceSuffix(word, "ator", "ate");
    }
    else if (endsWith(word, "alism")) {
        replaceSuffix(word, "alism", "al");
    }
    else if (endsWith(word, "iveness")) {
        replaceSuffix(word, "iveness", "ive");
    }
    else if (endsWith(word, "fulness")) {
        replaceSuffix(word, "fulness", "ful");
    }
    else if (endsWith(word, "ousness")) {
        replaceSuffix(word, "ousness", "ous");
    }
    else if (endsWith(word, "aliti")) {
        replaceSuffix(word, "aliti", "al");
    }
    else if (endsWith(word, "iviti")) {
        replaceSuffix(word, "iviti", "ive");
    }
    else if (endsWith(word, "biliti")) {
        replaceSuffix(word, "biliti", "ble");
    }


    if (endsWith(word, "icate")) {
        replaceSuffix(word, "icate", "ic");
    }
    else if (endsWith(word, "ative")) {
        replaceSuffix(word, "ative", "");
    }
    else if (endsWith(word, "alize")) {
        replaceSuffix(word, "alize", "al");
    }
    else if (endsWith(word, "iciti")) {
        replaceSuffix(word, "iciti", "ic");
    }
    else if (endsWith(word, "ical")) {
        replaceSuffix(word, "ical", "ic");
    }
    else if (endsWith(word, "ful")) {
        replaceSuffix(word, "ful", "");
    }
    else if (endsWith(word, "ness")) {
        replaceSuffix(word, "ness", "");
    }


    if (endsWith(word, "al") && measure(word.substr(0, word.length() - 2)) > 1) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "ance") && measure(word.substr(0, word.length() - 4)) > 1) {
        word = word.substr(0, word.length() - 4);
    }
    else if (endsWith(word, "ence") && measure(word.substr(0, word.length() - 4)) > 1) {
        word = word.substr(0, word.length() - 4);
    }
    else if (endsWith(word, "er") && measure(word.substr(0, word.length() - 2)) > 1) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "ic") && measure(word.substr(0, word.length() - 2)) > 1) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "able") && measure(word.substr(0, word.length() - 4)) > 1) {
        word = word.substr(0, word.length() - 4);
    }
    else if (endsWith(word, "ible") && measure(word.substr(0, word.length() - 4)) > 1) {
        word = word.substr(0, word.length() - 4);
    }
    else if (endsWith(word, "ant") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if ((endsWith(word, "ement") || endsWith(word, "ment")) && measure(word.substr(0, word.length() - 4)) > 1) {
        word = word.substr(0, word.length() - 4);
    }
    else if (endsWith(word, "ent") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if ((endsWith(word, "ion") && word.length() >= 4 && (word[word.length() - 4] == 's' || word[word.length() - 4] == 't')) ||
             (endsWith(word, "ion") && word.length() >= 5 && word[word.length() - 5] == 's' && word[word.length() - 4] == 't')) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "ou") && measure(word.substr(0, word.length() - 2)) > 1) {
        word = word.substr(0, word.length() - 2);
    }
    else if (endsWith(word, "ism") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "ate") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "iti") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "ous") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "ive") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }
    else if (endsWith(word, "ize") && measure(word.substr(0, word.length() - 3)) > 1) {
        word = word.substr(0, word.length() - 3);
    }


    if (endsWith(word, "e") && measure(word.substr(0, word.length() - 1)) > 1) {
        word = word.substr(0, word.length() - 1);
    }
    else if (endsWith(word, "e") && measure(word.substr(0, word.length() - 1)) == 1 &&
             !endsWith(word.substr(0, word.length() - 1), "c")) {
        word = word.substr(0, word.length() - 1);
    }


    if (measure(word) > 1 && endsWith(word, "ll")) {
        word = word.substr(0, word.length() - 1);
    }

    return word;
}
bool HTable::isVowel(char c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
}

// Function to check if a character is a consonant
bool HTable::isConsonant(char c) {
    return (c >= 'a' && c <= 'z' && !isVowel(c));
}

// Function to check if a string ends with a given suffix
bool HTable::endsWith(const string& str, string suffix) {
    return str.size() >= suffix.size() && str.compare(str.size() - suffix.size(), suffix.size(), suffix) == 0;
}

// Function to measure the measure of a string
int HTable::measure(const string& str) {
    int count = 0;
    int length = str.length();

    for (int i = 0; i < length; ++i) {
        if (i > 0 && isVowel(str[i]) && isConsonant(str[i - 1])) {
            ++count;
        }
    }

    return count;
}

// Function to check if a string contains a vowel
bool HTable::containsVowel(string& str) {
    int length = str.length();

    for (int i = 0; i < length; ++i) {
        if (isVowel(str[i])) {
            return true;
        }
    }

    return false;
}

// Function to replace a suffix with a new suffix
void HTable::replaceSuffix(string& word, string oldSuffix, string newSuffix) {
    size_t pos = word.rfind(oldSuffix);
    if (pos != string::npos) {
        word.replace(pos, oldSuffix.length(), newSuffix);
    }
}
int HTable::searchLinear(string spamword) {
    int index = hash(spamword);
    int actualindex = index;
    while (table[index].Category != "") {
        if (table[index].value == spamword) {
            return index;
        }
        else {
            index = (index + 1) % TableSize;
            if (index == actualindex) {
                cerr << "The Spam Word Not Found" << endl;
            }
        }
    }
    return -1;
}
string HTable::searchLinearforcat(string spamword) {
    int index = hash(spamword);
    int actualindex = index;
    while (table[index].Category != "") {
        if (table[index].value == spamword) {
            return table[index].Category;
        }
        else {
            index = (index + 1) % TableSize;
            if (index == actualindex) {
                cerr << "The spam word not found" << endl;
                exit(1);
            }
        }
    }
    return"not found";
}

void HTable::display(ostream& out) {
    for (int i = 0; i < TableSize; ++i) {
        if (table[i].key != -1 && !table[i].Category.empty() && !table[i].value.empty()) {
            out << "Index: " << i
                << " Key: " << table[i].key
                << " Category: " << table[i].Category
                << " Value: " << table[i].value << endl;
        }
    }
}
ostream& operator<<(ostream& out, HTable& aHash) {
    aHash.display(out);
    return out;
}
