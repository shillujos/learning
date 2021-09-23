package com.personal.algo.graphtraversal;

import java.util.ArrayDeque;
import java.util.HashSet;

/*
 * Write a function, islandCount, that takes in a grid containing Ws and Ls.
 * W represents water and L represents land. 
 * The function should return the number of islands on the grid.
 *  An island is a vertically or horizontally connected region of land.
 */

/**
 * Java implementation of Alvin's graph traversal course - IslandCount
 * CourseReference : https://www.youtube.com/watch?v=tWVWeAqZ0WU&t=51s
 * @author Shillu Jos
 *
 */
public class IslandCount {

	public static void main(String[] args) {
		//Test 00
		String[][] test00 = {
				{"W", "L", "W", "W", "W"},
				{"W", "L", "W", "W", "W"},
				{"W", "W", "W", "L", "W"},
				{"W", "W", "L", "L", "W"},
				{"L", "W", "W", "L", "L"},
				{"L", "L", "W", "W", "W"}
		};
		System.out.println("Test 00 DFS::"+countIslands(test00));
		System.out.println("Test 00 BFS::"+countIslandsBfsWay(test00));

		//Test 01
		String[][] test01 = {
				{"L", "W", "W", "L", "W"},
				{"L", "W", "W", "L", "L"},
				{"W", "L", "W", "L", "W"},
				{"W", "W", "W", "W", "W"},
				{"W", "W", "L", "L", "L"}
		};
		System.out.println("Test 01 DFS::"+countIslands(test01));
		System.out.println("Test 01 BFS::"+countIslandsBfsWay(test01));

		//Test 02
		String[][] test02 = {
				{"L", "L", "L"},
				{"L", "L", "L"},
				{"L", "L", "L"}
		};
		System.out.println("Test 02 DFS::"+countIslands(test02));
		System.out.println("Test 02 BFS::"+countIslandsBfsWay(test02));

		//Test 03
		String[][] test03 = {
				{"W", "W"},
				{"W", "W"},
				{"W", "W"}
		};

		System.out.println("Test 03 DFS::"+countIslands(test03));
		System.out.println("Test 03 BFS::"+countIslandsBfsWay(test03));
	}

	/**
	 * Count the islands either using dfs 
	 * @param grid
	 * @return
	 */
	private static int countIslands(String[][] grid) {
		int islandCount = 0;
		HashSet<String> visited = new HashSet<>();
		//iterate through each cell/node of the grid
		for (int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				if(dfsExplore(row, col, grid, visited)) {
					islandCount++;
				}
			}
		}
		return islandCount;
	}

	private static boolean dfsExplore(int row, int col, String[][] grid, HashSet<String> visited) {
		//boundary check - base case
		boolean rowInBounds = row >= 0 && row < grid.length;
		boolean colInBounds = col >= 0 && col < grid[0].length;

		if(!rowInBounds || !colInBounds) {
			return false;
		}

		//if Water, no need to traverse
		if(grid[row][col].equals("W")) {
			return false;
		}

		//if already visited, don't recount
		String pos = row + "," + col;
		if(visited.contains(pos)) {
			return false;
		}

		visited.add(pos);
		//check the neighbors in all 4 directions
		dfsExplore(row+1, col, grid, visited);
		dfsExplore(row-1, col, grid, visited);
		dfsExplore(row, col+1, grid, visited);
		dfsExplore(row, col-1, grid, visited);

		return true;
	}


	/**
	 * Count islands in BFS way. Preferred way is DFS as it is muc cleaner
	 * @param grid
	 * @return
	 */
	private static int countIslandsBfsWay(String[][] grid) {
		int islandCount = 0;
		int[][] visited = new int[grid.length][grid[0].length];
		//iterate through each cell/node of the grid
		for (int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[0].length; col++) {
				if(bfsExplore(row, col, grid, visited)) {
					islandCount++;
				}
			}
		}
		return islandCount;
	}

	/**
	 * Go trhough each cell, breadth first
	 * @param row
	 * @param col
	 * @param grid
	 * @param visited
	 * @return
	 */
	private static boolean bfsExplore(int row, int col, String[][] grid, int[][] visited) {
		
		if(visited[row][col] == 1) {
			return false;
		}
		visited[row][col] = 1;
		
		if(grid[row][col].equals("W")) {
			return false;
		}
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {row,col});
		
		while(queue.size() > 0) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			if(isValid(r+1, c , grid, visited)) {
				queue.offer(new int[] {r+1,c});
				visited[r+1][c] = 1;
			}
			
			if(isValid(r-1, c , grid, visited)) {
				queue.offer(new int[] {r-1,c});
				visited[r-1][c] = 1;
			}
			
			if(isValid(r, c +1 , grid, visited)) {
				queue.offer(new int[] {r,c+1});
				visited[r][c+1] = 1;
			}
			
			if(isValid(r, c -1 , grid, visited)) {
				queue.offer(new int[] {r,c-1});
				visited[r][c-1] = 1;
			}
		}
		
		return true;
		
	}

	/**
	 * Helper function for BFS to check validity of each cell
	 * @param row
	 * @param col
	 * @param grid
	 * @param visited
	 * @return
	 */
	private static boolean isValid(int row, int col, String[][] grid, int[][] visited ) {
		boolean rowInBounds = row >= 0 && row < grid.length;
		boolean colInBounds = col >= 0 && col < grid[0].length;

		if(!rowInBounds || !colInBounds) {
			return false;
		}

		//if Water, no need to traverse
		if(grid[row][col].equals("W")) {
			return false;
		}
		
		if(visited[row][col] == 1) {
			return false;
		}
		return true;
	}

}
