package com.mycomp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	/*
	 * ESTA VARIABLE TIENE LA UBICACION EN DONDE CARGARA LA IMAGEN DENTRO DEL
	 * PROYECTO EL NOMBRE LO GUARDA EN LA BD
	 */
	private String folder = "src/main/resources/static/imgs//";

	/* MultipartFile -> ES UN OBJETO QUE SERIALIZA NUESTRA IMG */
	public String saveImage(MultipartFile file) throws IOException {

		/* SI ES QUE EL OBJETO IMG ES CARGADO */
		if (!file.isEmpty()) {

			/* LO CONVERTIMOS A BYTES (0 & 1 PARA QUE PUEDA ENVIARSE DE UN LUGAR A OTRO */
			byte[] bytes = file.getBytes();

			/* ES LA URI DONDE SE ALMACENARA LA IMAGEN */
			Path path = Paths.get(folder + file.getOriginalFilename());

			/* PASAMOS LA RUTA Y LA IMG EN BYTES */
			Files.write(path, bytes);

			/* RETORNAMOS EL NOMBRE DE LA IMG QUE SE SUBIO */
			return file.getOriginalFilename();

		}

		/* CUANDO LA IMAGEN VIENE VACIA, ESTA IMG SE CARGA POR DEFECTO */
		return "default.jpg";
	}

	/*
	 * ESTE METODO ELIMINA UNA IMG CUANDO ELIMINAMOS UN PRODUCTO. OBTENEMOS EL
	 * NOMBRE DE LA IMG Y RECIBIMOS EL NOMBRE DE LA IMG EN EL PARAMETRO
	 */
	public void deleteImage(String nombre) {

		String ruta = "src/main/resources/static/imgs//";

		File file = new File(ruta + nombre);

		file.delete();

	}

}
