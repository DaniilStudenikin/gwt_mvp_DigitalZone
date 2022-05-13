package ru.itis.gwt_mvp.shared;

import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Xml;

@Xml(name = "ROW")
public class Well extends Entity {
	public static final String PROPERTYNAME_id = "id";
	public static final String PROPERTYNAME_name = "name";
	
	@Xml(name = "id")
	private Integer id;
	
	@Xml(name = "name")
	private String name;

	public Well() {
	}
	
	public Well(Integer id, String name) {
		this.setId(id);
		this.setName(name);
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		Integer oldValue = this.id;
		this.id = id;
		firePropertyChange(PROPERTYNAME_id,oldValue,id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange(PROPERTYNAME_name,oldValue,name);
	}
}
