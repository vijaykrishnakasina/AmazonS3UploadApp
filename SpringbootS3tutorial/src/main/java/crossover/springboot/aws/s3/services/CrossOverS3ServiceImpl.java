package crossover.springboot.aws.s3.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import crossover.springboot.aws.s3.Entity.FileObj;
import crossover.springboot.aws.s3.dto.FileObjDto;
import crossover.springboot.aws.s3.repository.FileRepository;

@Service
public class CrossOverS3ServiceImpl implements CrossOverS3Service {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrossOverS3ServiceImpl.class);

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private FileRepository filerepo;
	
	@Value("${aws.s3.bucket}")
	private String bucketName;
	
	String uniqueFileName;

	@Override
	// @Async annotation ensures that the method is executed in a different background thread 
	// but not consume the main thread.
	@Async
	public void uploadFile(final MultipartFile multipartFile, String fileDescription) {
		LOGGER.info("File upload in progress.");
		try {
			final File file = convertMultiPartFileToFile(multipartFile);
			uploadFileToS3Bucket(bucketName, file);
			LOGGER.info("File upload is completed.");
			String url = String.valueOf(amazonS3.getUrl(bucketName, uniqueFileName));
			LOGGER.info("The Url is : {}",url);
			FileObj ImageObject = FileObj.builder().fileDescription(fileDescription).fileType(multipartFile.getContentType().split("/")[1])
					.fileSize(String.valueOf((multipartFile.getSize())/1024)).resourceUrl(url).build();
			filerepo.save(ImageObject);
			LOGGER.info("File details are saved in the database..");
			file.delete();	
		} catch (AmazonServiceException ex) {
			LOGGER.info("File upload is failed.");
			LOGGER.error("Error= {} while uploading file.", ex.getMessage());
		}
	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	private PutObjectResult uploadFileToS3Bucket(final String bucketName, final File file) {
		uniqueFileName = LocalDateTime.now() + "_" + file.getName();
		LOGGER.info("Uploading file with name= " + uniqueFileName);
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
		return amazonS3.putObject(putObjectRequest);
	}
	
	public List<FileObj> searchFile(FileObjDto file){
		
		Example<FileObj> example = Example.of(FileObj.builder().fileDescription(file.getFileDescription()).fileSize(file.getFileSize())
				.fileType(file.getFileType()).build());
		List<FileObj> list = filerepo.findAll(example);
		return list;
		//filerepo.findOne(new FileObj());
	}
}
