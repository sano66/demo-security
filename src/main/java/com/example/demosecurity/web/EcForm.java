package com.example.demosecurity.web;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Book book;
	private String username;

}
