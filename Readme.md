
# Travel Blog Application using Spring Boot

<div style="padding:10px;font-family: 'Montserrat', sans-serif;">
Web Application developed using <span style="font-family: cursive; font-weight: 500; font-style: italic">Java Spring framework, Hibernate, MYSQL, Thymeleaf and HTML </span>. The Application design pattern follows 
MVC (Model-View-Controller) architecture. 
</div>

## Directory structure

```
 📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com.example.travellerblog
 ┃ ┃     ┣ 📂controller
 ┃ ┃     ┃ ┣ 📜BlogController.java
 ┃ ┃     ┃ ┣ 📜BlogItemController.java
 ┃ ┃     ┃ ┗ 📜MainController.java
 ┃ ┃     ┣ 📂model
 ┃ ┃     ┃ ┗ 📜Blog.java
 ┃ ┃     ┣ 📂repository
 ┃ ┃     ┃ ┗ 📜BlogRepository.java
 ┃ ┃     ┣ 📂service
 ┃ ┃     ┃ ┣ 📜BlogService.java
 ┃ ┃     ┃ ┣ 📜BlogServiceImpl.java
 ┃ ┃     ┃ ┣ 📜ImageStorageService.java
 ┃ ┃     ┃ ┗ 📜ImageStorageServiceImpl.java
 ┃ ┃     ┣ 📂utils
 ┃ ┃     ┃ ┣ 📜BlogForm.java
 ┃ ┃     ┃ ┗ 📜BlogItemTemplate.java
 ┃ ┃     ┗ 📜TravellerBlogApplication.java
 ┃ ┃
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂assets
 ┃ ┃ ┃ ┃ ┣ 📜bcg.jpg
 ┃ ┃ ┃ ┃ ┗ 📜bg-inner.png
 ┃ ┃ ┃ ┃
 ┃ ┃ ┃ ┗ 📂styles
 ┃ ┃ ┃ ┃ ┣ 📜blogItem.css
 ┃ ┃ ┃ ┃ ┣ 📜blogItemEdit.css
 ┃ ┃ ┃ ┃ ┣ 📜blogList.css
 ┃ ┃ ┃ ┃ ┗ 📜index.css
 ┃ ┃ ┃ ┃
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📜blogItem.html
 ┃ ┃ ┃ ┣ 📜blogItemEdit.html
 ┃ ┃ ┃ ┣ 📜blogList.html
 ┃ ┃ ┃ ┣ 📜error.html
 ┃ ┃ ┃ ┗ 📜index.html
 ┃ ┃ ┃
 ┃ ┃ ┗ 📜application.properties
 ┃ ┃
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂com.example.travellerblog
 ┃ ┃   ┣ 📜ImageStorageServiceTests.java
 ┃ ┃   ┗ 📜TravellerBlogApplicationTests.java          

```

## Module Info

This spring application is dependent on following module.

``` java
    module TravellerBlog {

        requires java.persistence;
        requires commons.fileupload;
        requires org.apache.commons.io;
        requires spring.beans;
        requires spring.boot.autoconfigure;
        requires spring.boot.devtools;
        requires spring.context;
        requires spring.data.commons;
        requires spring.data.jpa;
        requires spring.web;
        requires spring.webmvc;
}
```

## Building this Project

Simply clone this project, then open the project in your favourite IDE,
the maven building of the project will be automatically done by the IDE.

## Output and Features

### Index Page

The index or the home page of the website has a travel blog form with following fields
* **User Name:** Name of the user to whom the blog belongs ****(max 100 characters)****; later using this name we can access all the blog belonging to this person.
* **Name** Travel Name you want to dedicated this blog to
* **Date** Travel Date
* **Description** Description related to the blog
* **Cover Image** Image file you want to associate this blog to.

![index Page](https://github.com/Ryednap/TravelBlog/blob/main/docs/indexPage.gif)
