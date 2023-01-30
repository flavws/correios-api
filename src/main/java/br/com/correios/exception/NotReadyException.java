package br.com.correios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Serviço está em instalação. Por favor, aguarde de 3 à 5 minutos.")
public class NotReadyException extends Exception{
}
