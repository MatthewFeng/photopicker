package cn.ffb.tools;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.nature.ffb.utils.FileCopyUtil;
import org.nature.ffb.utils.FileTraveler;
import org.nature.ffb.utils.Visitor;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
//import com.drew.metadata.exif.ExifDirectory;

/**
 * 用于照片按时间分类，共需要四个参数：
 * <li>1,源文件夹
 * <li>2,目标文件夹
 * <li>3,模式，两个值 '1'--copy;'2'--move
 * <li>4,类型 默认为到月,'1'--到日
 * 
 * @param args
 * @param args
 */
public class PhotoPicker {

	private File source;
	private File destination;
	private String mode;
	private String type;

	public Date getPhotoDate(File f) {
		Date date = null;
		String fileName = f.getName();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HHmmss");

		try {
			date = df.parse(fileName);
		} catch (ParseException e1) {
		}

<<<<<<< HEAD
		if (date != null) {
			return date;
		}

		if (!f.getName().toLowerCase().endsWith("jpg") || f.getName().toLowerCase().endsWith("jpeg")) {
=======
		if (!(f.getName().toLowerCase().endsWith("jpg")|| f.getName().toLowerCase().endsWith("jpeg"))) {
>>>>>>> a2edd895c0fc808f605a738848a716fc6d6ba366
			return new Date(f.lastModified());
		} else {

			Metadata metadata;

			try {
				metadata = ImageMetadataReader.readMetadata(f);
				Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
				if (directory != null) {
					date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
				}
			} catch (ImageProcessingException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
			if (date == null) {
				date = new Date(f.lastModified());
			}
			return date;
		}
	}

	public void pickFile(File f, String mode) {
		Date d = getPhotoDate(f);
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
		String day = dayFormat.format(d);
		String month = day.substring(0, 6);
		String year = month.substring(0, 4);
		String destdir = this.destination + System.getProperty("file.separator") + year
				+ System.getProperty("file.separator") + month + System.getProperty("file.separator");
		if ("1".equals(this.type)) {
			destdir = destdir + day + System.getProperty("file.separator");
		}

		String dest = destdir + f.getName();
		if ("1".equals(this.mode)) {
			FileCopyUtil fu = new FileCopyUtil();
			try {
				fu.copyFile(f.getAbsolutePath(), dest);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if ("2".equals(this.mode)) {
			File dir = new File(destdir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			f.renameTo(new File(dest));
		}
	}

	public void pick() {
		FileTraveler ft = new FileTraveler(new Visitor() {
			public void vistor(Object file) {
				PhotoPicker.this.pickFile((File) (file), PhotoPicker.this.mode);
			}
		});
		ft.travel(this.source);
	}

	private boolean precheck(String[] args) {
		if (args.length < 4) {
			System.out.println("该程序用于照片按时间分类，共需要三个参数：" + "\n1,源文件夹" + "\n2,目标文件夹"
					+ "\n3,模式，两个值 '1'--copy;'2'--move\n4,类型 默认为到月,'1'--到日");
			return false;
		}
		this.source = new File(args[0]);
		this.destination = new File(args[1]);
		if (!destination.exists()) {
			destination.mkdirs();
		}
		if (!this.source.isDirectory() || !this.destination.isDirectory()
				|| !("1".equals(args[2]) || "2".equals(args[2]) || !("1".equals(args[3]) || "2".equals(args[3])))) {
			System.out.println("该程序用于照片按时间分类，共需要三个参数：" + "\n1,源文件夹" + "\n2,目标文件夹"
					+ "\n3,模式，两个值 '1'--copy;'2'--move\n4,类型 默认为到月,'1'--到日");
			return false;
		}
		this.mode = args[2];
		this.type = args[3];
		return true;

	}

	public static void main(String[] args) {
		PhotoPicker p = new PhotoPicker();
		
//		if (args==null||args.length==0){
//			args= new String [] {"F:\\ffb\\03_picture\\source","F:\\ffb\\03_picture","2",""};
//		}
		if (p.precheck(args)) {
			p.pick();
		}

	}

}
