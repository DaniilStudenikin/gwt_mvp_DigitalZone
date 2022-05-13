package ru.itis.gwt_mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import ru.itis.gwt_mvp.client.presenter.MainPresenter;
import ru.itis.gwt_mvp.client.presenter.MainPresenter.Display;
import ru.itis.gwt_mvp.client.server.DatabaseUtils;
import ru.itis.gwt_mvp.client.presenter.Presenter;
import ru.itis.gwt_mvp.client.service.ApplicationServiceClientImpl;
import ru.itis.gwt_mvp.client.views.UI;

public class AppEntryPoint implements EntryPoint {

	@Override
	public void onModuleLoad() {
		ApplicationServiceClientImpl service = new ApplicationServiceClientImpl(GWT.getModuleBaseURL() + "applicationservice");
		Presenter presenter = new MainPresenter(service.getView());
		presenter.go(RootPanel.get());
	}
	
}
