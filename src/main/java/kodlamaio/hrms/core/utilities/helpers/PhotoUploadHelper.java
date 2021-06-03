package kodlamaio.hrms.core.utilities.helpers;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface PhotoUploadHelper {
	DataResult<Map<String, String>> upload(MultipartFile file);
}
