package br.edu.fatecpg.soldi.exception;

public class PasswordConfirmationException extends RuntimeException {
    public PasswordConfirmationException() { super("Erro na validação dos dados"); }

    public PasswordConfirmationException(String message) {
        super(message);
    }
}
