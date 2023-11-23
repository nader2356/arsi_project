package com.example.service;

import com.example.dto.requestDto.PartnerRequest;
import com.example.dto.responseDto.PartnerResponse;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PartnerService {

    void addPartner(PartnerRequest partnerRequest);
    List<PartnerResponse> getAllPartner();
    PartnerResponse getPartnerByID(Long id);
    void updatePartner(Long id,PartnerRequest partnerRequest);
    void deletePartner(Long id);
    void uploadImage(MultipartFile file, Long id);
    Resource serveImage(String fileName);

}