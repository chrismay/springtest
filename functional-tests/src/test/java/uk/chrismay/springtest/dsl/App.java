package uk.chrismay.springtest.dsl;

import java.io.IOException;

import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class App {

	private final WebConversationWrapper conversation;

	public App() {
		conversation = new WebConversationWrapper(new WebConversation());
	}

	public App isRunning() {
		Waiter.Exec ping = new Waiter.Exec() {

			@Override
			public boolean run() {
				WebResponse res = conversation
						.getResponse("http://localhost:8080/springtest/status.htm");
				try {
					return (res.getText().startsWith("\nok"));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}
		};
		Waiter.waitForCondition(ping, "Fetch status page");
		return this;
	}

	public MainPage mainPage() {
		return new MainPage(conversation);
	}
}
