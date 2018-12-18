package app.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile  {

	private static final int MEMORY_THRESHOLD = 16000 * 1024;
	private static final long MAX_FILE_SIZE = 16000 * 1024;

	public static byte[] upload(HttpServletRequest request){
		byte[] fileData = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum file size
			upload.setSizeMax( MAX_FILE_SIZE );
			try{ 
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);
				// Process to get file items
				Iterator<FileItem> i = fileItems.iterator();
				while ( i.hasNext () ) {
					FileItem fi = (FileItem)i.next();
					if (!fi.isFormField ()){
						fileData = new byte[(int) fi.getSize()];
						InputStream fileStream = fi.getInputStream();
						fileStream.read(fileData);
						fileStream.close();
					}
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
				fileData = null;
			}
		}
		return fileData;
	}

}
