package com.miage.altea.battle_api.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Bad Request")  // 404
public class ExceptionNotFound extends Exception {
}
