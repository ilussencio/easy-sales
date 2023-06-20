package br.edu.iftm.api.easysales.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ExceptionResponse implements Serializable {
    private Date timestamp;
    private String message;
    private String datails;
}
