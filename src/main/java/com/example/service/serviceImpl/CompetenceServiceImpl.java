package com.example.service.serviceImpl;


import com.example.dto.responseDto.CategoryResponse;
import com.example.dto.responseDto.CompetenceResponse;
import com.example.dto.requestDto.CompetenceRequest;
import com.example.entity.Category;
import com.example.entity.Competence;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.repository.CategoryRepository;
import com.example.repository.CompetenceRepository;
import com.example.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {

	private final CompetenceRepository competenceRepository; 
	private final CategoryRepository categoryRepository;

    @Override
    public void addCompetence(CompetenceRequest competenceRequest) {
    	 if(competenceRepository.existsByName(competenceRequest.getName())){
             throw new ConflictException(String.format("this name ([%s]) is already exist ",competenceRequest.getName()));
         }
         Category category = categoryRepository.findById(competenceRequest.getCategoryId()).orElseThrow(
                 ()-> new NotFoundException(String.format("this CategoryId [%s] is not exist ",competenceRequest.getCategoryId())));
         Competence competence = Competence.builder()
                 .name(competenceRequest.getName())
                 .description(competenceRequest.getDescription())
                 .category(category)
                 .build();
         competenceRepository.save(competence);
    }

    @Override
    public List<CompetenceResponse> getAllCompetence() {
        List<Competence> competences = competenceRepository.findAll();
        List<CompetenceResponse> competenceResponses = new ArrayList<>();
        for (Competence competence:competences) {
            CompetenceResponse competenceResponse = CompetenceResponse.makeCompetence(competence);
            competenceResponses.add(competenceResponse);
        }
        return competenceResponses;
    }

    @Override
    public CompetenceResponse getCompetenceById(Long id) {

        Competence competence = competenceRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this CategoryId [%s] is not exist ",id)));

        return CompetenceResponse.makeCompetence(competence);
    }

    @Override
    public void deleteCompetence(Long id) {
    	  if (!competenceRepository.existsById(id)){
              throw new NotFoundException(String.format("this id[%s] is not exist",id));
          }
          competenceRepository.deleteById(id);
    }

    @Override
    public void updateCompetence(Long id, CompetenceRequest competenceRequest) {

        Competence competence = competenceRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("this id[%s] is not exist",id)));
        if(!competence.getName().equals(competenceRequest.getName()) && competenceRepository.existsByName(competenceRequest.getName())){
            throw  new ConflictException(String.format("this name is already exist ( [%s] ) ",competenceRequest.getName()));
        }
        Category category = categoryRepository.findById(competenceRequest.getCategoryId()).orElseThrow(
                ()->new NotFoundException(String.format("this Category with id[%s] is not exist",id)));

        competence.setName(competenceRequest.getName());
        competence.setDescription(competenceRequest.getDescription());
        competence.setCategory(category);

        competenceRepository.save(competence);
    }
}