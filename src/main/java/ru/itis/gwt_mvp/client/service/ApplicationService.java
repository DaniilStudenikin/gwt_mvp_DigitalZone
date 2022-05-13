package ru.itis.gwt_mvp.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Map;
import ru.itis.gwt_mvp.shared.Equipment;
import ru.itis.gwt_mvp.shared.Well;

@RemoteServiceRelativePath("applicationservice")
public interface ApplicationService extends RemoteService{
	void addEquipments(String wellName, int count);
	Map<Well,List<Equipment>> getEquipments(String[] wells);
}
