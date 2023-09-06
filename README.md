This project is a desktop application simulating a virus spread among an animal population. 

It is written in Java and I used Java Swing for the GUI. 

There are four slide bars for the user to determine four important simulation parameters, namely the total number of animals, the number of initial infections, the virus infectiousness and the mortality rate. The following image shows a snapshot of the graphical interface:   

![Bildschirmfoto vom 2023-09-06 08-14-34](https://github.com/PeterNiederwieser/SimulationVirusSpreadDesktopApplication/assets/112017284/3079528c-cc06-49e3-8cd2-d8e0edf76008)

On the upper left side there is the map showing the region, where the animals live, and each animal is displayed as a coloured circle. The color indicates the health or infection status of the respective animal (light blue ... healthy, yellow ... recovered and immune, red ... infected, black ... severely ill). 

There are two charts on the upper right side displaying important data of the virus spread. If the simulation is running, these charts get updated regularly within short time intervals. 

Throughout many parts of the code, I placed emphasis on certain design principles, such as the SOLID principles or SLAP (Single Level of Abstraction), as well as Clean Code rules. I am aware that there is still much room for improvement, and therefore, I am very excited to seize the opportunity to actually get better with each new project!


