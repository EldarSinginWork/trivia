package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String name;
    private int place = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return name;
    }

    public void movePlayer(int roll) {
        this.place += roll;
        if (this.place > 11) {
            this.place -= 12;
        }
    }
}
