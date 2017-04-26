package org.nature.ffb.utils;

/** 
 * <p>Title: Direcotry copy </p>
 * <p>Description: Direcotry copy</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: cime</p>
 * @author cl.kcitwm
 * @version 1.0
 * @mail   kcitwm@gmail.com
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyUtil {

	private String sourceDir;

	private String destDir;

	private int total = 0;

	// public void copy(String sourceDir, String destDir) {
	// this.sourceDir = sourceDir;
	// this.destDir = destDir;
	// Thread thread = new Thread(this);
	// thread.start();
	// }

	public void copyDir(String sourceDir, String destDir) throws Exception {
		File sourceFile = new File(sourceDir);
		File destFile = new File(destDir);
		String tempSource;
		String tempDest;
		String fileName;
		if (!destFile.exists())
			destFile.mkdirs();

		if (!sourceFile.exists()){
			System.out.println(sourceFile.getAbsolutePath());
			throw new IOException("source directory is not exists!");
		}
		if (sourceFile.isFile()) {
			fileName = sourceFile.getName();
			tempDest = destDir + System.getProperty("file.separator")
					+ fileName;
			copyFile(sourceDir, tempDest);
			return;
		}
		
		File[] files = sourceFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			fileName = files[i].getName();
			tempSource = sourceDir + System.getProperty("file.separator")
					+ fileName;
			tempDest = destDir + System.getProperty("file.separator") + sourceFile.getName();
			copyDir(tempSource, tempDest);
		}
	}

	public void copyFile(String source, String dest) throws Exception {
		File d = new File(dest);
		File s = new File(source);
		if (!d.getParentFile().exists()) {
			d.getParentFile().mkdirs();
		}
		if (d.exists() && d.lastModified() == s.lastModified())
			return;
		FileInputStream fis = new FileInputStream(s);
		FileOutputStream fos = new FileOutputStream(d);
		
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, n);
		}
		fis.close();
		fos.close();
		buffer = null;
		s = null;
		d = null;
	}

	// public void run() {
	// try {
	// copyDir(sourceDir, destDir);
	// } catch (Exception e) {
	// }
	// }
	public static void main(String[] args) throws Exception {
		FileCopyUtil fu = new FileCopyUtil();
		fu.copyDir("D:\\temp", "d:\\tmp2");
	}
}
