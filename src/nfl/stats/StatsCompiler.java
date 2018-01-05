package nfl.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsCompiler {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int year = 2017;
        
        // load season-ending standings from file
        StandingsLoader standLoader = new StandingsLoader(year);
        List<Team> standings = standLoader.load();
        
        // place the teams in a map
        Map<String, Team> nameToTeamStatsMap = new HashMap<String, Team>();
        
        for(Team t : standings) {
            nameToTeamStatsMap.put(t.getName(), t);
        }
        
        // load season schedule from file
        ScoresLoader scoresLoader = new ScoresLoader(year);
        List<MatchUp> season = scoresLoader.load();
        
        // add opponents to each team
        for(MatchUp m : season) {
            String home = m.getHome();
            String visitor = m.getVisitor();
            nameToTeamStatsMap.get(home).addOpponent(visitor);
            nameToTeamStatsMap.get(visitor).addOpponent(home);
        }
        
        // add opponents wins and losses to each team
        for(Team t : standings) {
            List<String> opponents = t.getOpponents();
            for(String teamName : opponents) {
                Team opponent = nameToTeamStatsMap.get(teamName);
                t.setOpponentWins(t.getOpponentWins() + opponent.getWins());
                t.setOpponentLosses(t.getOpponentLosses() + opponent.getLosses());
            }
        }
        
        // add quality wins and losses for each team
        for(MatchUp m : season) {
            String home = m.getHome();
            String visitor = m.getVisitor();
            int homeScore = m.getHomeScore();
            int visitorScore = m.getVisitorScore();
            
            Team homeTeam = nameToTeamStatsMap.get(home);
            Team visitorTeam = nameToTeamStatsMap.get(visitor);
            
            if(homeScore > visitorScore) {
                if(visitorTeam.getWinPct() > 0.500) {
                    homeTeam.incrementQualityWins();
                }
                else {
                    homeTeam.incrementBadWins();
                }
                
                if(homeTeam.getWinPct() > 0.500) {
                    visitorTeam.incrementQualityLosses();
                }
                else {
                    visitorTeam.incrementBadLosses();
                }
            }
            else if(homeScore < visitorScore) {
                if(visitorTeam.getWinPct() > 0.500) {
                    homeTeam.incrementQualityLosses();
                }
                else {
                    homeTeam.incrementBadLosses();
                }
                
                if(homeTeam.getWinPct() > 0.500) {
                    visitorTeam.incrementQualityWins();
                }
                else {
                    visitorTeam.incrementBadWins();
                }
            }
        }
        
        // display results
        System.out.println("Team|Wins|Losses|Ties|Win %|Opp Wins|Opp Losses|Opp Win %|Q Wins|Q Losses|Q Win %|B Wins|B Losses|B Win %");
        System.out.println(":---|:--:|:--:|:--:|---:|:--:|:--:|---:|:--:|:--:|---:|:--:|:--:|---:");
        for(Team t : standings) {
            System.out.println(t);
        }
    }
}
