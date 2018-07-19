package net.infobosccoma.projecte.afroditanuvies;

import net.infobosccoma.projecte.afroditanuvies.R;
import net.infobosccoma.projecte.afroditanuvies.model.LlistaVestits;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class PageFragment extends Fragment implements OnTouchListener {

	private ImageView imatgeVestit;
	private Button btnMesInfo;
	private LinearLayout layoutInferior, layoutMesInfo;
	private int posicio;
	private float y = 0;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_page, container, false);

		rootView.setOnTouchListener(this);

		// Posar-hi el títol del vestit
		getActivity()
				.setTitle(LlistaVestits.getVestits().get(posicio).getNom());

		// Recuperar el Layouts de Mes Informacio
		layoutInferior = (LinearLayout) rootView
				.findViewById(R.id.layoutInferior);
		layoutMesInfo = (LinearLayout) rootView.findViewById(R.id.layoutMesInfo);

		// Assignar la imatge corresponent
		imatgeVestit = (ImageView) rootView.findViewById(R.id.imatgeVestit);
		imatgeVestit.setImageBitmap(LlistaVestits.getBitmapVestits().get(
				getPosicio()));

		// Recuperar el botó
		btnMesInfo = (Button) rootView.findViewById(R.id.btnMesInfo);

		return rootView;
	}

	public int getPosicio() {
		return posicio;
	}

	public void setPosicio(int posicio) {
		this.posicio = posicio;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				y = event.getY();
				return true;
			case MotionEvent.ACTION_UP:
				if(y + 20 < event.getY()){
					amagarDadesVestit();
				}
				else if(y - 20 > event.getY()){
					mostrarDadesVestit();
				}
				
				y = event.getY();
				return true;
			default:
				return false;
		}
	}
	
	private void mostrarDadesVestit() {
		LinearLayout.LayoutParams paramsLayoutInferior = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
				0.9f);
		btnMesInfo.setVisibility(View.GONE);
		layoutMesInfo.setVisibility(View.VISIBLE);
		layoutInferior.setLayoutParams(paramsLayoutInferior);
	}
	
	private void amagarDadesVestit() {
		LinearLayout.LayoutParams paramsLayoutInferior = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
				0.1f);
		layoutMesInfo.setVisibility(View.GONE);
		btnMesInfo.setVisibility(View.VISIBLE);
		layoutInferior.setLayoutParams(paramsLayoutInferior);
	}
}
