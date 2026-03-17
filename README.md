# url-shortener-project
# URL Shortener Project

 Overview

This project is a full-stack URL Shortener application developed using Java for the backend and HTML, CSS, and JavaScript for the frontend.

The application allows users to convert long URLs into short, easy-to-share links and redirect back to the original URL when accessed.

## Features

* Convert long URLs into short URLs
* Redirect short URL to original URL
* Track click count (basic level)
* Display recent URLs in table format
* Show analytics using chart
* Clean and user-friendly interface


##  Technologies Used

### Backend

* Java (HttpServer API)

### Frontend

* HTML
* CSS
* JavaScript

##  Project Structure

url-shortener-project/

backend/
└── src/
    └── UrlShortenerServer.java

frontend/
├── index.html
├── style.css
└── script.js


##  Setup and Installation

###  Prerequisites

* Java JDK (version 8 or above)
* Web browser (Chrome recommended)
* Code editor (VS Code recommended)

##  How to Run the Project

### Step 1: Start Backend Server

Open terminal and navigate to backend source folder:

cd backend/src

Compile the Java file:

javac UrlShortenerServer.java

Run the server:

java UrlShortenerServer

Server will start at:

http://localhost:8080


### Step 2: Run Frontend

Open the frontend folder and launch:

index.html

##  How to Use / Test

1. Enter a valid URL (e.g., https://facebook.com)
2. Click **"Shorten URL"**
3. A short URL will be generated
4. Click the short URL
5. It will redirect to the original website
6. Click count will be updated in the table

##  Functionality Explanation

* The frontend sends the URL to the backend using HTTP POST request
* The backend generates a unique short code
* The mapping is stored using HashMap (in-memory)
* When the short URL is accessed:

  * Backend retrieves the original URL
  * Redirects the user
  * Updates click count

##  Limitations

* Data is stored in memory (HashMap)
* Data will be lost when server restarts
* No database integration (can be added in future)

##  Future Enhancements

* Integrate MySQL database for persistent storage
* Add user authentication
* Improve UI with advanced design
* Add copy-to-clipboard animation
* Deploy project online

##  Author

* Jeeva S

##  Conclusion

This project demonstrates the implementation of a URL shortening system with both frontend and backend integration. It showcases core concepts such as HTTP communication, Java server handling, and dynamic UI updates.

---


    
