package com.example.service;

import com.example.dto.requestDto.DocumentRequest;
import com.example.dto.responseDto.DocumentResponse;

import java.util.List;
public interface DocumentService {
    void createDocument(DocumentRequest documentRequest);

    void deleteDocument(Integer id);

    List<DocumentResponse> getAllDocument();

    DocumentResponse updateDocument(Integer id, DocumentRequest documentRequest);
}
