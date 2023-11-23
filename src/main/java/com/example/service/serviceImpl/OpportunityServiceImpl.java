package com.example.service.serviceImpl;

import com.example.config.UtilsConfiguration;
import com.example.dto.requestDto.OpportunityRequest;
import com.example.dto.responseDto.OpportunityResponse;
import com.example.entity.Opportunity;
import com.example.exception.NotFoundException;
import com.example.repository.OpportunityRepository;
import com.example.service.OpportunityService;
import com.example.util.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OpportunityServiceImpl implements OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    private FileStorageService fileStorageService;
    @Override
    public void createOpportunity(OpportunityRequest request) {

        Opportunity opportunity = Opportunity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .company(request.getCompany())
                .type(request.getType()).build();
        opportunityRepository.save(opportunity);

    }

    @Override
    public OpportunityResponse getOpportunityById(Long id) {
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow();
        return OpportunityResponse.makeOpportunity(opportunity);
    }

    @Override
    public List<OpportunityResponse> getAllOpportunity() {

        List<Opportunity> opportunities = opportunityRepository.findAll();
        List<OpportunityResponse> opportunityResponses = new ArrayList<>();
        for (Opportunity opportunity: opportunities) {
            OpportunityResponse opportunityResponse = OpportunityResponse.makeOpportunity(opportunity);
            opportunityResponses.add(opportunityResponse);
        }

        return opportunityResponses;
    }

    @Override
    public void updateOpportunity(OpportunityRequest request,Long id) {

        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow();

        opportunity.setTitle(request.getTitle());
        opportunity.setDescription(request.getDescription());
        opportunity.setCompany(request.getCompany());
        opportunity.setType(request.getType());

        opportunityRepository.save(opportunity);


    }

    @Override
    public void deleteOpportunity(Long id) {

        if (opportunityRepository.existsById(id)){
            opportunityRepository.deleteById(id);
        }else {
            throw new NotFoundException("opportunity is not exist");
        }

    }

    @Override
    public void uploadImage(MultipartFile file, Long id) {
       Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("opportunity is not exist"));

        if (UtilsConfiguration.isImage(Objects.requireNonNull(file.getContentType()))){

            fileStorageService.storeFile(file, "OPPORTUNITY_IMG");
            opportunity.setImage(file.getOriginalFilename());
            opportunityRepository.save(opportunity);

        }else{
            throw new RuntimeException("mahiyech image****************");
        }
    }

    @Override
    public Resource serveImage(String fileName) {
        fileName = "OPPORTUNITY_IMG/"+fileName;
        return fileStorageService.loadFileAsResource(fileName);
    }
}