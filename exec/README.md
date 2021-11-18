# How to build & deploy `Closet of Days (cod)` project

## 1. Setting
- JVM: OpenJDK-11 
- Web Server: Nginx
- WAS: Gradle
- Database: MYSQL
- Frontend IDE: Visual Studio Code
- Backend IDE: Intellij Ultimate

<br>



## 2. Setup PyTorch/Flask Server
- Requirements

```
python 3.8 or newer
torch and torchvision stable version (https://pytorch.org)
```



- Install torch/torchvision

Go to https://pytorch.org and scrool down to `INSTALL PYTORCH` section and follow the instructions.

For example:

```
PyTorch Build: Stable (1.7.1)
Your OS: Windows
Package: Pip
Language: Python
CUDA: None
```

The install cmd is:

```
pip install torch==1.7.1+cpu torchvision==0.8.2+cpu -f https://download.pytorch.org/whl/torch_stable.html
```



- Installation

```
pip install rembg
```



- Start the server

```
rembg-server
```





<br>

## 3. Setup Web Server
- Issue `let's encrypt` certificate
``` 
$ docker run -it --rm --name cert_tmp -p 80:80 -v /home/ubuntu/cert:/etc/letsencrypt certbot/certbot certonly \ --standalone -d {YOUR_SERVER_DOMAIN} -m {YOUR_MAIL} 
```

- Install Nginx
```
$ sudo apt-get update

$ sudo apt-get upgrade

// nginx install
$ sudo apt-get install nginx
```

- Move build file to Nginx root directory(/usr/share/nginx/html)
```
$ cd S05P21B205/frontend

// npm package install
$ npm install

// project build
$ npm run build

// project deploy
$ cp build/* /usr/share/nginx/html
```

- Set up Nginx conf file
```
$ cd /etc/nginx/sites-available

$ vi default
```

```
# 80 port 용도
server {
    listen      80 default_server;
    listen [::]:80 default_server;

    location / {
        # 영구적으로 https로 redirect 처리
        return 301 https://$host$request_uri;
    }
}

# 기존 설정을 https(443)용으로 설정
server {
    listen      443 ssl default_server;
    listen [::]:443 ssl default_server;

    # 인증서 설정 (managed by Certbot)
    # 인증서 만료시 새로 발급 후 cipher 변경 필요
    ssl_certificate /home/ubuntu/cert/live/k5b202.p.ssafy.io/fullchain.pem;
    ssl_certificate_key /home/ubuntu/cert/live/k5b202.p.ssafy.io/privkey.pem;
    ssl_session_cache shared:le_nginx_SSL:10m;
    ssl_session_timeout 1440m;
    ssl_session_tickets off;
    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_prefer_server_ciphers off;
    ssl_ciphers ALL:!aNULL:!EXPORT56:RC4+RSA:+HIGH:+MEDIUM:+LOW:+SSLv2:+EXP;AES256-SHA:ECDHE-RSA-AES128-SHA256:ECDHE-RSA-AES256-SHA384";

 	root /home/ubuntu/S05P31B202/frontend/dist;
    index index.html index.htm index.nginx-debian.html;
	server_name _;

    location /api {
         proxy_pass http://backend;
         proxy_http_version 1.1;
         proxy_set_header Connection "";
         include /etc/nginx/proxy_params;
    }
}

```

- Restart Nginx
```
$ sudo service nginx start
```

<br>

## 4. Setup application.yml
- Set environment variables according to your project. 
```
# S05P31B202/backend/src/main/resources/application.yml

spring:
  datasource:
    hikari:
      driver-class-name: {YOUR_DATABASE_DIRVER_CLASS_NAME}
      jdbc-url: {YOUR_DATABASE_URL}
      username: {YOUR_DATABASE_USERNAME}
      password: {YOUR_DATABASE_PASSWORD}

  jpa:
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        
  #WebClient in-memory Setting      
  codec:
    max-in-memory-size: 10MB
    
custom:
  constant:
      access.token.secret.key: {YOUR_ACCESS_TOKEN_SECRET_KEY}
      valid.time: {YOUR_VALID_TIME}

server:
  servlet:
    context-path: "/api"

#AMAZON S3 Setting
cloud:
  aws:
    credentials:
      accessKey: {YOUR_ACCESS_KEY}
      secretKey: {YOUR_SECRET_KEY}
    s3:
      bucket: {YOUR_BUCKET_NAME}
    region:
      static: {YOUR_BUCKET_REGION}
    stack:
      auto: false
```

<br>

## 5. Setup Web Application Server
- Before deploying, you have to set application.yml in your project
```
$ cd S05P31B202/backend

$ chmod +x gradlew

// project build
$ ./gradlew clean build -x test

// project deploy
$ cd S05P31B202/frontend
$ npm install
$ npm build
$ java backend/build/libs/cod_api-1.0.jar ubuntu@k5b202.p.ssafy.io:~/app1.jar
```

<br>