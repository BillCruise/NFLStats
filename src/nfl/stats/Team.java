package nfl.stats;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private int wins;
    private int losses;
    private int ties;
    private double winPct;
    
    private int oppWins = 0;
    private int oppLosses = 0;
    
    private int qualityWins = 0; // wins against teams that finished above .500
    private int qualityLosses = 0; // losses to teams that finished above .500
    private int badWins = 0; // wins against teams that finished .500 or below
    private int badLosses = 0; // losses to teams that finished .500 or below
    
    private List<String> opponents;
    
    public Team(String name, int wins, int losses, int ties, double pct) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.winPct = pct;
        
        opponents = new ArrayList<String>();
    }
    
    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public double getWinPct() {
        return winPct;
    }

    public List<String> getOpponents() {
        return opponents;
    }
    
    public void addOpponent(String opponent) {
        opponents.add(opponent);
    }

    public int getOpponentWins() {
        return oppWins;
    }

    public void setOpponentWins(int opponentWins) {
        this.oppWins = opponentWins;
    }

    public int getOpponentLosses() {
        return oppLosses;
    }

    public void setOpponentLosses(int opponentLosses) {
        this.oppLosses = opponentLosses;
    }
    
    public double getOpponentWinPct() {
        return oppWins / 256.0;
    }
    
    public void incrementQualityWins() {
        qualityWins++;
    }
    
    public void incrementQualityLosses() {
        qualityLosses++;
    }
    
    public void incrementBadWins() {
        badWins++;
    }
    
    public void incrementBadLosses() {
        badLosses++;
    }
    
    

    public String toString() {
        double oppWinPct = oppWins / 256.0;
        double qualityWinPct = (double)qualityWins / (qualityWins + qualityLosses);
        double badWinPct = (double)badWins / (badWins + badLosses);
        
        return String.format("%s|%d|%d|%d|%.3f|%d|%d|%.3f|%d|%d|%.3f|%d|%d|%.3f", 
                name, wins, losses, ties, winPct, oppWins, oppLosses, oppWinPct,
                qualityWins, qualityLosses, qualityWinPct, badWins, badLosses, badWinPct);
    }
}
