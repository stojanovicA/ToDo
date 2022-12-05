package com.example.ToDo.error;

import java.io.PrintStream;
import java.io.PrintWriter;

import javax.management.RuntimeErrorException;

public class NekaGreska extends RuntimeErrorException {

	public NekaGreska(Error e, String message) {
		super(e, message);
		// TODO Auto-generated constructor stub
	}

	
	
}
