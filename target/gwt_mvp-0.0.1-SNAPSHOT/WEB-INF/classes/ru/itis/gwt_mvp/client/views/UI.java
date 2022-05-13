package ru.itis.gwt_mvp.client.views;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableColElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.thirdparty.guava.common.collect.Multiset.Entry;

import ru.itis.gwt_mvp.client.presenter.MainPresenter;
import ru.itis.gwt_mvp.client.service.ApplicationServiceClientImpl;
import ru.itis.gwt_mvp.shared.Equipment;
import ru.itis.gwt_mvp.shared.Well;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class UI extends Composite implements MainPresenter.Display {

	private static UIUiBinder uiBinder = GWT.create(UIUiBinder.class);

	private MainPresenter presenter;
	private ApplicationServiceClientImpl service;

	interface UIUiBinder extends UiBinder<Widget, UI> {
	}

	public UI(ApplicationServiceClientImpl service) {
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button getEqupments;

	@UiField
	Button addEquipments;

	@UiField
	Label wellNameLabel;

	@UiField
	Label equipmentCountLabel;

	@UiField
	TextArea wellName;

	@UiField
	TextArea equipmentsCount;
	@UiField
	Label equipmentsToGetLabel;
	@UiField
	TextArea equipmentsToGet;

	@UiField
	Button submitGetEquipments;

	@UiField
	Button submitAddEquipments;

	@UiField
	FlexTable table;

	@Override
	public void clear() {
		wellNameLabel.setVisible(false);
		equipmentCountLabel.setVisible(false);
		wellName.setVisible(false);
		equipmentsCount.setVisible(false);
		submitGetEquipments.setVisible(false);
		submitAddEquipments.setVisible(false);
		equipmentsToGetLabel.setVisible(false);
		equipmentsToGet.setVisible(false);
		table.setVisible(false);

	}

	@UiHandler("getEqupments")
	void onClickGetEquipments(ClickEvent e) {
		clear();
		equipmentsToGetLabel.setVisible(true);
		equipmentsToGet.setVisible(true);
		submitGetEquipments.setVisible(true);
	}

	@UiHandler("addEquipments")
	void onClickAddEquipments(ClickEvent e) {
		clear();
		wellNameLabel.setVisible(true);
		wellName.setVisible(true);
		equipmentCountLabel.setVisible(true);
		equipmentsCount.setVisible(true);
		submitAddEquipments.setVisible(true);
	}

	@UiHandler("submitAddEquipments")
	void onClickSubmitAddEquipments(ClickEvent e) {
		clear();
		service.addEquipments(wellName.getText(), Integer.valueOf(equipmentsCount.getText()));
	}

	@UiHandler("submitGetEquipments")
	void onClickSubmitGetEquipments(ClickEvent e) {
		clear();
		service.getEquipments(equipmentsToGet.getText().split(","));
	}

	@Override
	public void updateTable(Map<Well, List<Equipment>> map) {
		for(Map.Entry<Well, List<Equipment>> entry:map.entrySet()) {
			for(int j = 0; j < entry.getValue().size();j++) {
				table.setText(j,0,entry.getKey().getName());
				table.setText(j, 1, entry.getValue().get(j).getName());
			}
		}
		table.setVisible(true);
	}

	@Override
	public void addEquipments() {
		String well = wellName.getText();
		String equipments = equipmentsCount.getText();
		service.addEquipments(well, Integer.valueOf(equipments));
	}

	@Override
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}
}
