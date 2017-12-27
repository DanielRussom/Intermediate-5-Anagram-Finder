package anagramfinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Daniel Russom
 *
 */
public class Controller {

	public static void main(String[] args) throws IOException {
		String[] unsearchedWordList = readLines("bin/Words2.txt");
		String[] wordList = unsearchedWordList.clone();
		int matches = 0;
		for (int i = 0; i < wordList.length - 1; i++) {
			if (wordList[i].equals("")) {
				continue;
			}
			for (int j = i + 1; j < wordList.length; j++) {
				if (wordList[j].equals("")) {
					continue;
				}
				if (compareWords(wordList[i], wordList[j])) {
					System.out.println(wordList[i] + " matches " + wordList[j]);
					wordList[j] = "";
					matches += 1;
				}
			}

		}
		System.out.println("There were " + matches);
	}

	// Handles reading lines from a file
	public static String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}

		bufferedReader.close();

		return lines.toArray(new String[lines.size()]);
	}

	// Compares two words
	public static boolean compareWords(String word1, String word2) {
		// Iterates through each char in word1
		for (int i = 0; i < word1.length(); i++) {
			// Iterates through each char in word2
			for (int j = 0; j < word2.length(); j++) {
				// If the chars match, replace with a space
				if (word1.charAt(i) == word2.charAt(j)) {
					word2 = word2.substring(0, j) + " " + word2.substring(j + 1);
					break;
				}
				// If words don't contain same char, return false
				if (j == word2.length() - 1) {
					return false;
				}
			}
		}
		// Return true if words match
		return true;
	}
}
