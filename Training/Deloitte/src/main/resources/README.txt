DEPLOYMENT:
1. Download tomcat 8.5.78 https://tomcat.apache.org/download-80.cgi
2. Unzip tomcat archive in any folder
3. Move to tomcat folder /webapps
4. Remove directory which called ROOT
5. Copy ROOT.war to /webapps
6. Move to tomcat folder /bin
7. Execute in command line (for Linux): catalina.sh jpda start
8. Be sure that tomcat started. You should see “Tomcat started.” line
9. Be sure that application started. Open file: /logs/catalina.out and you should see the word "Deloitte"
10. To stop the application you should move to tomcat folder /bin and execute ./shutdown.sh
11. Open the application using the URL: http://localhost:8080/api/v1/tasks
8080 - default port
8000 - debug port

USING THE APPLICATION:
There are 3 users in the application
№1
login: test
password: pwd123
role: admin
№2
login: test2
password: pwd1234
role: admin
№3
login: test3
password: pwd12345
role: user

There are 2 roles in the application:
admin - create, read, update, delete operations are available to you
user - read operation is available to you


