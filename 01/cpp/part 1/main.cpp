#include <fstream>
#include <iostream>
#include <string>

int main()
{
    std::ifstream inf{ "input.txt" };
    if (!inf)
    {
        std::cerr << "Uh oh, input.txt could not be opened for reading!\n";
        return 1;
    }

    int currentDepth = 0;

    int ascendingIntervals = 0;

    while (inf)
    {
        std::string line;
        std::getline(inf, line);

        if (line.empty()) {
          continue;
        }

        //std::cout << "line" << line << '\n';

        if (currentDepth == 0) {
          currentDepth = std::stoi(line);
        } else {
          int newDepth = std::stoi(line);
          if (currentDepth < newDepth) {
            ascendingIntervals += 1;
            //std::cout << "Ascending depths: " << ascendingIntervals << '\n';
          }
          currentDepth = newDepth;
        }
    }

    std::cout << ascendingIntervals << '\n';

    return 0;
}