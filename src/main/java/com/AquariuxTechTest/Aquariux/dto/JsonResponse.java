package com.AquariuxTechTest.Aquariux.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class JsonResponse {

	@Getter
	@Setter
	private Object data;
	
	public JsonResponse(Object data) {
		this.data = data;
	}
}
