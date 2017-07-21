package peach.anonymiser.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import peach.anonymiser.bean.CODS;

@RestController
public class AnonymiserRestController {
	
	//INFO curl http://localhost:8080/cods\?description\=dos
	@CrossOrigin(origins= "http://127.0.0.1:9000")
	@RequestMapping("/cods")
	public CODS getCODSdataREST(@RequestParam(value="description", defaultValue="World")String description) {
		
		CODS data = new CODS();
		data.setId(1);
		data.setContent(description);		
		return data;
		
	}	
	
	
	
	private static String UPLOADED_FOLDER = "/Users/ytachi/training/restupload/";
	
	//INFO curl -F file=@"/Users/ytachi/swprojects/PEACH-anonymiser/test" http://localhost:8080/api/upload/
	@CrossOrigin(origins= "http://127.0.0.1:9000")
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }

        try {
            saveUploadedFiles(Arrays.asList(uploadfile));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }
	
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
    		System.out.println("Solution");
        for (MultipartFile file : files) {
        		System.out.println("first file");
			System.out.println(file);

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }
	
	
	
	
	
	
	


}