
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

## Building this Project

* On Linux based distros
  * install maven ``sudo apt get install maven``
  * 
