package net.infobosccoma.projecte.afroditanuvies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementsmenuAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	private final String[] values2;

	public ElementsmenuAdapter(Context context, String[] values,String[] values2) {
		super(context, R.layout.listitem_llistesopcions, values);
		this.context = context;
		this.values = values;
		this.values2 = values2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.listitem_llistesopcions, parent,
				false);
		TextView textView = (TextView) rowView.findViewById(R.id.elementMenuText);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.elementMenuImatge);
		textView.setText(values[position]);
		imageView.setImageResource(context.getResources().getIdentifier(values2[position], "drawable", context.getPackageName()));
		return rowView;
	}
}
