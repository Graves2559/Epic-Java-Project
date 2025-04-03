Description

Our project idea is to make a timed escape room. Inspired by real escape rooms, the user will have a set amount of time to solve the puzzles and escape the room(s) to win the game. The escape room is planned to have an overarching theme so that the experience feels cohesive. In addition, we want to add audio elements to make the user’s experience more interesting. For graphics and user experience, we are thinking of creating it with 2D graphics and text boxes. Our idea is to have it in a point-and-click style game with accessible controls so that anybody can play it. Some of these points may be subject to change, however.

Exemplary Use Case
Steps:
User: start software
User: click start button
System: take user to escape room
User: interact with room(s)/items/buttons/etc.
System: as user figures puzzles out, use game logic to “solve” them
User: escape the room and beat the game
User: click quit
System: close window(s)

Minimum Viable Product / Project Enhancements
Minimum Viable Product (MVP): GUI, basic graphics, puzzle components, timer, start menu, game over screen

Optional Enhancements: music, sound effects, more advanced graphics (better art), more advanced puzzles

Design

Our software is intended to currently have 5 different classes to build off of. The ideas we came up with for our classes should provide us with enough flexibility to create what we want to create. However, we are aware that more classes may need to be created. A brief overview of our classes will be provided in this paragraph. Our main class, “Game”, will handle the start of our program and every other class will branch from this class because they are dependent on “Game”. The “Timer” class will handle, as its name suggests, the timer for the game. “Puzzle” will be the main connection between the individual puzzles and our “Game” class. As the player solves our puzzles and progresses, “Puzzle” will keep track of that and act accordingly. Our class of “Rooms” will be in charge of setting up the spaces for the player to interact in. Our GUI will most likely be taken care of in either our “Game” class or our “Rooms” class. And finally, our “Interactable” class will be arguably the most important. Every interaction that the player can make will stem from this class. Objects will inherit from “Interactable” but will be individually modified to meet the criteria of the scenario. All of the classes listed above will play a crucial role in our software, and as mentioned above, more classes may need to be created to aid in the development of our software.


Below are some design ideas for the required technical items in our project

GUI: for our GUI, using JFrame/Swing is going to be our best and easiest option. Our GUI will house our basic graphical elements and be the “window” with which the user interacts with the game

Basic Graphics: the way we intend to implement basic graphics into our game is by using ImageIcon/Swing. In order to create our images, we are going to either simply use Microsoft Paint, or an application called Aseprite. Both of those applications do well with basic 2D graphic creation, so we believe that they would work effectively with our game

Puzzle Components: the idea of puzzle components can be a bit more abstract than the other critical elements of our game. Just like with our graphics, using ImageIcon/Swing will work well here. The majority of the puzzle components will either stem from our “Interactable” class or simply from visual cues within the graphics. This is one element that is still a work in progress.

Timer: the timer will most likely also be rendered with ImageIcon/Swing, but we are unsure what, if any, package we will use for the actual timing part of the timer. Some research has been done into it, but we have not settled on a package yet. This element is also a work in progress.

Start Menu: our start menu will depend on our JFrame/Swing package as well as our ImageIcon/Swing package. After the user opens the .exe which starts the program, a window will open containing our start menu. The menu will have some simple graphical elements as well as a button or two to start the game.

Game Over Screen: our game over screen will be the simplest element out of them all. It will be rendered exactly the same as our start menu screen but with even more rudimentary graphical elements. The only way to reach the game over screen is by running out of time on the game timer. We are under the assumption that our project showcase in class will not be long, so our game over screen will most likely not even come into play. 










Below are some of the elements that are going to go into our design

i. Domain Model: 
		
		ii. Graphical User Interface: our GUI, as stated previously, will be reliant on JFrame/Swing, and some other things that fall into that family. The first screen being shown in the GUI will be the start screen. All other screens will branch from this start screen.

		iii. Text Formatting and Processing: text formatting will be done using certain packages that we select in the IDE. More specific aspects of text formatting and the like are undecided as of now.

		iv. Media: we are planning to use free online software to generate/find the sounds that are going to go into our game. As for graphics, we are planning on creating them all ourselves.

		v. Storage and Retrieval of Information: a couple of our classes are going to be responsible for data storage and retrieval while the player is in the game. Given that is a timed escape room, there are no plans currently to introduce save states for the player to come back to if they decide to quit before finishing.


Below are some examples of our Javadoc skeletons/outlines which we will be using (subject to change)

Game Class:
public class Game {
    
	// add variables here to keep track of progress
    
