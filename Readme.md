
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

![alt text](https://drive.google.com/file/d/1mfJpp7BfIjQ2AniKyHzR4TEXEMx2sQyc/view?usp=sharing)
