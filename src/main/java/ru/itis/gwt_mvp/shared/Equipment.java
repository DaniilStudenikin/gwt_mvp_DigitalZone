package ru.itis.gwt_mvp.shared;

import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Xml;

@Xml(name = "Equipment")
public class Equipment extends Entity {
	public static final String PROPERTYNAME_id = "id";
	public static final String PROPERTYNAME_name = "name";
	public static final String PROPERTYNAME_wellId = "wellId";

	@Xml(name = "id")
	private Integer id;

	@Xml(name = "name")
	private String name;

	@Xml(name = "wellId")
	private Integer wellId;

	public Equipment() {
	}

	public Equipment(Integer id, String name, Integer wellId) {
		this.setId(id);
		this.setName(name);
		this.setWellId(wellId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		Integer oldValue = this.id;
		this.id = id;
		firePropertyChange(PROPERTYNAME_id, oldValue, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange(PROPERTYNAME_name, oldValue, name);
	}

	public Integer getWellId() {
		return wellId;
	}

	public void setWellId(Integer wellId) {
		Integer oldValue = this.wellId;
		this.wellId = wellId;
		firePropertyChange(PROPERTYNAME_wellId, oldValue, wellId);
	}
}
