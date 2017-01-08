package net.mohron.skyclaims.exception;

import org.spongepowered.api.text.Text;

public class CreateIslandException extends Exception {
	private Text message;

	public CreateIslandException(Text message) {
		super(message.toPlain());
		this.message = message;
	}

	public Text getText() {
		return message;
	}
}