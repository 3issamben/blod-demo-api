.PHONY: build,run,test


build:
	./mvnw clean package
	docker build . -t seb-demo

run:
	docker run -p 8080 -t seb-demo
