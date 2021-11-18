package com.cod.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.cod.serviceImpl.S3ServiceImpl;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.io.File;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.awt.image.DataBuffer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class WebClientController {
    private final S3ServiceImpl s3Service2;
    private final AmazonS3Client amazonS3Client;

    @PostMapping("/rembg")
    public byte[] upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String url = s3Service2.upload(multipartFile, "static");

        //Memory 조정: 10M (default 256KB)
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10*1024*1024))
                .build();

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(
                        client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) //miliseconds
                                .doOnConnected(
                                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                                                .addHandlerLast(new WriteTimeoutHandler(60)) //sec
                                )
                );

        WebClient client = WebClient.builder()
                .baseUrl("http://k5b202.p.ssafy.io:5000")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(
                        (req, next) -> next.exchange(
                                ClientRequest.from(req).header("from", "webclient").build()
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofRequestProcessor(
                                clientRequest -> {
                                    return Mono.just(clientRequest);
                                }
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofResponseProcessor(
                                clientResponse -> {
                                    return Mono.just(clientResponse);
                                }
                        )
                )
                .exchangeStrategies(exchangeStrategies)
                .build();

        //method1
//        byte[] fileItem = client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .accept(MediaType.IMAGE_PNG)
//                .retrieve()
//                .bodyToMono(byte[].class)
//                .block();
        //method2
//        Mono<byte[]> monoContents = client
//                .get()
//                .uri("/largefiles/1")
//                .retrieve()
//                .bodyToMono(byte[].class);

        byte[] image = client
                .get()
                .uri(url)
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        return image;

//        File uploadFile = new File()
//        return "OK";

//        s3Service2.upload(new FileOutputStream(fileItem).write(img);,"static");

//        File uploadFile = convert(fileItem).
//        s3Service2.upload()

//        Flux<DataBuffer> dataBufferFlux = client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .accept(MediaType.IMAGE_PNG)
//                .retrieve()
//                .bodyToFlux(DataBuffer.class);
//
//        DataBufferUtils.write(dataBufferFlux,"/",).block;
//        s3Service2.upload(fileItem,"static");

//        return client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .retrieve()
//                .bodyToMono(String.class);
    }

    @GetMapping("/rembg")
    public byte[] remBackGroundAndPutS3() {
        //Memory 조정: 10M (default 256KB)
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10*1024*1024))
                .build();

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(
                        client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) //miliseconds
                                .doOnConnected(
                                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                                                .addHandlerLast(new WriteTimeoutHandler(60)) //sec
                                )
                );

        WebClient client = WebClient.builder()
                .baseUrl("http://k5b202.p.ssafy.io:5000")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(
                        (req, next) -> next.exchange(
                                ClientRequest.from(req).header("from", "webclient").build()
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofRequestProcessor(
                                clientRequest -> {
                                    return Mono.just(clientRequest);
                                }
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofResponseProcessor(
                                clientResponse -> {
                                    return Mono.just(clientResponse);
                                }
                        )
                )
                .exchangeStrategies(exchangeStrategies)
                .build();

        //method1
//        byte[] fileItem = client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .accept(MediaType.IMAGE_PNG)
//                .retrieve()
//                .bodyToMono(byte[].class)
//                .block();
        //method2
//        Mono<byte[]> monoContents = client
//                .get()
//                .uri("/largefiles/1")
//                .retrieve()
//                .bodyToMono(byte[].class);

        byte[] image = client
                .get()
                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        return image;

//        File uploadFile = new File()
//        return "OK";

//        s3Service2.upload(new FileOutputStream(fileItem).write(img);,"static");

//        File uploadFile = convert(fileItem).
//        s3Service2.upload()

//        Flux<DataBuffer> dataBufferFlux = client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .accept(MediaType.IMAGE_PNG)
//                .retrieve()
//                .bodyToFlux(DataBuffer.class);
//
//        DataBufferUtils.write(dataBufferFlux,"/",).block;
//        s3Service2.upload(fileItem,"static");

//        return client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .retrieve()
//                .bodyToMono(String.class);
    }

    @GetMapping("/test")
//    public Mono<String> doTest3() {
//    public MultipartFile doTest3(){
    public byte[] doTest3() {
//      public Flux<String> doTest3() {
        //Memory 조정: 10M (default 256KB)
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10*1024*1024))
                .build();

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(
                        client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) //miliseconds
                                .doOnConnected(
                                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                                                .addHandlerLast(new WriteTimeoutHandler(60)) //sec
                                )
                );

        WebClient client = WebClient.builder()
                .baseUrl("http://k5b202.p.ssafy.io:5000")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(
                        (req, next) -> next.exchange(
                                ClientRequest.from(req).header("from", "webclient").build()
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofRequestProcessor(
                                clientRequest -> {
//                                    log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
//                                    log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
//                                    clientRequest.headers().forEach(
//                                            (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
//                                    );
                                    return Mono.just(clientRequest);
                                }
                        )
                )
                .filter(
                        ExchangeFilterFunction.ofResponseProcessor(
                                clientResponse -> {
//                                    log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
//                                    clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                                    return Mono.just(clientResponse);
                                }
                        )
                )
                .exchangeStrategies(exchangeStrategies)
                .build();

        byte[] fileItem = client.get()
                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
                .accept(MediaType.IMAGE_PNG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

//        File uploadFile = new File()
        return fileItem;

//        s3Service2.upload(new FileOutputStream(fileItem).write(img);,"static");

//        File uploadFile = convert(fileItem).
//        s3Service2.upload()

//        Flux<DataBuffer> dataBufferFlux = client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .accept(MediaType.IMAGE_PNG)
//                .retrieve()
//                .bodyToFlux(DataBuffer.class);
//
//        DataBufferUtils.write(dataBufferFlux,"/",).block;
//        s3Service2.upload(fileItem,"static");

//        return client.get()
//                .uri("?url=https://cod-bucket2.s3.ap-northeast-2.amazonaws.com/static/54e1f959-774f-4c19-bf8f-b4fe384cd64bolli-the-polite-cat.jpg")
//                .retrieve()
//                .bodyToMono(String.class);
    }
}
