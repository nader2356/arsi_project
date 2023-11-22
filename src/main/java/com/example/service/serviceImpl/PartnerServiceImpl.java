package com.example.service.serviceImpl;

import com.example.dto.requestDto.PartnerRequest;

import com.example.dto.responseDto.PartnerResponse;

import com.example.entity.Partner;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.repository.PartnerRepository;
import com.example.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    @Override
    public void addPartner(PartnerRequest partnerRequest) {
        if (partnerRepository.existsByName(partnerRequest.getName())){
            throw new ConflictException(String.format("this name ([%s]) is already exist ",partnerRequest.getName()));
        }
        Partner partner = Partner.builder()
                .name(partnerRequest.getName())
                .description(partnerRequest.getDescription())
                .address(partnerRequest.getAddress())
                .contact(partnerRequest.getContact())
                .type(partnerRequest.getType())
                .image(partnerRequest.getImage())
                .build();

        partnerRepository.save(partner);
    }

    @Override
    public List<PartnerResponse> getAllPartner() {
        List<Partner> partners = partnerRepository.findAll();
        List<PartnerResponse> partnerResponses = new ArrayList<>();

        for (Partner partner: partners) {
            PartnerResponse partnerResponse = PartnerResponse.makePartner(partner);
            partnerResponses.add(partnerResponse);
        }
        return partnerResponses;
    }

    @Override
    public PartnerResponse getPartnerByID(Long id) {
        Partner partner = partnerRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("this id[%s] is not exist",id)));

        return PartnerResponse.makePartner(partner);

    }

    @Override
    public void updatePartner(Long id, PartnerRequest partnerRequest) {

        Partner partner = partnerRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("this id[%s] is not exist",id)));
        if(!partner.getName().equals(partnerRequest.getName()) && partnerRepository.existsByName(partnerRequest.getName())){
            throw  new ConflictException(String.format("this name is already exist ( [%s] ) ",partnerRequest.getName()));
        }

        partner.setName(partnerRequest.getName());
        partner.setDescription(partnerRequest.getDescription());
        partner.setAddress(partnerRequest.getAddress());
        partner.setContact(partnerRequest.getContact());
        partner.setType(partnerRequest.getType());
        partner.setImage(partnerRequest.getImage());

        partnerRepository.save(partner);

    }

    @Override
    public void deletePartner(Long id) {
        if (!partnerRepository.existsById(id)){
            throw new NotFoundException(String.format("this id[%s] is not exist",id));
        }
        partnerRepository.deleteById(id);
    }
}