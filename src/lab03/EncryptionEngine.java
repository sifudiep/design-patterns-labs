package lab03;

/*
 * EncryptionEngine should handle invalid inputs on it's own.
 * This has mostly the advantage of being independent, meaning it can be reused in other projects. 
 * Can we remove controller? It is not used.
 */

public class EncryptionEngine {
	private Controller controller;
	private int encryptionKey;
	public EncryptionEngine(Controller _controller) {
		controller = _controller;
	}
	
	public boolean validateInputText(String inputText) {
		char[] charArr = inputText.toCharArray();

	    for (int i = 0; i < charArr.length; i++) {
	        if(!Character.isLetter(charArr[i])) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	public boolean setEncryptionKey(String key) {
		try {
			encryptionKey = Integer.parseInt(key);
		} catch(NumberFormatException error) {
			return false;
		}
		
		return true;
	}
	
	public int getEncryptionKey() {
		return encryptionKey;
	}
	
	
	
	// 'A' = 65
	public String encrypt(String inputText) {
		if (!validateInputText(inputText)) return "Enter only letters inside input";
		char inCharArr[] = inputText.toUpperCase().toCharArray();
		for (int i = 0; i < inCharArr.length; i++) {
			inCharArr[i] = (char) (((inCharArr[i] - 'A' + getEncryptionKey()) % 26) + 'A');
		}
		return new String(inCharArr);
	}
	
	public String decrypt(String inputText) {
		if (!validateInputText(inputText)) return "Enter only letters inside input";
		char inCharArr[] = inputText.toUpperCase().toCharArray();
		for (int i = 0; i < inCharArr.length; i++) {
			inCharArr[i] = (char) (((inCharArr[i] + 'A' - getEncryptionKey()) % 26) + 'A');
		}
		
		return new String(inCharArr);
	}
}
