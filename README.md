# NFLStats
Generates summary stats for an NFL season given team schedules and scores.

NFL 2015 standings downloaded from: http://www.nfl.com/standings?category=league
Playoff symbols (x, y, z, *) removed manually.

NFL 2015 scores and schedule downloaded from http://www.scoreboard.com/nfl/results/

The output is in table format suitable for posting to reddit.

Opp Wins - Total wins by all of each team's opponents.

Opp Losses - Total losses by all of a team's opponents.

Opp Win % - Opposing teams win percent. Denver, New England, Seattle, and Arizona are the only playoff teams whose opponents were above .500. Oakland was the overall leader in this category, but their opponents benefited from getting to play Oakland (sorry Raiders fans).

The next few columns tell how each team did against teams with a winning (above .500) record or losing (.500 and below) record.

Q Wins - Quality Wins are wins against teams with a record above .500.

Q Losses - Quality Losses are losses to teams with a record above .500.

Q Win % - Q Wins / (Q Wins + Q Losses)

B Wins - Bad Wins are wins against teams with a record of .500 or below.

B Losses - Bad Losses are losses to teams with a record of .500 or below.

B Win % - B Wins / (B Wins + B Losses)
