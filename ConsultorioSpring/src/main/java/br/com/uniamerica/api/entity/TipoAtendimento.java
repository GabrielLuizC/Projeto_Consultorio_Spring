package br.com.uniamerica.api.entity;

public enum TipoAtendimento {

    particular("Particular"),
    convenio("Convênio");

    public final String valor;

    private TipoAtendimento(String valor){
        this.valor = valor;
    }
}
