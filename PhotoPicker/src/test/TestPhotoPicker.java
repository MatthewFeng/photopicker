package test;

import java.io.File;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import cn.ffb.tools.PhotoPicker;


public class TestPhotoPicker {
	
	@Test
	public void getDate(){
		Assert.assertEquals(new PhotoPicker().getPhotoDate(new File("2013-10-13 214658.mov")),new Date());
	}

}
