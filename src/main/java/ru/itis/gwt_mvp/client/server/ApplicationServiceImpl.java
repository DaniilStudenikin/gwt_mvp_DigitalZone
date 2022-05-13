package ru.itis.gwt_mvp.client.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.ep.sdo.Session;
import ru.ep.sdo.SessionFactory;
import ru.ep.sdo.list.XMLListModel;
import ru.itis.gwt_mvp.client.service.ApplicationService;
import ru.itis.gwt_mvp.shared.Equipment;
import ru.itis.gwt_mvp.shared.Well;

public class ApplicationServiceImpl extends RemoteServiceServlet implements ApplicationService {

	@Override
	public void addEquipments(String wellName, int count) {
		DatabaseUtils.createTablesIfNotExists();
		Session session = SessionFactory.getInstance().createSessionFromFile("sdo.session", null);
		XMLListModel listModel = session.getListModel("SDO.Well");
		Well well = getWellByName(wellName);
		Random random = new Random();
		if(well == null) {
			addWell(wellName);
			well = getWellByName(wellName);
		}
		for (int i = 0; i < count; i++) {
			Equipment equipment = new Equipment();
			equipment.setName(generateName(random));
			equipment.setWellId(well.getId());
			listModel.add(equipment);
		}
		session.commit();
		session.close();
	}

	@Override
	public Map<Well,List<Equipment>> getEquipments(String[] wells) {
		DatabaseUtils.createTablesIfNotExists();
		Map<Well, List<Equipment>> map = new HashMap();
		for(String well:wells) {
			Well wellFromDb = getWellByName(well);
			if (wellFromDb == null) {
				continue;
			} else {
				Session session = SessionFactory.getInstance().createSessionFromFile("sdo.session", null);
				XMLListModel listModel = session.getListModel("SDO.Equipment");
				listModel.setWhereClause("id LIKE ?");
				listModel.setWhereClauseParam(0, wellFromDb.getId());
				listModel.fetchAll();
				map.put(wellFromDb, listModel.asList(Equipment.class));
				session.close();
			}
		}
		return map;
	}
	private void addWell(String name) {
		DatabaseUtils.createTablesIfNotExists();
		Session session = SessionFactory.getInstance().createSessionFromFile("sdo.session", null);
		XMLListModel listModel = session.getListModel("SDO.Well");
		Well well = new Well();
		well.setName(name);
		listModel.add(well);
		session.commit();
		session.close();
	}
	private Well getWellByName(String name) {
		Session session = SessionFactory.getInstance().createSessionFromFile("sdo.session", null);
		XMLListModel listModel = session.getListModel("SDO.Well");
		listModel.fetchAll();
		List<Well> wellList = listModel;
		session.close();
		return wellList.get(0);
	}
	private String generateName(Random random) {
		final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		int length = random.nextInt(32 - 1) + 1;
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
		}
		return new String(text);
	}
}
