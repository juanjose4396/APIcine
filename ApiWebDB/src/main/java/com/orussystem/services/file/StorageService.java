package com.orussystem.services.file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	Logger log = Logger.getLogger(this.getClass().getName());
	private Path rootLocation = null;

	public Boolean store(MultipartFile file, String path) throws Exception {
		try {

			// Se crea la ruta de la imagen
			File folder = new File(path);
			if (!folder.exists() && !folder.mkdir()) {
				throw new RuntimeException("Error: creando carpeta imagen");
			}

			// Crear la carpeta o recuper.
			Path rootLocation = Paths.get(path);
			Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
			this.rootLocation = rootLocation;
			return true;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("FAIL!");
		}
	}

	/**
	 * Metodo para eliminar las imagenes 
	 * @param file
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public Boolean removeFile(String path) throws Exception {

		try {

			// Se crea la ruta de la imagen
			File folder = new File(path);
			if (folder.exists() && !folder.delete()) {
				throw new RuntimeException("Error: Eliminado la imagen");
			}
			
			return true;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public String path() {
		String path = "";
		try {
			path = rootLocation.toRealPath().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
