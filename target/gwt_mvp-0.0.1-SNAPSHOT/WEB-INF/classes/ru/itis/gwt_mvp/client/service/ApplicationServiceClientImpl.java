package ru.itis.gwt_mvp.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import ru.itis.gwt_mvp.client.views.UI;
import ru.itis.gwt_mvp.shared.Equipment;
import ru.itis.gwt_mvp.shared.Well;

public class ApplicationServiceClientImpl implements ApplicationServiceClient {

	private ApplicationServiceAsync serivce;
	private UI view;

	public ApplicationServiceClientImpl(String url) {
		this.serivce = GWT.create(ApplicationService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.serivce;
		endpoint.setServiceEntryPoint(url);
		this.view = new UI(this);
	}

	@Override
	public void addEquipments(String wellName, int count) {
		// TODO Auto-generated method stub
		this.serivce.addEquipments(wellName, count,new AddEquipmentsCallback());
	}
	public UI getView() {
		return view;
	}
	@Override
	public void getEquipments(String[] wells) {
		// TODO Auto-generated method stub
		this.serivce.getEquipments(wells, new GetEquipmentsCallback());
	}
	private class AddEquipmentsCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			view.clear();
			Window.alert("Failure");
		}

		@Override
		public void onSuccess(Object result) {
			// TODO Auto-generated method stub
			view.clear();
			Window.alert("Success");
		}
		
	}
	private class GetEquipmentsCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			view.clear();
			Window.alert("Failure");
		}

		@Override
		public void onSuccess(Object result) {
			// TODO Auto-generated method stub
			view.clear();
			if(result instanceof Map<?,?>) {
				Map<Well, List<Equipment>> map = (Map<Well, List<Equipment>>) result;
				view.updateTable(map);
			}
		}
		
	}
}
