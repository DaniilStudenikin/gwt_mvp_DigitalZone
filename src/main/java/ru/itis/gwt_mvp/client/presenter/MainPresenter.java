package ru.itis.gwt_mvp.client.presenter;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import ru.itis.gwt_mvp.shared.Equipment;
import ru.itis.gwt_mvp.shared.Well;

public class MainPresenter implements Presenter {
	Display view;
	
	public interface Display {
		public void clear();
		public void updateTable(Map<Well,List<Equipment>> map);
		public void addEquipments();
		public Widget asWidget();
		public void setPresenter(MainPresenter presenter);
		
	}
	public MainPresenter(Display view) {
		this.view = view;
		bind();
	}
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		view.setPresenter(this);
		view.clear();
	}

	@Override
	public void go(Panel panel) {
		// TODO Auto-generated method stub
		panel.add(view.asWidget());
	} 

}
