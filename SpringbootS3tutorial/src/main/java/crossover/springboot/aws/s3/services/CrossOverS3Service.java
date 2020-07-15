package crossover.springboot.aws.s3.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import crossover.springboot.aws.s3.Entity.FileObj;
import crossover.springboot.aws.s3.dto.FileObjDto;

public interface CrossOverS3Service {

	void uploadFile(MultipartFile multipartFile,String fileDescription);
	List<FileObj> searchFile(FileObjDto file);
}
