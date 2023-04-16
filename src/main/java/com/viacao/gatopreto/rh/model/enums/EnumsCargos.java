package com.viacao.gatopreto.rh.model.enums;

public enum EnumsCargos {

	MOTORISTA("MOTORISTA"),
	COBRAODR("COBRADOR"),
	FISCAL_DE_PONTO("FISCAL_DE_PONTO"),
	FISCAL_DO_CCO("FISCAL_DO_CCO"),
	PORTARIA("PORTARIA"),
	INSPETOR("INSPETOR"),
	GERENCIA("GERENCIA");
	
	private String cargo;

	private EnumsCargos(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	
}
