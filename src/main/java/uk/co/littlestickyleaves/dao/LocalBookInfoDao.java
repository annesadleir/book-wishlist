package uk.co.littlestickyleaves.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.littlestickyleaves.domain.Book;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * A really basic implementation of the Book DAO,
 * storing the json in a local file
 */
public class LocalBookInfoDao implements BookInfoDao{

    private static final String FILE_LOCATION = "C:\\Workarea\\books.json";

    private final String fileName;

    private final ObjectMapper objectMapper;

    public LocalBookInfoDao(String fileName, ObjectMapper objectMapper) {
        this.fileName = fileName;
        this.objectMapper = objectMapper;
    }

    public static LocalBookInfoDao defaultLocalBookInfoDao(ObjectMapper objectMapper) {
        return new LocalBookInfoDao(FILE_LOCATION, objectMapper);
    }

    @Override
    public List<Book> fetchBooks() {
        try {
            String content = Files.readString(Path.of(fileName), StandardCharsets.UTF_8);
            return deserialise(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void storeBooks(List<Book> books) {
        try {
            Files.write(Path.of(fileName),
                    serialise(books),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file '" + fileName + "'", e);
        }
    }

    private byte[] serialise(List<Book> books) {
        try {
            return objectMapper.writeValueAsString(books).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to write books object as String", e);
        }
    }

    private List<Book> deserialise(String fileContents) {
        try {
            return objectMapper.readValue(fileContents, new TypeReference<List<Book>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to write books object as String", e);
        }
    }

}
