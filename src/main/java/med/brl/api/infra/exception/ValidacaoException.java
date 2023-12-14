package med.brl.api.infra.exception;

public class ValidacaoException extends RuntimeException{

    public ValidacaoException(String messagem){
        super(messagem);
    }
}
