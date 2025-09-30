package com.dicipline.SystemUser.Services;

import java.time.Duration;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConvertDuration implements AttributeConverter<Duration, String> {
	
	@Override
	public String convertToDatabaseColumn(Duration duration) {
		return duration == null?null: duration.toString();
	}
	@Override
	public Duration convertToEntityAttribute(String value) {
		return value == null?null:Duration.parse(value);
	}

}
