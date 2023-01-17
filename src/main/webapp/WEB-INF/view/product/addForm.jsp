<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
        </head>

        <body>
            <ul>
                <li>
                    <a href="/">홈</a>
                </li>
                <li>
                    <a href="/product/addForm">상품 등록</a>
                </li>
            </ul>
            <h1>상품 등록 페이지</h1>
            <hr>
            <form action="/product/add" method="post"> <!-- auto increasement id값이 자동부여됨 -->
                <input type="text" name="name" placeholder="제품 이름"><br>
                <input type="number" name="price" placeholder="제품 가격"><br>
                <input type="number" name="qty" placeholder="재고량"><br>
                <button type="submit">상품 등록</button>
            </form>
        </body>

        </html>