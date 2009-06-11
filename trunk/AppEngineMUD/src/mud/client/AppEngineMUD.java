package mud.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class AppEngineMUD implements EntryPoint {

	private final RpcServiceAsync rpcService = GWT
			.create(RpcService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		TextArea history = new TextArea();
		rootPanel.add(history);
		
		TextBox textBox = new TextBox();
		rootPanel.add(textBox);
		
		rpcService.execute(new TypedAction<TypedResponse>(),new AsyncCallback<TypedResponse>() {
		
			public void onSuccess(TypedResponse result) {
				// TODO Auto-generated method stub
		
			}
		
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
		
			}
		});
	}
}
