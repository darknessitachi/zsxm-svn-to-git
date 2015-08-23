package com.group.core.common;

public class FileEntry {
	private String name;

	private String path;

	private String contentType;

	private String sourceName;

	public FileEntry() {

	}

	public FileEntry(String name, String path, String contentType,
			String sourceName) {
		this.name = name;
		this.path = path;
		this.contentType = contentType;
		this.sourceName = sourceName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}
