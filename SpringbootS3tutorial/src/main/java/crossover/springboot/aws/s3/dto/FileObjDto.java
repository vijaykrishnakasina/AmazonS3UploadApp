package crossover.springboot.aws.s3.dto;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileObjDto {

    public String fileDescription;
    
    public String fileType;
    
    public String fileSize;
    
    public String resourceUrl;
    
}