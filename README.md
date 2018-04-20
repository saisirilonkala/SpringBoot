# SpringBoot File Upload Sample
 Overview : This Sample is for uploading files(only json) and store the meta data in H2 in memory data base using Spring boot. This Spring boot service provides functionality of uploading files and store the data in to memory and retrive the data from the H2 database using Rest API.
 
# Functionality 
1. Start your Spring Boot application and Access http://localhost:8080/index.html. This will allow to upload files.
2. Select the json file(I have attached the file in sample project with 111.json) and click on upload.
3. If we select other than json file we will be redirecting failure screen and shows 'Failed to upload file or Not a valid file' error message.
4. For retriving stored meta data use below url.
http://localhost:8080/getdata
This will trive the data in JSON formate.
