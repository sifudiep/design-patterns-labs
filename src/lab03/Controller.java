package lab03;

public class Controller {	
	private View view; 
	private EncryptionEngine encryptionEngine;
	
	public Controller() {
		view = new View(this);
		encryptionEngine = new EncryptionEngine(this);
	}
	
	public void encryptButtonClicked() {
		if (!encryptionEngine.setEncryptionKey(view.getEncryptionKey())) {
			view.setOutputText("Enter only number/s in encryption key");
			return;
		}
		String encryptedText = encryptionEngine.encrypt(view.getInputText());
		view.setOutputText(encryptedText);
	}
	
	public void decryptButtonClicked() {
		if (!encryptionEngine.setEncryptionKey(view.getEncryptionKey())) {
			view.setOutputText("Enter only number/s in encryption key");
			return;
		}
		String decryptedText = encryptionEngine.decrypt(view.getInputText());
		view.setOutputText(decryptedText);
	}
}
