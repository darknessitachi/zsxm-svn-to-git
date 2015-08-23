package com.netwander.explib.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;

@Service
public class FileService  extends BaseService{
	@Autowired
	private LobHandler lobHandler;
	private Integer FID_KEY = 1;
	private String FILE_SQL = "insert into XT_FILE(FID,FNAME, FTYPE, FSIZE, INTIME, FDATA) values(?,?,?,?,getdate(),?)";
	
	/***
	 * 保存附件
	 * @param fid
	 * @param files
	 * @param filenames
	 * @param filetypes
	 * @return
	 */
	public int fileUpload(Integer fid,File[] files,String[] filenames,String[] filetypes){
		String sql = "";
		try{
			if(fid != null && !fid.equals("")){
				sql = " delete from XT_FILE where fid="+fid;
				this.getSimpleJdbcTemplate().update(sql);
			}
			for(int i=0;i<files.length;i++){
				final File file = files[i];
				final String filename = filenames[i];
				final String filetype = filetypes[i];
				FID_KEY = Integer.parseInt(String.valueOf(getMaxKey("XT_FILE")));
				this.jdbcTemplate.execute(FILE_SQL, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
					protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
						ps.setInt(1, FID_KEY);
						ps.setString(2, filename);
						ps.setString(3, filename.split("\\.")[1]);
						ps.setString(4, String.valueOf(file.length()));
						byte[] bytes = null;
						if(file!=null){
							try{
								 FileInputStream fis=new FileInputStream (file);
								 if(fis!=null){
									  int len=fis.available();
									  bytes=new byte[len];
									  fis.read(bytes);
								 }
							}catch(Exception e){
							}
						}
						lobCreator.setBlobAsBytes(ps, 5, bytes);
					}
				});
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BusException(e.getMessage());
		}
		return FID_KEY;
	}
	
	
	public String getFileName(Integer fid){
		String sql = " select count(*) from xt_file where fid="+fid;
		if(queryForInt(sql) > 0){
			sql = " select fname from xt_file where fid="+fid;
			return queryForObject(sql, String.class);
		}else{
			return "";
		}
	}
	
	public String getFileType(Integer fid){
		String sql = " select count(*) from xt_file where fid="+fid;
		if(queryForInt(sql) > 0){
			sql = " select FTYPE from xt_file where fid="+fid;
			return queryForObject(sql, String.class);
		}else{
			return "";
		}
	}
	
	public void doDeleteFile(Integer fid){
		String sql = "delete from xt_file where fid="+fid;
		update(false, sql);
	}
	
	public void downloadFile(Integer fid, final OutputStream os) {
		try {
			String sql = "select FDATA from xt_file where fid=?";
			this.getJdbcTemplate().query(sql, new Object[] { fid }, new AbstractLobStreamingResultSetExtractor() {
				protected void handleNoRowFound() throws LobRetrievalFailureException {
				}

				public void streamData(ResultSet rs) throws SQLException, IOException {
					InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
					if (is != null) {
						FileCopyUtils.copy(is, os);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}
}
