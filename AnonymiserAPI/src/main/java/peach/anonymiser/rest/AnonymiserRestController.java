package peach.anonymiser.rest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import peach.anonymiser.bean.DATA_SET;
import peach.anonymiser.service.impl.COSDAnonymiser;
import peach.anonymiser.service.impl.CWTAnonymiser;

@RestController
public class AnonymiserRestController {
	private static String UPLOADED_FOLDER = "/Users/ytachi/training/restupload/";

	// INFO curl -F file=@"/Users/ytachi/swprojects/PEACH-anonymiser/test" http://localhost:8080/api/upload/
	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("cancerDataType") String cancerDataType) {
		System.out.println(cancerDataType);
		DATA_SET cancerType = DATA_SET.valueOf(cancerDataType);
		
		if (uploadfile.isEmpty()) {
			return new ResponseEntity("Please select a file!", HttpStatus.OK);
		}
		try {
			String fileNameToBeAnonymised = saveUploadedFiles(Arrays.asList(uploadfile));
			String anonymisedFileName = getAnonymisedFileName(fileNameToBeAnonymised);
			
			String input = UPLOADED_FOLDER + fileNameToBeAnonymised;
			String output = UPLOADED_FOLDER + anonymisedFileName;
			
			switch (cancerType) {
				case CWT:
					System.out.println("cwt");
					CWTAnonymiser cwt = new CWTAnonymiser(input, output);
					cwt.anonymise();
					break;
				case COSD:
					System.out.println("cods");
					COSDAnonymiser cosd = new COSDAnonymiser(input, output);
					cosd.anonymise();
					break;
				case SACT:
					System.out.println("sact");
					break;
			}
			return new ResponseEntity(anonymisedFileName, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@RequestMapping(path = "/api/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadAnonymisedFile(
			@RequestParam(value = "filename", defaultValue = "error") String filename) {
		try {
			File file = new File(UPLOADED_FOLDER + filename);
			Path path = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok().headers(headers).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		} catch (Exception e) {
			return new ResponseEntity("File cannot be downloaded.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private boolean validFormatFile(MultipartFile file) {
		if(file == null) {
			return false;
		}
		if (file.isEmpty()) {
			return false;
		}
		if (!file.getOriginalFilename().matches(".*.csv")) {
			return false;
		}
		return true;
	}

	private String getFileNameToBeAnonymised(String fileName) {
		StringBuilder anonymisedFileName = new StringBuilder();
		anonymisedFileName.append("_anony_");
		anonymisedFileName.append(fileName);
		return anonymisedFileName.toString();
	}
	private String getAnonymisedFileName(String fileName) {
		StringBuilder anonymisedFileName = new StringBuilder();
		anonymisedFileName.append("_out_");
		anonymisedFileName.append(fileName);
		return anonymisedFileName.toString();
	}

	private String saveUploadedFiles(List<MultipartFile> files) throws IllegalStateException {
		for (MultipartFile file : files) {
			if (!validFormatFile(file)) {
				throw new IllegalStateException("The file does not match a Health file format.");
			}

			try {
				byte[] bytes = file.getBytes();
				String anonymisedFileName = getFileNameToBeAnonymised(file.getOriginalFilename());
				Path path = Paths.get(UPLOADED_FOLDER + anonymisedFileName);
				Files.write(path, bytes);
				return anonymisedFileName;
			} catch (Exception e) {
				throw new IllegalStateException("Fail to obtain the file to be anonymised.");
			}
		}
		throw new IllegalStateException("File does not exits.");
	}

}
