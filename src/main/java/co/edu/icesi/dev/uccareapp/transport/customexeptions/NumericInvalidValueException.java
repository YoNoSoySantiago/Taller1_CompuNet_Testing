package co.edu.icesi.dev.uccareapp.transport.customexeptions;

import java.security.InvalidAlgorithmParameterException;

@SuppressWarnings("serial")
public class NumericInvalidValueException extends InvalidAlgorithmParameterException{
	public NumericInvalidValueException() {
		super("The value is not allowed here, please edit and try again");
	}
}
