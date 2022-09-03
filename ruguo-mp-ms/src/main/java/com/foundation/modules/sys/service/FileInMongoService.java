package com.foundation.modules.sys.service;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.upload.UploadException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Service(FileInMongoService.SERVICE_ID)
public class FileInMongoService {
	// 用于定义该service的spring bean name
	public final static String SERVICE_ID = "fileInMongoService";

	@Autowired
	private GridFsTemplate gridFsTemplate;

	private final static String FILE_ORGN = "_orgn";
	// private final static String FILE_SMALL = "_weix";

	private final static String META_FILE_NAME = "fileName";
	private final static String META_FILE_OWNER_ID = "ownerId";
	private final static String META_FILE_OWNER_ID_TEMP = FileInMongoService.class.getName() + ".temp";

	// 图片文件上传，会自动生成720*540的图片
	public final static String ACT_TYPE_IMAGE = "jpg";
	// 普通文件上传
	public final static String ACT_TYPE_FILE = "file";

	/**
	 * 文件保存。
	 * 
	 * @param actType
	 * @param file
	 * @return 文件ID
	 * @throws Exception
	 */
	public String storeFile(String actType, MultipartFile file) throws UploadException {

		// String fileId = IDHelper.genTimeseqId();
		String fileId = IdGen.uuid();
		try {
//			Pattern reg = Pattern.compile("jpg|png|jpeg|gif$");
//			Matcher matcher = reg.matcher(actType);

//			if (matcher.find()) {
//				saveImgFile(fileId, file);
//
//			} else {
				saveMultipartFile(fileId, file);
//			}
			// else if (ACT_TYPE_FILE.equals(actType)) {
			// saveMultipartFile(fileId, file);
			// }
			// if(ACT_TYPE_IMAGE.equals(actType)) {
			// saveImgFile(fileId, file);
			// }

		} catch (Exception e) {
			e.printStackTrace();
			throw new UploadException("上传文件失败！", e);
		}
		return fileId;
	}

	// 保存原始文件
	private void saveMultipartFile(String fileId, MultipartFile file) throws IOException {
		storeFile(fileId, file.getOriginalFilename(), file.getContentType(), file.getInputStream());
	}

	/**
	 * 保存OWNER的文件。
	 * 
	 * @param ownerId
	 * @param actType
	 * @param fileIds
	 *            原始文件ID（不包含[_orgn]和[_weix]的文件ID）
	 */
	/*
	 * public void saveFile(String ownerId, String actType, List<String>
	 * fileIds) { List<String> newFileIds = null;
	 * if(ACT_TYPE_IMAGE.equals(actType)) { newFileIds = new
	 * ArrayList<String>(); for(String fileId : fileIds) { newFileIds.add(fileId
	 * + FILE_ORGN); newFileIds.add(fileId + FILE_SMALL); } } else { newFileIds
	 * = fileIds; } saveFile(ownerId, newFileIds); }
	 */
	/**
	 * 保存OWNER的文件。
	 * 
	 * @param ownerId
	 * @param fileIds
	 *            完整的文件ID
	 */
	/*
	 * public void saveFile(String ownerId, List<String> fileIds) { Criteria
	 * whereOldFiles =
	 * GridFsCriteria.whereMetaData(META_FILE_OWNER_ID).is(ownerId);
	 * List<GridFSDBFile> oldFiles = gridFsOperations.find(new
	 * Query(whereOldFiles)); if (oldFiles == null) { oldFiles = new
	 * ArrayList<GridFSDBFile>(); }
	 * 
	 * Map<Object, GridFSDBFile> oldFilesMap = new HashMap<Object,
	 * GridFSDBFile>(); for(GridFSDBFile f : oldFiles) {
	 * oldFilesMap.put(f.getId(), f); }
	 * 
	 * Criteria whereNewFiles = GridFsCriteria.whereFilename().in(fileIds);
	 * List<GridFSDBFile> newFiles = gridFsOperations.find(new
	 * Query(whereNewFiles));
	 * 
	 * if (newFiles == null) { newFiles = new ArrayList<GridFSDBFile>(); }
	 * 
	 * GridFSDBFile oldFile; DBObject newMeta; for(GridFSDBFile newFile :
	 * newFiles) { newMeta = newFile.getMetaData(); oldFile =
	 * oldFilesMap.get(newFile.getId());
	 * 
	 * if (oldFile == null) { newMeta.put(META_FILE_OWNER_ID, ownerId);
	 * newFile.save(); } else { oldFilesMap.remove(newFile.getId()); } }
	 * 
	 * List<String> deleteOldFiles = new ArrayList<String>(); for(GridFSDBFile f
	 * : oldFilesMap.values()) { deleteOldFiles.add(f.getFilename()); } Criteria
	 * whereId = GridFsCriteria.whereFilename().in(deleteOldFiles);
	 * gridFsOperations.delete(new Query(whereId)); }
	 */

	/**
	 * 获取文件。
	 * 
	 * @param fileId
	 * @return 文件对象
	 * @throws Exception
	 */
	public GridFSDBFile getFile(String actType, String fileId) throws Exception {
		return getFile(fileId);
	}

	/**
	 * 删除OWNER拥有的所有文件。
	 * 
	 * @param ownerId
	 */
	public void deleteAll(String ownerId) {
		Criteria whereOldFiles = GridFsCriteria.whereMetaData(META_FILE_OWNER_ID).is(ownerId);
		List<GridFSDBFile> files = gridFsTemplate.find(new Query(whereOldFiles));
		List<String> fileNames = new ArrayList<String>();
		for (GridFSDBFile f : files) {
			fileNames.add(f.getFilename());
		}
		Criteria whereId = GridFsCriteria.whereFilename().in(fileNames);
		gridFsTemplate.delete(new Query(whereId));
	}

