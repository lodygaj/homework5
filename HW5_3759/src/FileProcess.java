import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FileProcess {

	String folderPath;
	File folder;
	List<Map<String, Integer>> termVectors = new ArrayList<Map<String, Integer>>();
	Map<String, Integer> vocabulary = new HashMap<String, Integer>();
	List<Character> characters = new ArrayList<Character>();

	FileProcess(String folderPath) {

		this.folderPath = folderPath;
		this.folder = new File(folderPath);

	}

	/**
	 * This function removes all punctuation and symbols from all files in the
	 * folder
	 * 
	 * @return true is successful, false otherwise
	 */
	void preProcess() {

		for (File file : folder.listFiles()) {
			System.out.println(file);
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				while (line != null) {
					StringTokenizer tokenizer = new StringTokenizer(line,
							" \t\n\r\f,.:;?![]'->@()/+-\"#\\<*_=&~`{}$%|^");
					while (tokenizer.hasMoreTokens()) {
						String word = tokenizer.nextToken();
						if (vocabulary.containsKey(word)) {
							vocabulary.put(word, vocabulary.get(word) + 1);
							System.out.println("Updated word: " + word);
						} else {
							vocabulary.put(word, 1);
							System.out.println("Added new word: " + word);
						}

						for (int i = 0; i < word.length(); i++) {
							if (!characters.contains(word.charAt(i))) {
								characters.add(word.charAt(i));
							}
						}
					}
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException fNFE) {
				fNFE.printStackTrace();
			} catch (IOException iOE) {
				iOE.printStackTrace();
			}
		}

		/*
		 * for (char c : characters) { System.out.print(c); }
		 */
		
		System.out.println(vocabulary);
	}
}
