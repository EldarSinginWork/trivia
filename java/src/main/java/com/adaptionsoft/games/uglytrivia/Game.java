package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    List<Player> players = new ArrayList<>();
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

	LinkedList<String> popQuestions = new LinkedList<>();
	LinkedList<String> scienceQuestions = new LinkedList<>();
	LinkedList<String> sportsQuestions = new LinkedList<>();
	LinkedList<String> rockQuestions = new LinkedList<>();
    
    int currentPlayerIndex = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	public boolean add(String playerName) {
	    players.add(new Player(playerName));
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {

		Player currentPlayer = getCurrentPlayer();
		System.out.println(currentPlayer.getName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayerIndex]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(currentPlayer + " is getting out of the penalty box");
				currentPlayer.movePlayer(roll);

				System.out.println(currentPlayer.getName()
						+ "'s new location is " 
						+ currentPlayer.getPlace());
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(currentPlayer + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
			if (places[currentPlayerIndex] > 11) places[currentPlayerIndex] = places[currentPlayerIndex] - 12;
			
			System.out.println(currentPlayer.getName()
					+ "'s new location is " 
					+ places[currentPlayerIndex]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());		
	}

	private String currentCategory() {
		if (places[currentPlayerIndex] == 0) return "Pop";
		if (places[currentPlayerIndex] == 4) return "Pop";
		if (places[currentPlayerIndex] == 8) return "Pop";
		if (places[currentPlayerIndex] == 1) return "Science";
		if (places[currentPlayerIndex] == 5) return "Science";
		if (places[currentPlayerIndex] == 9) return "Science";
		if (places[currentPlayerIndex] == 2) return "Sports";
		if (places[currentPlayerIndex] == 6) return "Sports";
		if (places[currentPlayerIndex] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = getCurrentPlayer();
		if (inPenaltyBox[currentPlayerIndex]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayerIndex]++;
				System.out.println(currentPlayer.getName()
						+ " now has "
						+ purses[currentPlayerIndex]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayerIndex++;
				if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
				
				return winner;
			} else {
				currentPlayerIndex++;
				if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayerIndex]++;
			System.out.println(currentPlayer
					+ " now has "
					+ purses[currentPlayerIndex]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayerIndex++;
			if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayer().getName() + " was sent to the penalty box");
		inPenaltyBox[currentPlayerIndex] = true;
		
		currentPlayerIndex++;
		if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayerIndex] == 6);
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}
}
