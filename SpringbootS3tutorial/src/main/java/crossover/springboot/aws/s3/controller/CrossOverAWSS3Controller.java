package crossover.springboot.aws.s3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import crossover.springboot.aws.s3.dto.FileObjDto;
import crossover.springboot.aws.s3.services.CrossOverS3Service;

@RestController
@RequestMapping(value= "/s3")
public class CrossOverAWSS3Controller {

	@Autowired
	private CrossOverS3Service service;

	@PostMapping(value= "/upload")
	public ResponseEntity<String> uploadFile(@RequestPart(value= "file") final MultipartFile multipartFile,@RequestPart(value = "description") String fileDescription) {
		service.uploadFile(multipartFile,fileDescription);
		final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value= "/search")
	public ResponseEntity<String> searchFile(@RequestBody FileObjDto file) {
	    service.searchFile(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
