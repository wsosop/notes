package com.wck.simple.orm;

import java.math.BigDecimal;

/**
 * @author 御香烤翅
 * @create 2020-02-23 22:55
 */
public class Book {

//      `bookid` int(11) NOT NULL AUTO_INCREMENT,
//      `bookname` varchar(255) DEFAULT NULL,
//      `price` decimal(10,0) DEFAULT NULL,

    private Integer bookid;
    private String bookname;
    private Double price;

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", price=" + price +
                '}';
    }
}
