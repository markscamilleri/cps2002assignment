import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Denise Buttigieg, Mark Said Camilleri
 * @version 13/04/2017.
 */

public class Game {
    static Map map;
    private static Scanner scan = new Scanner(System.in);
    
    
    //This class can't be instantiated
    private Game() {
    }

    public static void main(String[] args) {
        Player playerList[];
        Team teamList[];
        
        scan.useDelimiter("\n");
        
        final int numOfPlayers = getNumberOfPlayers();
        final int numOfTeams = getNumberOfTeams(numOfPlayers);
        
        final int mapSize = getMapSize();
        map = getMap(mapSize);
        
        teamList = createTeamList(numOfTeams, mapSize);
        playerList = createPlayerList(numOfPlayers, mapSize, teamList);
        
        boolean gameOver = false;
        
        //generate HTML Game Files for each Player
        generateHTMLFiles(teamList, playerList);
        
        while (!gameOver) {
            // Loop till winning
            for (int i = 0; i < playerList.length; i++) {
                Player player = playerList[i];
                Position previous = player.position;
                
                // Prompt user for input and check it.
                getNextMove(player, i);
                
                Position newPos = player.position;
                if (player.setPosition(newPos, map)) {
                    player.position = newPos;
                } else {
                    player.position = previous;
                }
                
                if (getLandingTile(player, map) == 't') {
                    gameOver = true;
                    System.out.println("Player " + (i + 1) + " wins!");
                }
            }
            
            //generate HTML Game Files for each Player
            generateHTMLFiles(teamList, playerList);
        }
    }
    
    /**
     * Asks the user for the number of players
     *
     * @return the number of players
     */
    public static int getNumberOfPlayers() {
        int input;
        do {
            System.out.println("Enter the number of players:");
            input = scan.nextInt();
        } while (!setNumPlayers(input));
        
        return input;
    }
    
    /**
     * Asks the user whether this is a collaborative game or not
     * and if yes, the number of teams
     *
     * @return the number of teams, this is equal to the number of players if it's a solo game
     */
    public static int getNumberOfTeams(int noOfPlayers) {
        int gameType = 0;
        
        do {
            System.out.println("Enter 1 for a solo game, 2 for a collaborative game: ");
            gameType = scan.nextInt();
        } while (gameType < 1 || gameType > 2);
        
        if (gameType == 1) {
            return noOfPlayers;
        } else {
            int input;
            
            do {
                System.out.println("Enter the number of teams (1 - " + (noOfPlayers - 1) + ") :");
                input = scan.nextInt();
            } while (input < 1 || input >= noOfPlayers);
            
            return input;
        }
    }
    
    /**
     * Prompts the user for the map size and returns it
     *
     * @return the map size given by the user. Gurantees a legal amount
     */
    public static int getMapSize() {
        int mapSize;
        do {
            System.out.println("Enter map size: ");
            mapSize = scan.nextInt();
        } while (!Map.checkMapSize(mapSize));
        
        return mapSize;
    }
    
    /**
     * Prompts the user for the map type and returns the generated map
     *
     * @param mapSize the map size
     * @return the generated map.
     */
    public static Map getMap(final int mapSize) {
        int mapType = -99;
        do {
            System.out.println("Enter the number corresponding to the map type");
            System.out.println("you want to use: \n");
            System.out.println("1 Safe Map");
            System.out.println("2 Hazardous Map");
            
            mapType = scan.nextInt();
        } while (mapType < 1 || mapType > 2);
        
        if (mapType == 1) {
            return new SafeMap(mapSize, mapSize);
        } else { // mapType is guaranteed to be a 1 or a 2.
            return new HazardousMap(mapSize, mapSize);
        }
    }

    /**
     * Creates the list of teams
     *
     * @param numOfTeams the number of teams required
     * @param mapSize    the preferred map size
     * @return a list of teams
     */
    public static Team[] createTeamList(int numOfTeams, int mapSize) {
        Team[] teams = new Team[numOfTeams];
        
        for (int i = 0; i < teams.length; i++) {
            teams[i] = new Team(mapSize);
        }
        
        return teams;
    }
    
