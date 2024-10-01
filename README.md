
Pull the docker image for rabbitMQ (including management UI for the browser). 
- docker pull rabbitmq:4.0.2-management

Create and start a new docker container based on the image (--rm automatically deletes the container when you stop it)
- docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:4.0.2-management
- docker run --rm -d -p 15672:15672 -p 5672:5672 rabbitmq:4.0.2-management (if you want to detach it meaning running it without occupying the terminal)


You can see it here http://localhost:15672. Standard login credentials are 
user: guest
password: guest
