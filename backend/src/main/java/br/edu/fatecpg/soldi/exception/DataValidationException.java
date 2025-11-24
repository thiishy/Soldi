package br.edu.fatecpg.soldi.exception;

public class DataValidationException extends RuntimeException {
    public DataValidationException() { super("Erro na validação dos dados"); }

    public DataValidationException(String message) {
        super(message);
    }
}
