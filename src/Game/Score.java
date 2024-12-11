package Game;

import java.io.*;
import java.util.*;

public class Score implements Comparable<Score> {
    private String playerName;
    private int money;
    private static final String SCORES_FILE = "scores.txt";

    public Score(String playerName, int money) {
        this.playerName = playerName;
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Score other) {
        // Sort in descending order (highest money first)
        return Integer.compare(other.money, this.money);
    }

    public void saveScore() {
        try {
            // Check if file exists and if it ends with a newline
            File file = new File(SCORES_FILE);
            boolean needsNewline = false;

            if (file.exists() && file.length() > 0) {
                // Check if the file ends with a newline
                try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                    if (raf.length() > 0) {
                        raf.seek(raf.length() - 1);
                        needsNewline = raf.read() != '\n';
                    }
                }
            }

            // Write the score
            try (FileWriter fw = new FileWriter(SCORES_FILE, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {

                if (needsNewline) {
                    out.println();
                }
                out.println(playerName + " " + money);
            }

        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    public static List<Score> loadScores() {
        List<Score> scores = new ArrayList<>();

        try {
            File file = new File(SCORES_FILE);
            if (!file.exists()) {
                return scores;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split("\\s+");
                        if (parts.length >= 2) {
                            try {
                                String name = parts[0];
                                int score = Integer.parseInt(parts[1]);
                                scores.add(new Score(name, score));
                            } catch (NumberFormatException e) {
                                System.err.println("Invalid score format in line: " + line);
                            }
                        }
                    }
                }
            }

            // Sort scores in descending order (highest to lowest)
            Collections.sort(scores);

        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }

        return scores;
    }

    @Override
    public String toString() {
        return String.format("%-15s %d", playerName, money);
    }

    // Method to rewrite all scores in sorted order
    public static void rewriteSortedScores(List<Score> scores) {
        try (PrintWriter out = new PrintWriter(new FileWriter(SCORES_FILE))) {
            for (Score score : scores) {
                out.println(score.getPlayerName() + " " + score.getMoney());
            }
        } catch (IOException e) {
            System.err.println("Error rewriting scores: " + e.getMessage());
        }
    }
}