package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Programutvikling school project for HiOA
 *
 * @author Tetiana Bushuieva
 * 
 * Class name: GoLFileReader. Description: This class implements reading of
 * file.
 */
public class GoLFileReader {

    File file;
    BufferedReader br;

    /**
     * This method creates a filereader from parameter File and returns board
     * which is returned from readGameBoard()
     *
     * @param file - the file to be read.
     * @return Board - the new board with vaues from File
     * @throws IOException - if this file can not be found/read.
     * @throws PatternFormatException - If the file can not be read or if it
     * contains invaild characters.
     */
    public Board readGameBoardFromFile(File file) throws IOException, PatternFormatException {
        Reader r = new FileReader(file);
        if (r == null) {
            throw new IOException("Cannot read file"); //hvis reader er null, dvs kan ikke lese fil, kast IOException
        }
        return readGameBoard(r);
    }

    /**
     * Recieves the file reader as a parameter r and parses the file into
     * pattern. The method checks if file is empty and skips the comments. It
     * then calls methods for reading pattern from the file on a new board and
     * returns the new board.
     *
     *
     * If the read the file is empty then it throws IOException. If the file
     * format is uncorrect it throws PatternFormatException.
     *
     * @param r - file to be read.
     * @return board - returns the pattern of file .
     * @throws IOException - if this file can not be found/read.
     * @throws PatternFormatException - If the file can not be read or if it
     * contains invaild characters.
     */
    private Board readGameBoard(Reader r) throws IOException, PatternFormatException {
        Scanner scan = new Scanner(r);
        if (scan.hasNext() == false) {
            throw new IOException("File is empty!"); //hvis fil har ingen tekst 
        }
        String line = scan.nextLine();

        for (; line.charAt(0) == '#'; line = scan.nextLine()) {
        }

        Board board = parseBoardSize(line);
        checkRule(line);
        readPattern(scan, board);

        return board;
    }

    /**
     * The method reads the lines in the file by comparing it to line patern
     * using regular expression. It then splits the line t read alive and dead
     * values using another regular expression. After reading alive or dead
     * values it updates them on the board. It throws PatternFormatException
     * when there is invalid values in the file.
     *
     * @param scan
     * @param board
     * @throws PatternFormatException
     */
    private void readPattern(Scanner scan, Board board) throws PatternFormatException {
        scan.useDelimiter("!");

        int i = 0;
        int j = 0;

        while (scan.hasNext()) { //leser hele pattern 
            String cellLine = scan.next();

            Pattern cellPattern = Pattern.compile("\\d+|[ob]|\\$"); //leser integer eller character eller $
            Matcher cellMatcher = cellPattern.matcher(cellLine);
            while (cellMatcher.find()) {
                int amount = 1;
                if (cellMatcher.group().matches("\\d+")) {
                    amount = Integer.parseInt(cellMatcher.group());
                    cellMatcher.find();
                }
                String next = cellMatcher.group();
                for (int start = 0; start < amount; start++) {

                    if (next.equals("b")) {
                        board.setState(false, j, i);
                        j++;
                    } else if (next.equals("o")) {
                        board.setState(true, j, i);
                        j++;
                    } else if (next.equals("$")) {
                        i++;
                        j = 0;
                    } else {
                        throw new PatternFormatException("Wrong Format");
                    }

                }
            }
        }
    }

    /**
     * The method reads the size of the board from the line and creates a new
     * board from the size and returns it.
     *
     * @param line
     * @return board
     * @throws PatternFormatException
     */
    private Board parseBoardSize(String line) throws PatternFormatException {
        Pattern rulespattern = Pattern.compile("x = (\\d+), y = (\\d+)");
        Matcher matcher = rulespattern.matcher(line);

        if (!matcher.find()) {
            throw new PatternFormatException("Invalid size");
        }
        int x = Integer.parseInt(matcher.group(1));//rad
        int y = Integer.parseInt(matcher.group(2));//kolone

        Board board = new DynamicGameBoard(x, y);
        return board;
    }

    /**
     * This method checks the rule in the file and verifies that it is Conway
     * game of life. It throws PatternFormatException if it is something else.
     *
     * @param line
     * @throws PatternFormatException
     */
    private void checkRule(String line) throws PatternFormatException {
        Pattern rulespattern;
        Matcher matcher;
        rulespattern = Pattern.compile("rule = (.+)");
        matcher = rulespattern.matcher(line);
        if (!matcher.find()) {
            throw new PatternFormatException("Rule not found");
        } else {
            String group2 = matcher.group(1);
            if (!group2.equals("23/3")
                    && !group2.toLowerCase().equals("b3/s23")
                    && !group2.toLowerCase().equals("s23/b3")
                    && !group2.equals("3/23")) {
                throw new PatternFormatException("Wrong rule in pattern file");
            }
        }
    }
}
