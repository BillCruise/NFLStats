package nfl.stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StandingsLoader {
    
    private int year = 2014;
    
    public StandingsLoader() {
        this.year = 2014;
    }
    
    public StandingsLoader(int year) {
        this.year = year;
    }
    
    public List<Team> load() {
        List<Team> standings = new ArrayList<Team>();
        
        File standingsFile = new File("resources/standings_" + year + ".txt");
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(standingsFile);
            String line;
            
            // strip off the standings file header
            line = scanner.nextLine();
            line = scanner.nextLine();
            
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] tokens = line.split("\\t");
                String name = tokens[0];
                
                if (year < 2017) { // Conv and Div columns were removed in 2017
	                int wins = Integer.parseInt(tokens[3]);
	                int losses = Integer.parseInt(tokens[4]);
	                int ties = Integer.parseInt(tokens[5]);
	                double pct = Double.parseDouble(tokens[6]);
	                
	                Team team = new Team(name, wins, losses, ties, pct);
	                standings.add(team);
                }
                else {
                	int wins = Integer.parseInt(tokens[1]);
	                int losses = Integer.parseInt(tokens[2]);
	                int ties = Integer.parseInt(tokens[3]);
	                double pct = Double.parseDouble(tokens[4]);
	                
	                Team team = new Team(name, wins, losses, ties, pct);
	                standings.add(team);
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        finally {
            scanner.close();
        }
        
        return standings;
    }

    /**
     * Test the loader.
     * @param args
     */
    public static void main(String[] args) {
        StandingsLoader loader = new StandingsLoader();
        List<Team> standings = loader.load();
        
        for(Team team : standings) {
            System.out.println(team);
        }
    }
}