	// maybe add method here for sounds and stuff ?
    
	public void LoadRooms()
	{
    	// responsible for loading Rooms class
	}
    
	public void LoadPuzzle()
	{
    	// responsible for loading Puzzle class
	}
    
	public void LoadTimer()
	{
    	// responsible for loading Timer class
	}
    
	// MAIN FUNCTION
	public static void main(String[] args)
	{
    	// LoadRooms()
    	// LoadPuzzle()
    	// LoadTimer()
	}  
}

Rooms Class:
public class Rooms {
    
	public int LoadRoom1()
	{
    	// loads room 1
    	// loads interactables for room
   	 
    	return 0; // return 0 if loaded successfully
	}
    
	public int LoadRoom2()
	{
    	// loads room 2
    	// loads interactables for room
   	 
    	return 0; // return 0 if loaded successfully
	}
    
	public int LoadRoom3()
	{
    	// loads room 3
    	// loads interactables for room
   	 
    	return 0; // return 0 if loaded successfully
	}
    
	public int LoadRoom4()
	{
    	// loads room 4
    	// loads interactables for room
   	 
    	return 0; // return 0 if loaded successfully
	}
    
	public int LoadRoom5()
	{
    	// loads room 5
    	// loads interactables for room
   	 
    	return 0; // return 0 if loaded successfully
	}
    
	// add method for scenario where loading room a fails
}


Timer Class:
public class Timer {
    
	// variables to determine amount of time, font, etc.
    
	// variable to determine if timer hits 0
    
	private void TimerStart	()
	{
    	// start timer here
	}
    
	// may need more here later idk
}

Puzzle Class:
public class Puzzle {
    
	// add variables to check for puzzle completions
    
	public void UpdateGameProgress()
	{
    	// updates progress variables in Game class
	}
    
	public int UpdatePuzzle1Progress()
	{
    	// updates puzzle 1 progress and returns 0 if nothing
   	 
    	return 0;
	}
    
	public int UpdatePuzzle2Progress()
	{
    	// updates puzzle 2 progress and returns value
   	 
    	return 0;
	}
    
	public int UpdatePuzzle3Progress()
	{
    	// updates puzzle 3 progress and returns value
   	 
    	return 0;
	}
}
 
	Interactable Class:
public class Interactable {
    
	// MOST WIP CLASS //
    
	// add vars to determine if clicked or not to prevent from glitching out
    
	public void LoadHitbox()
	{
    	// load the actual clickable section that overlays on the screen
	}
    	 
	public void WhenClicked()
	{
    	// do something when clicked
	}
    
	// most likely need to add more later
}












Implementation

The work-in-progress plan we have to implement the design above is to implement the elements in their most basic pieces, and then add onto the pieces to expand on our concepts. For example, a 4-digit code puzzle will most likely be implemented with just 1 digit at first to make sure it works, and because of how we are going to set our framework up, adding the next 3 digits will be easy (ideally). The implementation will be done in so-called “layers”.

We do not currently know if we are going to need to/want to use any third-party libraries, but we are not against the idea. Once we start implementing our ideas into code, we can evaluate if we need to utilize any third-party libraries to help us finish our project.

Some problems with implementation may arise - an important one is getting our room transitions working properly. Our current plan is to have 5 rooms, and each of those rooms will contain different “objects” (a key, a note, a painting, etc.). Each individual object will need to be rendered according to which room is currently being displayed to prevent graphical errors and/or progression errors. The room transitions will be important because they will load and de-load objects as needed. We suspect that after the initial challenge of getting them working is solved, it will be pretty simple to adjust them as needed for each room. The only other real issue we have with implementation currently is deciding the puzzle progression. This is more of a conceptual issue currently, but it does trickle down to an implementation issue.

Accounting of the Time Budget

We have estimated that we will have to complete around 100 hours to finalize this project, the four sections being categorized as programming, art, conceptual design, and testing. The bulk of our time will be spent on programming, comprising around 50% of our allocated time. Art is still in development, we have a few prototype designs but it’s still too early to say how accurate our estimation for time is. Our conceptual design is mostly finished and has solidified most of our main themes and puzzles. Our time budget accurately reflects our current progress on the project and we have remained true to our time budget. 


Problems and/or Issues

Some of the potential issues that the team may face include, remaining consistent with deadlines, setting up a rough schedule to complete our milestones, and ironing out the final details to begin implementing the components of our project. However, we plan to address these issues by maintaining regular communication and weekly meetings, which will create an open channel to hold one another accountable for deadlines. 
