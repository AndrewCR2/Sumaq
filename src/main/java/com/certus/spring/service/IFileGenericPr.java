package com.certus.spring.service;

import org.springframework.web.multipart.MultipartFile;
import com.certus.spring.models.ResponseFile;

public interface IFileGenericPr {
    public ResponseFile crearFile(MultipartFile fileGeneric);
	public ResponseFile crearFileAPI(String fileBase64,String nombreFileExtension);
	public ResponseFile eliminarFile(String fileName);
}
