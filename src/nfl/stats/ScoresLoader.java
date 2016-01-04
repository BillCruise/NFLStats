package nfl.stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoresLoader {
    
private int year = 2014;
    
    public ScoresLoader() {
        this.year = 2014;
    }
    
    public ScoresLoader(int year) {
        this.year = year;
    }
    
    public List<MatchUp> load() {
        List<MatchUp> matchUps = new ArrayList<MatchUp>();
        
        File scheduleFile = new File("resources/scores_" + year + ".txt");
        Scanner scanner = null;
        List<String> scheduleLines = new ArrayList<String>();
        
        try {
            scanner = new Scanner(scheduleFile);
            String line;
            
            // loop through the file and only keep relevant lines
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(!line.startsWith("Round") && !line.startsWith("(") &&
                   !line.startsWith("Dec") && !line.startsWith("Nov") &&
                   !line.startsWith("Oct") && !line.startsWith("Sep") &&
                   !line.startsWith("Jan")) {
                    line = line.replaceAll("\\t", "");
                    scheduleLines.add(line);
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        finally {
            scanner.close();
        }
        
        // loop through the schedule lines and process them
        // each matchup is three lines: visitor, home, and score
        for(int i = 0; i < scheduleLines.size() / 3; i++) {
            int index = i * 3;
            String visitor = scheduleLines.get(index);
            String home = scheduleLines.get(index + 1);
            home = home.replaceAll("@ ", "");
            
            String[] scores = scheduleLines.get(index + 2).split(" : ");
            int visitorScore = Integer.parseInt(scores[0]);
            int homeScore = Integer.parseInt(scores[1]);
            
            MatchUp m = new MatchUp(visitor, home);
            m.setVisitorScore(visitorScore);
            m.setHomeScore(homeScore);
            matchUps.add(m);
        }
        
        return matchUps;
    }

    /**
     * Test the loader.
     * @param args
     */
    public static void main(String[] args) {
        ScoresLoader scoresLoader = new ScoresLoader();
        List<MatchUp> season = scoresLoader.load();
        
        int lineCount = 0;
        
        for(MatchUp m : season) {
            System.out.println(++lineCount + " - " + m);
        }
    }
}
