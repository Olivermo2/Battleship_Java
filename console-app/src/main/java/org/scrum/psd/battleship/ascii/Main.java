package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import org.scrum.psd.battleship.board.BoardStatus;
import org.scrum.psd.battleship.board.GameBoard;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Ship> myFleet;
    private static List<Ship> enemyFleet;
    private static ColoredPrinter console;
    
    private static GameBoard own;
    private static GameBoard enemy;

    public static void main(String[] args) {
    	own = new GameBoard();
    	enemy = new GameBoard();

        console = new ColoredPrinter.Builder(1, false).build();

        console.setForegroundColor(Ansi.FColor.MAGENTA);
        console.println("                                     |__");
        console.println("                                     |\\/");
        console.println("                                     ---");
        console.println("                                     / | [");
        console.println("                              !      | |||");
        console.println("                            _/|     _/|-++'");
        console.println("                        +  +--|    |--|--|_ |-");
        console.println("                     { /|__|  |/\\__|  |--- |||__/");
        console.println("                    +---------------___[}-_===_.'____                 /\\");
        console.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        console.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        console.println("|                        We1come to Battleship       v2.1              BB-61/");
        console.println(" \\_________________________________________________________________________|");
        console.println("");
        console.clear();

        InitializeGame();

        StartGame();
    }

    private static void StartGame() {
        Scanner scanner = new Scanner(System.in);

		printCanon();

        do {
			
			play(own, enemyFleet, "Player", true);
			play(enemy, myFleet, "Computer", false);
			
        } while (true);
    }

    private static void beep() {
        console.print("\007");
    }

    protected static Position parsePosition(String input) {
        Letter letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
		
        int number = Integer.parseInt(input.substring(1));
        return new Position(letter, number);
    }

    private static Position getRandomPosition() {
        int rows = 7;
        int lines = 7;
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(lines)+1];
        int number = random.nextInt(rows)+1;
        Position position = new Position(letter, number);
        return position;
    }

    private static void InitializeGame() {
        InitializeMyFleet();

        InitializeEnemyFleet();
    }

    private static void InitializeMyFleet() {
        Scanner scanner = new Scanner(System.in);
        myFleet = GameController.initializeShips();
        
        own.print();

        console.println("Please position your fleet (Game board has size from A to H and 1 to 8) :");

        for (Ship ship : myFleet) {
			
            console.println("");
            console.println(String.format("Please enter the positions for the %s (size: %s)", ship.getName(), ship.getSize()));
            for (int i = 1; i <= ship.getSize(); i++) {
            	
				inputShipPosition(ship, i, scanner);
				
            }
        }
    }

	private static boolean isOverlapped(String positionInput){
		boolean overlapped = false;
		for (Ship shipA : myFleet){
			if(shipA.isPlaced()) {
				List<Position> positions = shipA.getPositions();
				
				for(int i =0; i<positions.size();i++){
					if (String.valueOf(positions.get(i).getColumn()).equals(String.valueOf(positionInput.charAt(0)))
						&& String.valueOf(positions.get(i).getRow()).equals(String.valueOf(positionInput.charAt(1)))) {
							overlapped = true;
						break;
					}
				}
			}
			
			if (overlapped){
				break;
			}
		}
		
		return overlapped;
	}
    private static void InitializeEnemyFleet() {
        enemyFleet = GameController.initializeShips();
        
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 5));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 6));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 7));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 8));
        
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 6));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 7));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 8));
        enemyFleet.get(1).getPositions().add(new Position(Letter.E, 9));
        
        enemyFleet.get(2).getPositions().add(new Position(Letter.A, 3));
        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 3));
        enemyFleet.get(2).getPositions().add(new Position(Letter.C, 3));
        
        enemyFleet.get(3).getPositions().add(new Position(Letter.F, 8));
        enemyFleet.get(3).getPositions().add(new Position(Letter.G, 8));
        enemyFleet.get(3).getPositions().add(new Position(Letter.H, 8));
        
        enemyFleet.get(4).getPositions().add(new Position(Letter.C, 5));
        enemyFleet.get(4).getPositions().add(new Position(Letter.C, 6));
    }
	
	private static void printCanon() {
		console.println("");
        console.print("\033[2J\033[;H");
        console.println("                  __");
        console.println("                 /  \\");
        console.println("           .-.  |    |");
        console.println("   *    _.-'  \\  \\__/");
        console.println("    \\.-'       \\");
        console.println("   /          _/");
        console.println("  |      _  /\" \"");
        console.println("  |     /_\'");
        console.println("   \\    \\_/");
        console.println("    \" \"\" \"\" \"\" \"");
	}
	
	private static void printHit() {
		console.println("                \\         .  ./");
		console.println("              \\      .:\" \";'.:..\" \"   /");
		console.println("                  (M^^.^~~:.'\" \").");
		console.println("            -   (/  .    . . \\ \\)  -");
		console.println("               ((| :. ~ ^  :. .|))");
		console.println("            -   (\\- |  \\ /  |  /)  -");
		console.println("                 -\\  \\     /  /-");
		console.println("                   \\  \\   /  /");
	}
	
	private static void inputShipPosition(Ship ship, int shipPart, Scanner scanner) {
		boolean isWrongPosition = true;
		
		while (isWrongPosition) {
			own.print();

			console.println(String.format("Enter position %s of %s (i.e A3):", shipPart, ship.getSize()));

			String positionInput = scanner.next();
			
			if(positionInput.equals("exit")){
				System.exit(0);
			}
			
			isWrongPosition = !Character.isLetter(positionInput.charAt(0)) ||
								!Character.isDigit(positionInput.charAt(1)) ||
								Integer.valueOf(positionInput.charAt(1)) - 49 < 0 ||
								Integer.valueOf(positionInput.charAt(1)) - 49 > 7;
			if (isOverlapped(positionInput)){
				console.println("Overlapped Positions!!");
				
			} else if (isWrongPosition) {
				console.println(String.format("Wrong Input, Please enter the positions for the %s (size: %s)", ship.getName(), ship.getSize()));
			}
			else {
				own.set(positionInput.charAt(0) - 65, Integer.valueOf(positionInput.charAt(1)) - 49, BoardStatus.OCCUPIED);
				ship.addPosition(positionInput);
				ship.setPlaced(true);
				break;
			}
		}
	}
	
	private static void play(GameBoard board, List<Ship> fleet, String entityName, boolean isPlayer) {
		boolean isShootPositionValid = false;
		Position position;
		boolean isHit = false;
		Scanner scanner = new Scanner(System.in);
		
		while (!isShootPositionValid || isHit)
		{
			console.println("");
			
			if (isPlayer) {
				console.println(String.format("%s, it's your turn", entityName));
				console.println("Enter coordinates for your shot :");
				position = parsePosition(scanner.next());
			}
			else {
				position = getRandomPosition();
			}

			isShootPositionValid = GameController.isShootPositionValid(board, position);
		
			if (isShootPositionValid)
			{
				isHit = GameController.checkIsHit(fleet, position);
				console.println("");
				console.println(String.format("%s shoot in %s%s and %s", entityName, position.getColumn(), position.getRow(), isHit ? "hit your ship !" : "miss"));
				if (isHit) {
					board.setBoard2(position.getRow(), position.getColumn().ordinal(), BoardStatus.DESTROYED);
					beep();

					printHit();
				}
				else {
					board.setBoard2(position.getRow(), position.getColumn().ordinal(), BoardStatus.WATER);
				}

				console.println(isHit ? "Yeah ! Nice hit !" : "Miss");
			}
			else
			{
				console.println(String.format("%s shoot in %s%s and %s", entityName, position.getColumn(), position.getRow(), "Invalid position.  Please enter again."));
			}
		}
	}
}
