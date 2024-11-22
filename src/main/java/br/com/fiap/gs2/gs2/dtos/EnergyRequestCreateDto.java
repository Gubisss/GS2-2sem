package br.com.fiap.gs2.gs2.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EnergyRequestCreateDto {
    private String nome;
	private BigDecimal valor;
}
