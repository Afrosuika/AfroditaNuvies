package net.infobosccoma.projecte.afroditanuvies;

import java.util.ArrayList;

import net.infobosccoma.projecte.afroditanuvies.model.LlistaVestits;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Bitmap> bitmaps;
	private ImageView imageView;

	/**
	 * Constructor on se li passa l'Activity on s'usarà l'adaptador
	 */
	public ImageAdapter(Context context) {
		this.context = context;
		this.bitmaps = LlistaVestits.getBitmapVestits();
	}

	@Override
	public int getCount() {
		return bitmaps.size();
	}

	@Override
	public Bitmap getItem(int i) {
		return bitmaps.get(i);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Crear un ImageView per a cada element de la GridView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		imageView = new ImageView(context);

		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(140, 140));
		
		imageView.setImageBitmap(LlistaVestits.getBitmapVestits().get(position));

		imageView.setOnClickListener(new OnImageClickListener(position));

		return imageView;
	}
	
	class OnImageClickListener implements OnClickListener{
		
		private int position;
		
		public OnImageClickListener(int position){
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			Intent it = new Intent(context, PaginadorVestitsActivity.class);
			it.putExtra("POSICIO", position);
			
			context.startActivity(it);
		}
		
	}

}
