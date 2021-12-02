#include <fstream>
#include <iostream>
#include <string>

struct WindowSlice {
  int value1{0};
  int value2{0};
  int value3{0};
};

int sumSlice(WindowSlice slice) {
  return slice.value1 + slice.value2 + slice.value3;
}

int main()
{
  int depths[2000];
  int depthIndex = 0;

  std::ifstream inf{ "input.txt" };
  if (!inf)
  {
      std::cerr << "Uh oh, input.txt could not be opened for reading!\n";
      return 1;
  }

  while (inf)
  {
      std::string line;
      std::getline(inf, line);

      if (line.empty()) {
        continue;
      }

      depths[depthIndex] = std::stoi(line);

      depthIndex += 1;
  }

  WindowSlice currentSlice;
  int ascendingIntervals = 0;
  int descendingCounter = 0;
  int noChangeCounter = 0;

  for (int i = 0; i < sizeof(depths)/sizeof(int) - 2; i++) {

    std::cout << "Size: " << sizeof(depths)/sizeof(int) - 2 << '\n';
    std::cout << "Iteration: " << i << '\n';

    if (currentSlice.value1 == 0) {
      std::cout << "First iteration" << '\n';
      currentSlice = { depths[i], depths[i+1], depths[i+2] };
    } else {
      WindowSlice newSlice = { depths[i], depths[i+1], depths[i+2] };

      if (sumSlice(currentSlice) < sumSlice(newSlice)) {
        std::cout << "Ascending" << '\n';
        ascendingIntervals += 1;
      } else if (sumSlice(currentSlice) > sumSlice(newSlice)) {
        std::cout << "Descending" << '\n';
        descendingCounter += 1;
      } else {
        std::cout << "No Change" << '\n';
        noChangeCounter += 1;
      }

      currentSlice = newSlice;

      std::cout << '\n';
    }
  }

  std::cout << "Ascending: " << ascendingIntervals << '\n';
  std::cout << "Descending: " << descendingCounter << '\n';
  std::cout << "No change: " << noChangeCounter << '\n';
  std::cout << "Sum: " << ascendingIntervals + descendingCounter + noChangeCounter << '\n';

  return 0;
}