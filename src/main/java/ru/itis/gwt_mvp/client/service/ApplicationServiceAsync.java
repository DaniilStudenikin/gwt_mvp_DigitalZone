package ru.itis.gwt_mvp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ApplicationServiceAsync {
	void addEquipments(String wellName, int count, AsyncCallback callback);
	void getEquipments(String[] wells,AsyncCallback callback);
}
