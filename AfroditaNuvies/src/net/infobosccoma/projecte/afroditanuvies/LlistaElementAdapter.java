package net.infobosccoma.projecte.afroditanuvies;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import net.infobosccoma.projecte.afroditanuvies.model.Element;
import net.infobosccoma.projecte.afroditanuvies.utils.AppConstant;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LlistaElementAdapter extends ArrayAdapter<Element> {

	private final Context context;
	private final ArrayList<Element> values;
	private ImageView imageView;

	public LlistaElementAdapter(Context context, ArrayList<Element> values) {
		super(context, R.layout.listitem_llistesopcions, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listitem_llistesopcions,
				parent, false);
		TextView textView = (TextView) rowView
				.findViewById(R.id.elementMenuText);
		imageView = (ImageView) rowView.findViewById(R.id.elementMenuImatge);
		textView.setText(values.get(position).getNom());
		


		 try {
			imageView.setImageBitmap(new connexionHTTP().execute(AppConstant.URL
						+ values.get(position).getImatgethumburl()+values.get(position).getImatge()).get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return rowView;
	}

	// class TascaDescarrega extends AsyncTask<String, Void, Bitmap> {
	//
	// @Override
	// protected Bitmap doInBackground(String... params) {
	// return loadImageFromNetwork(params[0]);
	// }
	//
	// protected void onPostExecute(Bitmap result) {
	// imageView.setImageBitmap(result);
	// }
	//
	// }
	//
	// private Bitmap loadImageFromNetwork(String url) {
	//
	// Bitmap bitmap = null;
	// try {
	// bitmap = BitmapFactory.decodeStream((InputStream) new URL(url)
	// .getContent());
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return bitmap;
	//
	// }

	private class connexionHTTP extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap bitmap = null;
			for (String url : urls) {
				try {
					bitmap = BitmapFactory.decodeStream((InputStream) new URL(
							url).getContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bitmap;
		}
	}
}