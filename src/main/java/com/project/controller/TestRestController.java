package com.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Git url: https://github.com/Tejashwini-developer/Tejashwini_RestApis_Practice
@RestController
@RequestMapping("/MyRestProject")
public class TestRestController {
	
	@GetMapping("/getInvitation/{myJob}/{myCity}")
	public ResponseEntity<String> getInvitation(@RequestParam("myName") String name, @RequestParam("myId") int id, 
			@PathVariable String myJob, @PathVariable("myCity") String city) {
		
		//Request url for postman = http://localhost:8080/MyRestProject/getInvitation/Java Development with kafka/Bangalore?myName=Tejashwini Tiraki&myId=103
	
		String msg = "Hello!! your name is " + name 
				+ " and your ID is " + id 
				+ " you are invited to my first Rest app!! "
				+ " and your job here is  " + myJob
				+ " your base location is " + city;



		return new ResponseEntity<String>(msg,HttpStatus.OK);


		
		
	}
	

}



/*

sudo apt update
sudo apt install openjdk-17-jdk -y
sudo update-alternatives --config java  # pick 17
sudo update-alternatives --config javac # pick 17
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

java -version
mvn clean package -DskipTests
docker-compose up --build

curl -X POST "http://localhost:8080/kafka/send?message=tejashwini-kafka-message"
http://localhost:8080/MyRestProject/getInvitation/Java Development with kafka/Bangalore?myName=Tejashwini Tiraki&myId=103


docker-compose down   //Stop all Docker containers
docker rmi -f $(docker images -aq)  //remove all images (docker images -aq → lists ALL image IDs, including hidden ones docker rmi -f → force remove all of them)
docker volume rm $(docker volume ls -q)  //removes ALL volumes
docker-compose up --build
docker-compose logs app --tail=10
docker-compose exec kafka kafka-topics --bootstrap-server localhost:9092 --list
docker-compose ps

docker build -t spring-app
docker run -p 8080:8080 spring-app
================================================================

Final clean command sequence for interview:bash

# 1. Setup - only once
sudo apt update
sudo apt install openjdk-17-jdk maven docker.io docker-compose -y
sudo update-alternatives --config java  # pick 17
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
java -version

# 2. Build + Run
mvn clean package -DskipTests
docker-compose up --build -d  # -d = detached

# 3. Verify
docker-compose ps  # check all Up
docker-compose logs app | grep Mapped  # check /kafka/send loaded

# 4. Test producer(in terminal)
curl -X POST "http://localhost:8080/kafka/send?message=accenture-rocks"

# 5. Check consumer
docker-compose logs app --tail=5  # Should see "Received: accenture-rocks"

# 6. Cleanup
docker-compose down


===============================

kubernetes:





*/