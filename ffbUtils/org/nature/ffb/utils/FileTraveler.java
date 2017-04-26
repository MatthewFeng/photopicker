package org.nature.ffb.utils;

import java.io.File;
import java.io.FileFilter;

public class FileTraveler {

	private File file;

	private FileFilter filter;
	
	private Visitor visitor;
	

	public void travel() {
		
		travel(file);
	}

	public void travel(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles(filter);
			for (int i = 0; i < files.length; i++) {
				
				travel(files[i]);
			}
		}
		if (file.isFile()){
			visitor.vistor(file);
		}

	}

	public FileTraveler(Visitor visitor){
		this.visitor = visitor;
	}
	public FileTraveler(File file,Visitor visitor,FileFilter fileFilter) {
		this.file = file;
		this.visitor = visitor;
		this.filter=fileFilter;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public FileFilter getFilter() {
		return filter;
	}

	public void setFilter(FileFilter filter) {
		this.filter = filter;
	}

}