    /**
     * Creates the player lists and randomly assigns every player a team.
     *
     * @param numOfPlayers the number of players that will be playing
     * @param mapSize      the size of the map that is used
     * @param teams        the teams to put the players in
     * @return the list of players
     */
    public static Player[] createPlayerList(final int numOfPlayers, final int mapSize, final Team[] teams) {
        Player[] players = new Player[numOfPlayers];
        
        int[] numOfPlayersPerTeam = new int[teams.length];
        
        for (int i = 0; i < numOfPlayersPerTeam.length; i++) {
            numOfPlayersPerTeam[i] = 0;
        }
        
        Random rand = new Random();
        
        for (int i = 0; i < numOfPlayers; i++) {
            int teamNo = rand.nextInt(teams.length);
            
            if (numOfPlayersPerTeam[teamNo] >= Math.round(numOfPlayers * 1.0 / teams.length)) {
                --i;
            } else {
                Player p = new Player(mapSize, teams[teamNo]);
                numOfPlayersPerTeam[teamNo]++;
                players[i] = p;
            }
        }
        
        for (int i = 0; i < teams.length; i++) {
            System.out.println("Team " + (i + 1) + " consists of:");
            System.out.println("---------------------------------");
            
            
            for (int j = 0; j < players.length; j++) {
                if (players[j].getTeam() == teams[i]) {
                    System.out.println("Player " + (j + 1));
                }
            }
            
            System.out.println();
        }
        
        return players;
    }
    
    /**
     * Generates Game HTML Files for each player
     */
    public static void generateHTMLFiles(Team[] teams, Player[] players) {
        //Deleting all previously created game files
        final String FOLDER = "gamefiles";
        File dir = new File(FOLDER);
        dir.mkdir();
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
        
        
        if (teams.length == players.length) {
            //Creating new game files according to the new game
            for (int playerIndex = 1; playerIndex <= players.length; playerIndex++) {
                generateHTMLFile(players[playerIndex - 1], playerIndex, FOLDER);
            }
        } else {
            for (int teamIndex = 1; teamIndex <= teams.length; teamIndex++) {
                generateHTMLFile(teams[teamIndex - 1], players, teamIndex, FOLDER);
            }
        }
    }

    /**
     * Validates the next move for the player currently in play
     *
     * @param player        the player currently playing
     * @param playerIndex   the index of the player currently playing
     */
    private static void getNextMove(Player player, int playerIndex) {
        char move;
        boolean moveIsValid = true;
        
        do {
            System.out.println("Player " + (playerIndex + 1) + "'s turn.");
            System.out.println("Enter your move.");
            System.out.println("U to move UP");
            System.out.println("D to move DOWN");
            System.out.println("L to move LEFT");
            System.out.println("R to move Right");
            String input = scan.next();
            
            if (!input.trim().isEmpty()) {
                move = Character.toLowerCase(input.charAt(0));
            } else {
                move = ' '; // Makes the input loop
            }
            moveIsValid = player.move(move);
            System.out.print("\n");
        }
        while (!moveIsValid);
    }
    
    /**
     * Gets the tile type the user landed on
     *
     * @param player the player who's playing
     * @param map    the map used
     * @return the tile type
     */
    protected static char getLandingTile(Player player, Map map) {
        String action = "stepped on";
        if (!player.getTeam().isMapTileKnown(player.position.x, player.position.y)) {
            player.getTeam().discoverTile(player.position.x, player.position.y);
            action = "found";
        }
        
        char type = map.getTileType(player.position.x, player.position.y);
        if (type == 't') {
            System.out.println("Congratulations, you have found the treasure");
        } else if (type == 'w') {
            System.out.println("OOPS, you " + action + " on a water tile. You died!");
            System.out.println("You have respawned in your start position");
            player.position = player.getStartPosition();
        } else if (type == 'g') {
            System.out.println("You " + action + " a Grass tile! Wait for your turn.");
        }
        
        return type;
    }
    
