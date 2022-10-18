#Generate artifacts:
.\mvnw.cmd clean package install
#Build image and container.
docker build -t inditex/prices-api .
#Run Docker image.
docker run -d  --name prices-api  --net bridge  -p 8091:8091  inditex/prices-api