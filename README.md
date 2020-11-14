# Icecream shop
## Application properties:
- Backend: 
	- spring boot framework
	- Server port: localhost:8080
- Frontend: 
	- ReactJS framework
	- Server port: localhost:9001
- Database: 
	- MySQL 8.0
	- url: localhost:3306/Icecream
	- username: root
	- password: 1234
## Start up:
- Database: tạo trước một database có tên là "Icecream"	
- Backend: 
	- eclipse IDE >> import maven project trỏ tới thư mục Backend
	- Run as >> Java Application >> Search file Application.java
	- Quá trình start up của ứng dụng sẽ thành công khi có dòng chữ **Core data loaded** !["Core data loaded"](guide.png)
- Frontend: 
```bash
$ cd Frontend
$ npm install
$ npm start
```
## Application's details:
- Ứng dụng gồm 2 role: "ADMIN" và "USER" (customer)
- Các Account có sẵn:

| #id |  Username  |password| role  |
|:----|:----------:|:------:|:-----:|
|  1  | SanDang    | 1234   | ADMIN |
|  2  | JohnDoe    | 1234   | ADMIN |
|  3  | Socrates   | 1234   | USER  |
|  4  | Zeno 	   | 1234   | USER  |
|  5  | DavidBowie | 1234   | USER  |
|  6  | Hegelian   | 1234   | ADMIN |

## Documentation:
- Các file postman: Documentations/postman (import postman)
- Database diagram: Documentations/UML/database_diagram.drawio ([import drawio](https://app.diagrams.net/))

