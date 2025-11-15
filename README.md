# text-game-oop
Object-oriented, extensible, text-based adventure game engine built in Java.
The engine loads a complete game world from a game.txt data file. This includes everything invoved in the story line including the rooms, items, puzzles, descriptions etc.
This would allow potential developers to personalise the game theme without having to modify any code.

Object-Oriented Architecture
The project follows a modular class hierarchy including:
Room, Exit, Feature, Item, Equipment, Container
Command system (move, look, get, etc.)
Parser + Tokeniser system
GameState loader
Combine system for crafting new items

The entire game world is loaded from a structured data file:
data/game.txt
This allows anyone to:
Change the theme
Add/remove rooms
Add puzzles, items, and interactions
Add new equipment
Create new exit connections
All without touching Java code.

General game structure:
The player interacts entirely through text commands.
The player uses move <exit> commands to move through the setting between rooms. The help command displays all the commands available to the player, found in the command file.
The game includes puzzles which involve using items/ equipment on features, these alter the game state, allowing the player to proceed.
The player can combine certain items to create new equipment to be used.
The player has a health bar, if it runs out the game is over.
There is a scoring system, so the player can attempt to complete the game with the max score possible.
There is a map which the player can view at ony point in the game, it only shows rooms/ exits which have already been explored.
Hidden rooms and exits are revealed when completing puzzles, they are then displayed on the map.


The included game features:
10 rooms (Enterance, Cafeteria, Kitchen, Restroom, Terrace, Bedroom, Closet, Stairwell, Basement, Dungeon)
Multiple items & equipment which can be used on features to complete puzzles
Poisonous areas where an antidote must be used within the next few moves or the game is over.
A similar dragon encounter.
Multiple items and tools needed to solve the puzzles and reach the final room.
When all rooms are explored the player needs to find a treasure chest and escape to win.

Building and Running:
You can compile and run the game using the following command:
./run.sh
If you get a permission error, make the script executable:
chmod +x run.sh
