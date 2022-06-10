package com.adaptionsoft.games.uglytrivia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    public void shouldGiveWrongAnswer() {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");

        aGame.roll(1);
        assertTrue(aGame.wrongAnswer());
    }

    @Test
    public void shouldFailIfTooManyPlayers() {
        Game aGame = new Game();

        aGame.add("Pat1");
        aGame.add("Pat2");
        aGame.add("Pat3");
        aGame.add("Pat4");
        aGame.add("Pat5");
        aGame.add("Pat6");
        aGame.add("Pat7");
    }

    @Test
    public void shouldWin_afterSixCorrectAnswers() {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");

        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());
        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());

        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());
        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());

        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());
        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());

        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());
        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());

        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());
        aGame.roll(1);
        assertTrue(aGame.wasCorrectlyAnswered());

        aGame.roll(1);
        assertFalse(aGame.wasCorrectlyAnswered());
    }

}