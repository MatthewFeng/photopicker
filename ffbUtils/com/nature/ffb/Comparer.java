package com.nature.ffb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Comparer {
	
	private HashSet oldSet = new HashSet();
	private HashSet newSet = new HashSet();
	
	public void process(){
		File old_file =new File("d:\\temp\\20091118_qiyeold.txt");
		File new_file =new File("d:\\temp\\20091118_qiyenew.txt");
		try {
			readFile(old_file,oldSet);
			readFile(new_file,newSet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				
			}
		System.out.println("oldSetSize_befor: "+oldSet.size());
		oldSet.removeAll(newSet);
		System.out.println("oldSetSize: "+oldSet.size());
		System.out.println("newSetSize: "+newSet.size());
		for (Iterator iter = oldSet.iterator(); iter.hasNext();) {
			String  file_name  = (String) iter.next();
			System.out.println(file_name);
		}
		
	}

	private void readFile(File file, HashSet set) throws IOException {
		FileReader fr = null;
		fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);
		String s = br.readLine();
		while (s != null) {
			int f = s.indexOf('/',0);
			int i = s.indexOf('/',f+1);
			String t = s.substring(i);
			s = br.readLine();
			set.add(t);
		}

		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Comparer c  = new Comparer();
		c.process();
	}

}
