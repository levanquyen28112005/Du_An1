package category;

import java.util.List;

import book.Book;

public class category {

    private String nameCategory;
    private List<Book> books;

    public category(String nameCategory, List<Book> books) {
        this.nameCategory = nameCategory;
        this.books = books;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
