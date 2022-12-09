package com.certus.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.config.MvcConfig;
import com.certus.spring.helper.responseFileGeneric;
import com.certus.spring.models.ResponseFile;

@Component
public class FileService implements IFileGenericPr {
    
    @Autowired
	MvcConfig config;

    @Autowired
	IHelper helperfile;

    @Override
	public ResponseFile crearFileAPI(String fileBase64, String nombreFileExtension) {
		
		ResponseFile respuesta = new ResponseFile();
		responseFileGeneric rfg = new responseFileGeneric();
		
		Optional<Object> NewExtention = Optional.ofNullable(nombreFileExtension)
												.filter(e->e.contains("."))
												.map(ext -> ext.substring(nombreFileExtension.lastIndexOf(".") +1));
		
		String newName = UUID.randomUUID().toString() + "."+ NewExtention.get().toString();
		
		try {
			
			//byte[] fyleBytes = helperfile.procesarFile(fileBase64)
			byte[] bytesFile = null;
			rfg = helperfile.procesarFile(fileBase64);
			if (rfg.isEstado()) {
				bytesFile = rfg.getFileBytes();
				Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+ newName);
				Files.write(enlaceAGuardar, bytesFile);	
				respuesta.setEstado(true);
				respuesta.setNombreFile(newName);
			}else {
				respuesta.setEstado(false);
				respuesta.setNombreFile(newName);
				respuesta.setMensajeError("se produjo un error al procesar el archivo");
			}
			
			
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(newName);
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		
		
        return respuesta;
	}

    @Override
    public ResponseFile crearFile(MultipartFile fileGeneric) {
        
		ResponseFile respuesta = new ResponseFile();
		
		String NewExtention = StringUtils.getFilenameExtension(fileGeneric.getOriginalFilename());
		String newName = UUID.randomUUID().toString() + "."+ NewExtention;
		
		try {
			byte[] fyleBytes = fileGeneric.getBytes();
			Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+ newName);
			Files.write(enlaceAGuardar, fyleBytes);		
			
			respuesta.setEstado(true);
			respuesta.setNombreFile(newName);
		} catch (IOException e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileGeneric.getOriginalFilename());
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
        return respuesta;
    }

    @Override
    public ResponseFile eliminarFile(String fileName) {
        ResponseFile respuesta = new ResponseFile();
		
		Path enlaceAGuardado = Paths.get(config.pathImage()+"\\"+fileName);
		
		try {
			File fileEliminar = enlaceAGuardado.toFile();
			if(fileEliminar.exists()) {							
				fileEliminar.delete();
				
				respuesta.setEstado(true);
				respuesta.setNombreFile(fileName);
			}else {
				respuesta.setEstado(false);
				respuesta.setNombreFile(fileName);
				respuesta.setMensajeError("no se encontro");
			}
		} catch (Exception e) {
			respuesta.setEstado(false);
			respuesta.setNombreFile(fileName);
			respuesta.setMensajeError(e.getStackTrace().toString());
		}
		
		return respuesta;
    }


    
}