	/**
	 * 删除文件。
	 * 
	 * @param actType
	 * @param fileId
	 */
	public void delete(String actType, String fileId) throws UploadException {

		try {
			Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
			gridFsTemplate.delete(new Query(whereId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new UploadException("删除文件失败！");
		}

		// if(ACT_TYPE_IMAGE.equals(actType)) {
		// /*Criteria whereId = GridFsCriteria.whereFilename().is(fileId +
		// FILE_ORGN);
		// gridFsOperations.delete(new Query(whereId));
		// */
		// Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
		// gridFsOperations.delete(new Query(whereId));
		//
		// } else if (ACT_TYPE_FILE.equals(actType)) {
		// Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
		// gridFsOperations.delete(new Query(whereId));
		// }
	}

	/**
	 * 删除文件。
	 * 
	 * @param actType
	 * @param fileId
	 */
	public void delete(String fileId) throws UploadException{

		try {
			Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
			gridFsTemplate.delete(new Query(whereId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new UploadException("删除文件失败！");
		}

		// if(ACT_TYPE_IMAGE.equals(actType)) {
		// /*Criteria whereId = GridFsCriteria.whereFilename().is(fileId +
		// FILE_ORGN);
		// gridFsOperations.delete(new Query(whereId));
		// */
		// Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
		// gridFsOperations.delete(new Query(whereId));
		//
		// } else if (ACT_TYPE_FILE.equals(actType)) {
		// Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
		// gridFsOperations.delete(new Query(whereId));
		// }
	}

	// ---------------------------------------------------------------------------------
	// 私有函数
	// ---------------------------------------------------------------------------------

	// 存储图片文件，会自动生成小文件。
	private void saveImgFile(String fileId, MultipartFile file) throws Exception {
		String contentType = file.getContentType();
		String orgnFileName = file.getOriginalFilename();
		String tmpDir = System.getProperty("java.io.tmpdir");
		int dotIdx = orgnFileName.lastIndexOf(".");
		String imgExt = orgnFileName.substring(dotIdx);

		String orgnFileId = fileId + FILE_ORGN;
		String smallFileId = fileId;

		String orgnImgFileName = orgnFileId + imgExt;
		String smallImgFileName = smallFileId + imgExt;

		File orgnImgFile = new File(tmpDir, orgnImgFileName);
		File smallImgFile = new File(tmpDir, smallImgFileName);

		file.transferTo(orgnImgFile);
		genSmallImg(orgnImgFile, smallImgFile, 1024, 768);

		// 如果小文件不为空则保存小文件
		if (smallImgFile != null) {
			FileInputStream smallFileIns = null;
			try {
				smallFileIns = new FileInputStream(smallImgFile);
				this.storeFile(smallFileId, smallImgFileName, contentType, smallFileIns);

				// 删除小文件
				smallFileIns.close();
				FileUtils.forceDeleteOnExit(smallImgFile);

				// 删除原文件
				FileUtils.forceDeleteOnExit(orgnImgFile);
			} catch (Exception e) {
			} finally {
				if (smallFileIns != null) {
					IOUtils.closeQuietly(smallFileIns);
				}
			}
		}
		// 保存原文件
		/*
		 * else{ FileInputStream orgnFileIns = null; try{ orgnFileIns = new
		 * FileInputStream(orgnImgFile); this.storeFile(orgnFileId,
		 * orgnFileName, contentType, orgnFileIns);
		 * 
		 * FileUtils.forceDeleteOnExit(orgnImgFile); }catch(Exception e){}
		 * finally{ if(orgnFileIns!=null){ IOUtils.closeQuietly(orgnFileIns); }
		 * } }
		 */

	}

	// 生成小文件
	private void genSmallImg(File srcImgFile, File destImgFile, int width, int height) throws IOException {
		InputStream srcImgIns = new FileInputStream(srcImgFile);
		BufferedImage bsrc = ImageIO.read(srcImgIns);
		int srcWidth = bsrc.getWidth();
		int srcHeight = bsrc.getHeight();

		// 原文件的尺度不大于目的大小，不做变换
		if (srcWidth <= width && srcHeight <= height) {
			srcImgIns.close();
			FileUtils.copyFile(srcImgFile, destImgFile);
			return;
		}

		AffineTransform transform = new AffineTransform();
		double sx = (double) width / bsrc.getWidth();
		double sy = (double) height / bsrc.getHeight();
		transform.setToScale(sx, sy);// 设置图像转换的比例
		AffineTransformOp ato = new AffineTransformOp(transform, null);
		BufferedImage bsmall = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		ato.filter(bsrc, bsmall);
		String fileName = destImgFile.getName();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		ImageIO.write(bsmall, type, destImgFile);
		srcImgIns.close();
	}

	// 通过mongodb来保存文件
	private void storeFile(String fileId, String orgnFileName, String contentType, InputStream fileIns) {
		DBObject metaData = new BasicDBObject();
		metaData.put(META_FILE_NAME, orgnFileName);
		metaData.put(META_FILE_OWNER_ID, META_FILE_OWNER_ID_TEMP);
		gridFsTemplate.store(fileIns, fileId, contentType, metaData);
	}

	// 通过mongodb 获取文件对象。
	public GridFSDBFile getFile(String fileId) throws UploadException {
		Criteria whereId = GridFsCriteria.whereFilename().is(fileId);
		GridFSDBFile gfsDbFile = gridFsTemplate.findOne(new Query(whereId));
		if (gfsDbFile == null) {
			throw new UploadException("文件不存在！");
		}
		return gfsDbFile;
	}
}
