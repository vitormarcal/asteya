package com.vitormarcal.asteya.exceptionhandlers;

import lombok.ToString;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class AsteyaExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private static final Logger loggerUtil = LoggerFactory.getLogger(AsteyaExceptionHandler.class);


    @Autowired
    public AsteyaExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro>erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = criarListaDeErros(ex.getBindingResult());
        loggerUtil.error(erros.toString(), ex);
        return super.handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.nao-encotrado", null, LocaleContextHolder.getLocale());
        String  mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        loggerUtil.error(mensagemUsuario, ex);

        List<Erro>erros =Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("recurso.operacaoo-nao-permitida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro>erros =Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<Erro> errors = criarListaDeErros(ex.getConstraintViolations());

        return super.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<Object> handleTransactionSystemException(TransactionSystemException ex, WebRequest request) {
        String mensagemUsuario = messageSource.getMessage("erro.inesperado.database", null, LocaleContextHolder.getLocale());
        loggerUtil.error(mensagemUsuario, ex);
        List<Erro> errors;
        if (ex.getMostSpecificCause() instanceof ConstraintViolationException) {
            errors = criarListaDeErros(((ConstraintViolationException) ex.getMostSpecificCause()).getConstraintViolations());
            return super.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        errors = Collections.singletonList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return super.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> criarListaDeErros(Set<ConstraintViolation<?>> constraintViolations) {
        List<Erro> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolations) {
            String mensagemUsuario = violation.getPropertyPath() + ": " + violation.getMessage();
            String mensagemDesenvolvedor = violation.getRootBeanClass().getName() + ": " + mensagemUsuario;
            errors.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
            violation.getMessageTemplate();
        }
        return errors;
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        });
        return erros;
    }

    @ToString
    public static class Erro {
        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        private Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            super();
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        @SuppressWarnings("unused")
        public String getMensagemDesenvolvedor() {
            return mensagemDesenvolvedor;
        }

        @SuppressWarnings("unused")
        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

    }
}
