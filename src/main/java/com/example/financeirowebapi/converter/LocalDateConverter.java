package com.example.financeirowebapi.converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate entityValue) {
		if (entityValue == null) {
			return null;
		}
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		// 23 de abril de 2012
		LocalDate dt = LocalDate.of(entityValue.getYear(), entityValue.getMonth(), entityValue.getDayOfMonth());
		Date saida = (Date) Date.from(dt.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return saida;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date databaseValue) {
		if (databaseValue == null) {
			return null;
		}

		LocalDate localDate = databaseValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return localDate;
	}
}
