package com.example.demosecurity.web;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String isbn13;
	private String author;
	private String publisher;
	private String title;
	private String imageUrl;
	private int price;
	private LocalDate issueDate;
}
