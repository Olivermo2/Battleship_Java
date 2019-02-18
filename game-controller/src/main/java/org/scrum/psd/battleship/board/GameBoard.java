package org.scrum.psd.battleship.board;

import java.util.ArrayList;

public class GameBoard {
	public int column = 8;
	public int row = 8;

	public BoardStatus [][] board = new BoardStatus [column][row];
	public BoardStatus [][] board2 = new BoardStatus [column][row];
	
	public GameBoard() {
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < column; ++j) {
				board[i][j] = BoardStatus.WATER;
				board2[i][j] = BoardStatus.HIDDEN;
			}
		}
	}
	
	public void set(int a, int b, BoardStatus c) {
		System.out.println("a:" + a + ";b:" + b +";c:" + c.toString());
		board[a][b] = c;
	}
	
	public void open(int a, int b) {
		System.out.println("a:" + a + ";b:" + b);
		if (board[a][b] == BoardStatus.OCCUPIED)
			board2[a][b] = BoardStatus.DESTROYED;
		else if (board[a][b] == BoardStatus.WATER)
			board2[a][b] = BoardStatus.WATER;
	}
	
	public void print() {
		System.out.println("");
		System.out.println("  1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣");
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < column; ++j) {
				if (j == 0) {
					System.out.print(Character.toString ((char) (65 + i)) + " ");
				}
				if (board[i][j] == BoardStatus.HIDDEN)
					System.out.print("⬜️");
				else if (board[i][j] == BoardStatus.WATER)
					System.out.print("⬛");
				else if (board[i][j] == BoardStatus.DESTROYED)
					System.out.print("🔴");
				else if (board[i][j] == BoardStatus.OCCUPIED)
					System.out.print("🔵");
				else
					System.out.print("⚪");
			}
			System.out.println("");
		}
	}
	
	public void printBoard2() {
		System.out.println("");
		System.out.println("  1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣8️⃣");
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < column; ++j) {
				if (j == 0) {
					System.out.print(Character.toString ((char) (65 + i)) + " ");
				}
				if (board2[i][j] == BoardStatus.HIDDEN)
					System.out.print("⬛");
				else if (board2[i][j] == BoardStatus.WATER)
					System.out.print("⬜️");
				else if (board2[i][j] == BoardStatus.DESTROYED)
					System.out.print("🔴");
				else if (board2[i][j] == BoardStatus.OCCUPIED)
					System.out.print("🔵");
				else
					System.out.print("⚪");
			}
			System.out.println("");
		}
	}
}
