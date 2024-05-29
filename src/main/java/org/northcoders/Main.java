package org.northcoders;


import org.northcoders.model.Book;

import java.net.URISyntaxException;
import java.util.List;

import static org.northcoders.dao.FakeBooksDAO.askForInput;
import static org.northcoders.dao.FakeBooksDAO.getData;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
//       List<Book> resultList = getData();
//        resultList.stream().forEach(b -> System.out.println(b.title() + "  - Author: " + b.author()));

        askForInput();



    }
}