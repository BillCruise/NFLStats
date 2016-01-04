package nfl.stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScheduleLoader {
    
    private int year = 2014;
    
    public ScheduleLoader() {
        this.year = 2014;
    }
    
    public ScheduleLoader(int year) {
        this.year = year;
    }
    
    public List<MatchUp> load() {
        List<MatchUp> season = new ArrayList<MatchUp>();
        
        File scheduleFile = new File("resources/schedule_" + year + ".txt");
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(scheduleFile);
            String line;
            
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                if(line.length() > 0 && line.contains("--")) {
                    // strip off game time
                    String[] tokens = line.split(" -- ");
                    String matchup = tokens[0];
                    
                    // strip off * (footnote)
                    matchup = matchup.replaceAll(" [*]", "");
                    
                    // strip off parenthetical note
                    matchup = matchup.replace(" (LONDON)", "");
                    
                    // normalize new york
                    matchup = matchup.replace("N.Y.", "NEW YORK");
                    
                    MatchUp m = new MatchUp(matchup);
                    season.add(m);
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        finally {
            scanner.close();
        }
        
        return season;
    }

    /**
     * Test the loader.
     * @param args
     */
    public static void main(String[] args) {
        ScheduleLoader loader = new ScheduleLoader();
        List<MatchUp> season = loader.load();
        
        int lineCount = 0;
        
        for(MatchUp m : season) {
            System.out.println(++lineCount + " - " + m);
        }
    }
}
