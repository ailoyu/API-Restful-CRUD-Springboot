package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;

@Component
public class NewConverter {
	
	public NewEntity toEntity(NewDTO newDTO) {
		NewEntity entity = new NewEntity();
		
		entity.setTitle(newDTO.getTitle());
		entity.setContent(newDTO.getContent());
		entity.setShortDescription(newDTO.getShortDescription());
		entity.setThumbnail(newDTO.getThumbnail());
		
		return entity;
	}
	
	public NewDTO toDTO(NewEntity newEntity) {
		NewDTO dto = new NewDTO();
		if(newEntity.getId() != null) {
			dto.setId(newEntity.getId());
		}
		dto.setTitle(newEntity.getTitle());
		dto.setContent(newEntity.getContent());
		dto.setShortDescription(newEntity.getShortDescription());
		dto.setThumbnail(newEntity.getThumbnail());
		dto.setCreatedDate(newEntity.getCreatedDate());
		dto.setCreatedBy(newEntity.getCreatedBy());
		dto.setModifiedDate(newEntity.getModifiedDate());
		dto.setModifiedBy(newEntity.getModifiedBy());
		
		return dto;
	}
	
	public NewEntity toEntity(NewDTO newDTO, NewEntity entity) {
		
		entity.setTitle(newDTO.getTitle());
		entity.setContent(newDTO.getContent());
		entity.setShortDescription(newDTO.getShortDescription());
		entity.setThumbnail(newDTO.getThumbnail());
		
		return entity;
	}
	
}
