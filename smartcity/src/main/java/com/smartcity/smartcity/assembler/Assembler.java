package com.smartcity.smartcity.assembler;

public interface Assembler <E, R> {

	void mergeEntityWithrepresentation(E entity, R representation);

	R createRepresentation(E entity);
}
