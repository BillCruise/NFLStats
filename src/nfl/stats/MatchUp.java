package nfl.stats;

public class MatchUp {
    
    private String home;
    private String visitor;
    
    private int homeScore;
    private int visitorScore;
    
    public MatchUp(String matchup) {
        String[] s = matchup.split(" AT ");
        this.visitor = s[0];
        this.home = s[1];
    }

    public MatchUp(String visitor, String home) {
        this.home = home;
        this.visitor = visitor;
    }
    
    public String getHome() {
        return this.home;
    }
    
    public String getVisitor() {
        return this.visitor;
    }
    
    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getVisitorScore() {
        return visitorScore;
    }

    public void setVisitorScore(int visitorScore) {
        this.visitorScore = visitorScore;
    }

    public String toString() {
        return String.format("%s (%d) @ %s (%d)", visitor, visitorScore, home, homeScore);
    }
}
