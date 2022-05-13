package ru.itis.gwt_mvp.client.service;

public interface ApplicationServiceClient {
	void addEquipments(String wellName, int count);
	void getEquipments(String[] wells);
}
