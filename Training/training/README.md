JWT Mechanism:
1. Client (Browser) invokes JwtAuthFilter
2. JwtAuthFilter intercepts the call and checks if it has JWT token or not
- 403 error in case the token is missing
3. JwtAuthFilter extracts subject from token (subject is something unique, such as email)
4. JwtAuthFilter starts validation process and calls UserDetailsService. It passes token subject
5. UserDetailsService looks for user data in the database by token subject and return data to JwtAuthFilter
6. JwtAuthFilter validates data from the database
- 403 error in case user doesn't exist
- 403 error in case the token is not valid
7. JwtAuthFilter calls SecurityContextHolder and set this connected user
- This way it says to Spring that this user is authenticated
8. SecurityContextHolder calls DispatcherServlet
9. DispatcherServlet calls controller
10. controller returns response to client (Browser)

![JWT mechanism](doc/jwt_mechanism.PNG)