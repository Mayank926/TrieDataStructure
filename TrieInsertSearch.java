
import java.util.*;

class TrieInsertSearch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of test cases");
		int t = sc.nextInt();
		int num = t;
		while (t-- > 0) {
			System.out.println("Enter number of words to be considered for test case number " + (num - t));
			int n = sc.nextInt();
			System.out.println("Enter the words separated by a single space as delimeter and then press enter.");
			sc.nextLine();
			String[] keys = sc.nextLine().split(" ");

			TrieNode root = new TrieNode();
			for (int i = 0; i < n; i++) {
				insert(root, keys[i]);
			}
			System.out.println(" Trie data structure creation done");
			System.out.println(" now search");
			while (true) {
				System.out.println("enter word to be searched");
				String abc = sc.nextLine();
				if (search(root, abc))
					System.out.println(1);
				else
					System.out.println(0);
			}
		}
	}

	static final int ALPHABET_SIZE = 26;

	// trie node
	static class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];

		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;

		TrieNode() {
			isEndOfWord = false;
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;
		}
	};

//Function to insert string into TRIE.
	static void insert(TrieNode root, String key) {
		int len = key.length();
		TrieNode pointer = root;
		int level = 0;
		System.out.println("key to be stored is :" + key);
		for (char c : key.toCharArray()) {
			int index = 0;
			if (c > 96)
				index = c - 97;
			else
				index = c - 65;
			// check for presense of the charcter in root node
			if (pointer.children[index] == null) {
				System.out.println(
						"Creating a new node for charcter : " + c + " at index " + index + "at level " + level);
				TrieNode newNode = new TrieNode();
				newNode.isEndOfWord = false;
				pointer.children[index] = newNode;
				pointer = newNode;
				level++;
			} else {
				System.out.println("Character " + c + " already present at level " + level + " so moving to child");
				pointer = pointer.children[index];
				level++;
			}

		}
		pointer.isEndOfWord = true;
		System.out.println("Entry of word : " + key + " completed. the endofWord at level " + level + " is "
				+ pointer.isEndOfWord);

	}

//Function to use TRIE data structure and search the given string.
	static boolean search(TrieNode root, String key) {
		boolean result = false;
		TrieNode pointer = root;
		boolean loopBroken = false;
		int level = 0;
		for (char c : key.toCharArray()) {
			int index = 0;
			if (c > 96)
				index = c - 97;
			else
				index = c - 65;
			if (pointer.children[index] != null) {
				System.out.println("Charcter " + c + " present at level " + level + " at index " + index);
				pointer = pointer.children[index];
				level++;
			} else {
				System.out.println("Charcter " + c + " not present as expected, hence setting false");
				result = false;
				loopBroken = true;
				break;
			}
		}
		if (pointer.isEndOfWord && !loopBroken) {
			System.out.println("End of word is TRUE for endpointer for word " + key + " at level " + level);
			result = true;
		}
		return result;
	}

}
