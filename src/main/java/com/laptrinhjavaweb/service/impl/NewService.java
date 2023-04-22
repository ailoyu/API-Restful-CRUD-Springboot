package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;


@Service
public class NewService implements INewService{
	
	@Autowired
	private NewRepository newRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;

	@Override
	public NewDTO insert(NewDTO newDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		
		NewEntity newEntity = newConverter.toEntity(newDTO);
		
		newEntity.setCategory(categoryEntity);
		
		
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	@Override
	public NewDTO update(NewDTO newDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
		// cập nhật từ dto ms nhập -> newEntity
		NewEntity newEntity = newConverter.toEntity(newDTO, oldNewEntity); 
		newEntity.setCategory(categoryEntity);
		
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		if(newDTO.getId() != null) { // Cập nhật
			NewEntity oldNewEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, oldNewEntity); // cập nhật từ dto ms nhập -> newEntity
		}else {
			newEntity = newConverter.toEntity(newDTO);
		}
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		newEntity.setCategory(categoryEntity);
		
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			newRepository.delete(id);
		}
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> results = new ArrayList<NewDTO>();
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		for (NewEntity item : entities) {
			NewDTO newDTO = newConverter.toDTO(item);
			results.add(newDTO);
		}
		
		return results;
	}

	@Override
	public int totalItem() {
		return (int) newRepository.count();
	}
	
	
}