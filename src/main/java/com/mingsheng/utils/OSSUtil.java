package com.mingsheng.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
  * @Description: 阿里云OSS存储系统
 */
public class OSSUtil {
    
	private static final String oss_endpoint = "http://oss-cn-beijing.aliyuncs.com";
	private static final String oss_accessKeyId = "LTAI41mEPrDtnugs";
	private static final String oss_accessKeySecret ="T7QHQVUFoj35H7dDdEidqKn3xTJkWD";
	private static final String oss_bucket_img = "happyxiu"; // 图片访问路径 - 同理修改/assets/js/mymain.js
	private static final String oss_bucket_file = ""; //
	
    /**
     * 上传图片
     * @param file
     * @return 
     * <p>0：成功</p>
     * <p>1：异常</p>
     * <p>2：图片格式错误</p>
     * <p>3：没有图片后缀名</p>
     * <p>4：图片上传大小超过500KB限制</p>
     */
    public static Map<String, String> upload_img(MultipartFile file){
    	Map<String, String> respMap = new HashMap<>();
    	String bucket_img = oss_bucket_img;
    	String code = "0";
    	String fileName = "";
    	try {
    		long fileKB = getFileKB(file.getSize());
    		if(fileKB > 500){
    			code = "4"; //图片上传大小超过限制
    		}
    		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(file.getInputStream()));    
			IOUtils.closeQuietly(file.getInputStream());    
			String originalFilename = file.getOriginalFilename();
			if(originalFilename.lastIndexOf(".") <= 0){
				code = "3"; //没有图片后缀名
			}else{
				originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
				if(originalFilename.equals(".png") || originalFilename.equals(".PNG")|| originalFilename.equals(".jpg") || originalFilename.equals(".JPG") || originalFilename.equals(".jpeg") || originalFilename.equals(".JPEG") || originalFilename.equals(".gif") || originalFilename.equals(".GIF") || originalFilename.equals(".bmp") || originalFilename.equals(".BMP")){
					fileName = md5 + originalFilename;
				}else{
					code = "2"; //图片格式错误
				}
			}
    		if(code.equals("0")){
    			OSSClient ossClient = getOSSClient();
        		/*if (ossClient.doesBucketExist(bucket_img)) {
                } else {
                	ossClient.createBucket(bucket_img);
                }*/
        		ossClient.putObject(bucket_img, fileName, file.getInputStream());
        		ossClient.shutdown();
    		}
        } catch (OSSException oe) {
        	code = "1";
            oe.printStackTrace();
        } catch (ClientException ce) {
        	code = "1";
            ce.printStackTrace();
        } catch (Exception e) {
        	code = "1";
            e.printStackTrace();
        }
    	respMap.put("code", code);
    	respMap.put("fileName", fileName);
    	return respMap;
    }


	public static Map<String, String> baseToFile(String path){
		Map<String, String> respMap = new HashMap<>();
		String bucket_img = oss_bucket_img;
		String fileName="";
		try {
			String base64Img = path.replaceAll("data:image/jpeg;base64,", "");
			BASE64Decoder decoder = new BASE64Decoder();
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64Img);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			fileName = UUID.randomUUID()+".png";
			InputStream in = new ByteArrayInputStream(b);
			OSSClient ossClient = getOSSClient();
        		/*if (ossClient.doesBucketExist(bucket_img)) {
                } else {
                	ossClient.createBucket(bucket_img);
                }*/
			ossClient.putObject(bucket_img, fileName, in);
			ossClient.shutdown();
		}catch (Exception e){

		}
		respMap.put("code", "0");
		respMap.put("fileName", fileName);
		return respMap;
	}

    /**
     * 上传图片二维码专用
     * @param inputStream
     * @return
     */
    public static Map<String, String> upload_qr(InputStream inputStream){
    	Map<String, String> respMap = new HashMap<>();
    	String bucket_img = oss_bucket_img;
    	String code = "0";
    	String fileName = "";
    	try {  
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		byte[] buffer = new byte[1024];
    		int len;
    		while ((len = inputStream.read(buffer)) > -1 ) {
    			baos.write(buffer, 0, len);
    		}
    		baos.flush();	           
    		InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
    		InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
    		
    		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(stream1));    
			IOUtils.closeQuietly(stream1);  
    		
    		fileName = md5+".jpg";
    		OSSClient ossClient = getOSSClient();
    		ossClient.putObject(bucket_img, fileName, stream2);
    		ossClient.shutdown();
        } catch (OSSException oe) {
        	code = "1";
            oe.printStackTrace();
        } catch (ClientException ce) {
        	code = "1";
            ce.printStackTrace();
        } catch (Exception e) {
        	code = "1";
            e.printStackTrace();
        }
    	respMap.put("code", code);
    	respMap.put("fileName", fileName);
    	return respMap;
    }

	public static Map<String, String> upload_txImg(InputStream inputStream){
		Map<String, String> respMap = new HashMap<>();
		String bucket_img = oss_bucket_img;
		String code = "0";
		String fileName = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = inputStream.read(buffer)) > -1 ) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
			InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());

			String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(stream1));
			IOUtils.closeQuietly(stream1);

			fileName = md5+".jpg";
			OSSClient ossClient = getOSSClient();
			ossClient.putObject(bucket_img, fileName, stream2);
			ossClient.shutdown();
		} catch (OSSException oe) {
			code = "1";
			oe.printStackTrace();
		} catch (ClientException ce) {
			code = "1";
			ce.printStackTrace();
		} catch (Exception e) {
			code = "1";
			e.printStackTrace();
		}
		respMap.put("code", code);
		respMap.put("fileName", fileName);
		return respMap;
	}


    /**
     * 上传文件
     * @param file
     * @return 
     * <p>0：成功</p>
     * <p>1：异常</p>
     */
    public static Map<String, String> upload_file(CommonsMultipartFile file){
    	Map<String, String> respMap = new HashMap<>();
    	String bucket_file = oss_bucket_file;
    	String code = "0";
    	String fileName = "";
    	try {
    		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(file.getInputStream()));    
			IOUtils.closeQuietly(file.getInputStream());   
			String originalFilename = file.getOriginalFilename();
			originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
			fileName = md5 + originalFilename;
			OSSClient ossClient = getOSSClient();
    		/*if (ossClient.doesBucketExist(bucket_file)) {
            } else {
            	ossClient.createBucket(bucket_file);
            }*/
    		ossClient.putObject(bucket_file, fileName, file.getInputStream());
    		ossClient.shutdown();
        } catch (OSSException oe) {
        	code = "1";
            oe.printStackTrace();
        } catch (ClientException ce) {
        	code = "1";
            ce.printStackTrace();
        } catch (Exception e) {
        	code = "1";
            e.printStackTrace();
        }
    	respMap.put("code", code);
    	respMap.put("fileName", fileName);
    	return respMap;
    }
    
    /**
     * 获取 OSSClient 对象实例
     * @return
     */
    public static OSSClient getOSSClient(){
    	try {
			String endpoint = oss_endpoint;
			String accessKeyId = oss_accessKeyId;
			String accessKeySecret = oss_accessKeySecret;
			return new OSSClient(endpoint, accessKeyId, accessKeySecret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 文件流大小
     * @param byteFile
     * @return
     */
    private static long getFileKB(long byteFile){  
        if(byteFile==0)  
           return 0;  
        long kb=1024;  
        return byteFile/kb;  
    }  
    
}
