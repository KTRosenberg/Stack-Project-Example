# ArrayStack and HistoryStack Example

- This sample project was built live to showcase strategies for iteratively implementing and testing an example of a fundamental data structure: an array-based stack with automatic resizing
- We applied this stack in a Processing sketch project for basic colored-shape drawing, by using it for an "undo operation" history stack. (See draw_example) 

### Open the Project
- importing using Eclipse's `import > existing project` menu

### Run/Build the Drawing Example
- This example mini project is a Processing graphics 
- install Processing from https://processing.org

##### To run (but not be able to edit)
  - MacOS, enter a terminal window in the directory containing the .jar file. start the run file with ./run
  - Windows (TODO)

##### To build and run
  - Processing no longer works with the latest JDK. The solution is to use the JDK bundled inside the Processing application.
  - Open the draw_example project by importing using Eclipse's `import > existing project` menu
  - Right click the project folder in eclipse, select run as > run configurations
  - Go to the JRE tab, making sure the ChalkboardTime project is selected in the "Java Application" sub-menu on the left list
  - Select "alternate JRE" and click the installed JREs button.
  - Click the "add" button
  - Select this directory in the finder menu: /Applications/Processing.app/Contents/PlugIns/jdk1.8.0_202.jdk/Contents/Home
  - Apply all/confirm
  - In the alternate JRE dropdown, select the new "Processing" JRE
  - Apply all/confirm
  - Now the play button should be able to run the application with the Processing JRE.
