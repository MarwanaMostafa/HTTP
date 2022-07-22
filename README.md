# HTTP

## How run code : 
  
  1-Download Folder code 
  
  2-Run code on any editor (recommend Eclipse )
  
  3-Add in Folder any website folder (Contain Files)

## what do this code :
  
  1-create TCP connection between client and server then client can send any request to the server 
  
  2-The request that comes to the server written in this format
  
    o GET or POST/subfolder/file HTTP/Version
   
   o Host: Folder
  
  3-the server gets the required file
  
  4-the server send HTTP Response msg to client consisting of :
  
   o HTTP version
 
   o Status Code
   
   o Date and Time
   
   o File Content
 
 5-The server sends the response to the client:
   
   o If a client requests a file that is not present in your server, your server should 
     return a “404 Not Found” error message.
   
   o else The response and the content of the required file are displayed at the client console