    /**
     * Checks that the number of playerList is valid
     *
     * @param n the number of playerList
     * @return true if the number of playerList is between 2 and 8, false otherwise
     */
    public static boolean setNumPlayers(int n) {
        return !(n < 2 || n > 8);
    }

    /**
     * Generates HTML game files for solo play
     *
     * @param player        the player requiring the game files
     * @param playerIndex   index of the player
     * @param directory     the directory of where to store the game files
     */
    private static void generateHTMLFile(Player player, int playerIndex, final String directory) {
        String filename = "map_player_" + playerIndex + ".html";
        String pathToFile = directory + "/" + filename;
        
        
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile, true));
            bufferedWriter.write("<!DOCTYPE html>\n");
            bufferedWriter.write("<html>\n<body>\n");
            bufferedWriter.write("<h1>Player Map for player " + playerIndex + "</h1>\n<div>\n<table>");
            
            for (int y = map.getMapSize()[0] - 1; y >= 0; y--) {
                bufferedWriter.write("<tr>");
                for (int x = 0; x < map.getMapSize()[0]; x++) {
                    String colour;
                    if (player.getTeam().isMapTileKnown(x, y)) {
                        colour = getColour(map.getTileType(x, y));
                    } else {
                        colour = "grey";
                    }
                    
                    String style = "style=\"width: 2em; height: 2em; text-align: center; font-size: 2em; background-color: " + colour + ";\"";
                    bufferedWriter.write("<td " + style + ">");
                    if (player.position.y == y && player.position.x == x)
                        bufferedWriter.write("&bull;");
                    bufferedWriter.write("</td>");
                }
                bufferedWriter.write("</tr>\n");
            }
            
            bufferedWriter.write("</table>\n</div>\n</body>\n</html>");
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }

    /**
     * Generates HTML game files for team mode
     *
     * @param team          the team requiring the game files
     * @param playerList    the list of players in the team
     * @param teamIndex     the index of the team
     * @param directory     the directory of where to store the game files
     */
    private static void generateHTMLFile(Team team, Player[] playerList, int teamIndex, final String directory) {
        String filename = "map_team_" + teamIndex + ".html";
        String pathToFile = directory + "/" + filename;
        
        
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile, true));
            bufferedWriter.write("<!DOCTYPE html>\n");
            bufferedWriter.write("<html>\n<body>\n");
            bufferedWriter.write("<h1>Team Map for team " + teamIndex + "</h1>\n<div>\n<table>");
            
            for (int y = map.getMapSize()[0] - 1; y >= 0; y--) {
                bufferedWriter.write("<tr>");
                for (int x = 0; x < map.getMapSize()[0]; x++) {
                    String colour;
                    if (team.isMapTileKnown(x, y)) {
                        colour = getColour(map.getTileType(x, y));
                    } else {
                        colour = "grey";
                    }
                    
                    String style = "style=\"width: 2em; height: 2em; text-align: center; font-size: 2em; background-color: " + colour + ";\"";
                    bufferedWriter.write("<td " + style + ">");
                    
                    for (int i = 0; i < playerList.length; i++) {
                        if (playerList[i].getTeam() == team) {
                            if (playerList[i].position.y == y && playerList[i].position.x == x)
                                bufferedWriter.write((i + 1) + " ");
                        }
                    }
                    bufferedWriter.write("</td>");
                }
                bufferedWriter.write("</tr>\n");
            }
            
            bufferedWriter.write("</table>\n</div>\n</body>\n</html>");
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }
    
    /**
     * Returns the HTML Colour value as a string
     *
     * @param tileType The type of tile uncovered
     * @return green, blue, gold or (but should never return) grey.
     */
    static String getColour(char tileType) {
        switch (tileType) {
            case 'g':
                return "green";
            case 'w':
                return "blue";
            case 't':
                return "gold";
            default: //This should never be used.
                return "grey";
        }
    }
}
