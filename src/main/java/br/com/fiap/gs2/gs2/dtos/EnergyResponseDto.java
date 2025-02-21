package br.com.fiap.gs2.gs2.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class EnergyResponseDto {
    private Long id;
	private String nome;
	private BigDecimal valor;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    

}
