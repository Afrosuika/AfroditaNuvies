package net.infobosccoma.projecte.afroditanuvies.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AppConstant {

	// Number of columns of Grid View
	public static final int NUM_OF_COLUMNS = 3;

	// Gridview image padding
	public static final int GRID_PADDING = 8; // in dp

	// SD card image directory
	public static final String PHOTO_ALBUM = "DCIM"+File.separator+"Camera";

	// supported file formats
	public static final List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg",
			"png");
	
	// URL DE LA PÀGINA WEB
	public static final String URL = "http://afroditanuvies.bugs3.com";
	
}
