package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Dictionary implements IDictionary {

	private IAVLTree<String> tree;
	private int size;
	
	public Dictionary() {
		size = 0;
		tree = new AVLTree<String>();
	}

	@Override
	public void load(File file) {
		String line;
		try (InputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				String trimmedWord = line.trim();
				if(exists(trimmedWord)){
					System.out.println("Duplicate word");
				}else
					insert(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if (tree.search(word)) {
			System.out.println("Duplicate word");
			return false;
		}
		tree.insert(word);
		size++;
		return true;
	}

	@Override
	public boolean exists(String word) {
		return tree.search(word);
	}

	@Override
	public boolean delete(String word) {
		if (tree.search(word)) {
			tree.delete(word);
			size--;
			return true;
		}
		System.out.println("Word doesn't exist in the dictionary");
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return tree.height();
	}
}
