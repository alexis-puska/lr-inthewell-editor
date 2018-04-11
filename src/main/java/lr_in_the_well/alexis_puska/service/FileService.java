package lr_in_the_well.alexis_puska.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lr_in_the_well.alexis_puska.domain.level.LevelFile;
import lr_in_the_well.alexis_puska.domain.sprite.SpriteFileContent;

public class FileService {

	private final static Logger LOG = Logger.getLogger(FileService.class);

	private final ObjectMapper objectMapper;

	public FileService() {
		this.objectMapper = new ObjectMapper();
	}

	public LevelFile readJsonFile(InputStream in) {
		LevelFile levelFile = null;
		try {
			levelFile = objectMapper.readValue(in, LevelFile.class);
		} catch (JsonParseException e) {
			LOG.error("JsonParseException : " + e.getMessage());
		} catch (JsonMappingException e) {
			LOG.error("JsonMappingException : " + e.getMessage());
		} catch (IOException e) {
			LOG.error("IOException : " + e.getMessage());
		}
		return levelFile;
	}

	public void writeJson(LevelFile levelFile, File file) {
		try {
			LOG.info("START Write level json file : " + file.getAbsolutePath());
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, levelFile);
		} catch (JsonProcessingException e) {
			LOG.error("JsonProcessingException : " + e.getMessage());
		} catch (IOException e) {
			LOG.error("IOException : " + e.getMessage());
		}
		LOG.info("Write level json file : SUCCESS");
	}

	public SpriteFileContent readJsonSpriteFile(InputStream in) {
		SpriteFileContent list = null;
		try {
			list = objectMapper.readValue(in, SpriteFileContent.class);
		} catch (JsonParseException e) {
			LOG.error("JsonParseException : " + e.getMessage());
		} catch (JsonMappingException e) {
			LOG.error("JsonMappingException : " + e.getMessage());
		} catch (IOException e) {
			LOG.error("IOException : " + e.getMessage());
		}
		return list;
	}
}
