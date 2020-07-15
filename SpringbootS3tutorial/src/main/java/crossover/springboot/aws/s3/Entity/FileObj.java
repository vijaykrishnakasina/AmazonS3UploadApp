package crossover.springboot.aws.s3.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "file_data_t")
@EntityListeners(AuditingEntityListener.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "file_description", nullable = false)
    @NotNull
    public String fileDescription;
    
    @Column(name = "file_type", nullable = false)
    @NotNull
    public String fileType;
    
    @Column(name = "file_size", nullable = false)
    @NotNull
    public String fileSize;
    
    @Column(name = "resource_url",nullable = false)
    @NotNull
    public String resourceUrl;
    
    
   
}
