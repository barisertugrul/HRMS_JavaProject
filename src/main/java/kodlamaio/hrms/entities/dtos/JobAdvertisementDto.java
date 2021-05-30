package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

	private int advertisementId;
	private String companyName;
	private String positionName;
	private short numberOfOpenPositions;
	private LocalDate createdDate;
	private LocalDate expirationDate;
}
