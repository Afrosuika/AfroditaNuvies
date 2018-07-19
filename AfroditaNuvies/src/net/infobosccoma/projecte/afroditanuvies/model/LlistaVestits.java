package net.infobosccoma.projecte.afroditanuvies.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class LlistaVestits {
	
	private static ArrayList<Vestit> vestits;
	private static ArrayList<Bitmap> bitmapVestits;

	public static ArrayList<Vestit> getVestits() {
		return vestits;
	}

	public static void setVestits(ArrayList<Vestit> vestits) {
		LlistaVestits.vestits = vestits;
	}

	public static ArrayList<Bitmap> getBitmapVestits() {
		return bitmapVestits;
	}

	public static void setBitmapVestits(ArrayList<Bitmap> bitmapVestits) {
		LlistaVestits.bitmapVestits = bitmapVestits;
	}
	
	

}
