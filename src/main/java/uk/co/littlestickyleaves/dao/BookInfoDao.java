package uk.co.littlestickyleaves.dao;

import uk.co.littlestickyleaves.domain.Book;

import java.util.List;

/**
 * Interface to define what a BookInfoDao must be able to do
 */
public interface BookInfoDao {

    List<Book> fetchBooks();

    void storeBooks(List<Book> books);
}
