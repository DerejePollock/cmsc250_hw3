/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.lawrence.tabbedhello;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Players {
    private ArrayList<Players> player;
    private int won;
    private int played;

    public ArrayList<Players> getPlayer() {
        return player;
    }

    public int getWon() {
        return won;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayer(ArrayList<Players> player) {
        this.player = player;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void setPlayed(int played) {
        this.played = played;
    }
    
    
    public void writeTo(PrintWriter output) {
        for(Players r : player) {
            r.writeTo(output);
        }
    }
    
    public Players() {
        player = new ArrayList<Players>();
    }
    
    
    public void readFrom(Scanner input) {
        // Read the five rooms from the input file
        for(int n = 0;n < 5;n++) {
            Players newRoom = new Players();
            newRoom.readFrom(input);
            player.add(newRoom);
        }
    }
    
    public Players getRoom(String forName) {
//        for(Players r : player) {
////            if(r.getName().equalsIgnoreCase(forName))
////                return r;
////        }
        return null;
    }
}

