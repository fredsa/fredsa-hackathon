package mud.client;

import mud.client.action.GetNewPlayerAction;
import mud.client.action.GotPlayer;
import mud.client.action.GotTypedResponse;
import mud.client.action.TypedAction;

import com.allen_sauer.gwt.log.client.DivLogger;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AppEngineMUD implements EntryPoint {

	private final RpcServiceAsync rpcService = GWT.create(RpcService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Log.setUncaughtExceptionHandler();

		RootPanel rootPanel = RootPanel.get();

		Log.info("Welcome to App Engine MUD");
		Widget loggerWidget = Log.getLogger(DivLogger.class).getWidget();
		rootPanel.add(loggerWidget);

		final TextBox textBox = new TextBox();
		rootPanel.add(textBox);

		rpcService.execute(new GetNewPlayerAction(), new GotPlayer());
		textBox.addKeyDownHandler(new KeyDownHandler() {

			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					rpcService.execute(new TypedAction(textBox.getText()),
							new GotTypedResponse() {

								@Override
								public void got(String text) {
									Log.info(text);
								}
							});
					textBox.setText("");
				}
			}
		});

		textBox.setFocus(true);
	}
}
