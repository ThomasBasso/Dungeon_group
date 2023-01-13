package fil.coo.adventure.utils;

import fil.coo.adventure.places.Room;

public class DrawDongeon {
	private int currentRoom =0;
	private String[][] gameMatrix = new String[Constant.YROWS][Constant.XROWS];	// The matrix displayed in terminal
	private int[][] playerPosition = new int[3][2];
	private int[][] monstersPositions = new int[5][2];
	private int nbMonsters;
	private int addY = 0; // Add this value to every monsters or player coordinate
	private String donjon = "DUNGEON";

	/**
	 * @param room 			the room where the player is
	 * @param indexRoom		the remaining number of room in the donjon
	 */
	public DrawDongeon(Room room, int indexRoom) {
		addY = 25 *(indexRoom-1);

		nbMonsters = getNbMonsters(room);

		playerPosition[0][0] = 7;
		playerPosition[0][1] = 3;
		playerPosition[1][0] = 4;
		playerPosition[1][1] = 27;
		playerPosition[2][0] = 7;
		playerPosition[2][1] = 53;

		// max 5 monsters in a room
		monstersPositions[0][0] = 2;
		monstersPositions[0][1] = 20;
		monstersPositions[1][0] = 3;
		monstersPositions[1][1] = 19;
		monstersPositions[2][0] = 5;
		monstersPositions[2][1] = 18;
		monstersPositions[3][0] = 7;
		monstersPositions[3][1] = 17;
		monstersPositions[4][0] = 8;
		monstersPositions[4][1] = 17;

		// * for walls | / for door | " " for empty spot
		for (int i=0; i<gameMatrix.length; i++) {
			for (int j=0; j<gameMatrix[0].length; j++) {
				if (i==0 || i==9 || j==0 || j==74 || (j==25 && i!=3) || (j==50 && i!=8)) {
					gameMatrix[i][j] = "*";
				} else if ((j==25 && i==3) || (j==50 && i==8)) {
					gameMatrix[i][j] = "/";
				} else if (i==10 && (j<34 || j>40)) {
					gameMatrix[i][j] = "*";
				} else {
					gameMatrix[i][j] = " ";
				}
			}
		}

		// P for player | M for monster
		gameMatrix[playerPosition[currentRoom][0]][playerPosition[currentRoom][1]+addY] = "P";
		for (int i = 0; i<nbMonsters; i++) {
			gameMatrix[monstersPositions[i][0]][monstersPositions[i][1]+addY] = "M";
		}
		String[] d = donjon.split("");
		for (int i = 0; i < 7; i++) {
			gameMatrix[10][34+i] = d[i];
		}
	}

	/**
	 * Print the matrix
	 */
	public void displayMap() {
		for (int i=0; i<gameMatrix.length; i++) {
			for (int j=0; j<gameMatrix[0].length; j++) {
				if (gameMatrix[i][j]!=null) {
					System.out.print(gameMatrix[i][j]);
				}
			}
			System.out.println();
		}
	}


	/**
	 * Get the remaining number of room in the donjon
	 * @param room
	 */
	public void roomChanged(Room room) {
		currentRoom = room.getNbRoomMax() - room.getNeighbour().size();
		nbMonsters = getNbMonsters(room);
		addY+=25;
		for (int i=0; i<gameMatrix.length; i++) {
			for (int j=0; j<gameMatrix[0].length; j++) {
				if (gameMatrix[i][j] == "P" || gameMatrix[i][j] == "M") {
					gameMatrix[i][j] = " ";
					gameMatrix[playerPosition[currentRoom][0]][playerPosition[currentRoom][1]+addY] = "P";
				}
			}
		}
		for (int i = 0; i<nbMonsters; i++) {
			gameMatrix[monstersPositions[i][0]][monstersPositions[i][1]+addY] = "M";
		}
	}

	/**
	 * @param room
	 * @return the number of remaining monsters in the current room
	 */
	public int getNbMonsters(Room room) {
		return room.getMonsters().size();
	}

	/**
	 * 	Delete one monster from the matrix
	 */
	public void updateMap() {
		Boolean removedOne = false;

		for (int i=0; i<gameMatrix.length; i++) {
			for (int j=0; j<gameMatrix[0].length; j++) {
				if (gameMatrix[i][j]=="M" && !removedOne) {
					gameMatrix[i][j]=" ";
					removedOne = true;
				}
			}
		}
	}
}
