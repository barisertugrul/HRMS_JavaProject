package kodlamaio.hrms.business.adapters.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.helpers.PhotoUploadHelper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;

@Service
public class CloudinaryAdapterService implements PhotoUploadHelper {

    private Cloudinary cloudinary;
    
    private Map<String, String> valuesMap = new HashMap<>();

    	public CloudinaryAdapterService() {
    		valuesMap.put("cloud_name","Input your cloud name" );
    		valuesMap.put("api_key", "Input your Cloudinary api key" );
    		valuesMap.put("api_secret","Input your Cloudinary secret key" );
    		cloudinary = new Cloudinary(valuesMap);
    	}
    	
    	public DataResult<Map<String, String>> upload(MultipartFile multipartFile) {
    		File file;
			try {
				file = convert(multipartFile);
	    		Map<String, String> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
	    		file.delete();
	    		return new SuccessDataResult<Map<String, String>>(result);
			} catch (IOException e) {
				e.printStackTrace();
				return new ErrorDataResult<Map<String, String>>("Dosya yüklenemedi");
			}
    	}
    	
    	public DataResult<Map> delete (String id) throws IOException {
    		Map result = cloudinary.uploader().destroy(id,ObjectUtils.emptyMap());
    		return new SuccessDataResult<Map>(result);
    	}
    	
    	
    	private File convert(MultipartFile multipartFile) throws IOException {
    		File file = new File(multipartFile.getOriginalFilename());
    		FileOutputStream stream = new FileOutputStream(file);
    		stream.write(multipartFile.getBytes());
    		stream.close();
    		
    		return file;
    	}

}
