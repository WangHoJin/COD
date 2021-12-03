package com.cod.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.cod.serviceImpl.S3ServiceImpl;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import java.io.File;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class WebClientController {
    private final S3ServiceImpl s3Service2;
    private final AmazonS3Client amazonS3Client;
    @PostMapping("/rembg")
    public String upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
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

        byte[] byteArray = client
                .get()
                .uri("?url="+url)
                .accept(MediaType.IMAGE_JPEG)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        //파일로 변환
        int lByteArraySize = byteArray.length;
        String filename = "tempFile_" + multipartFile.getOriginalFilename();
        System.out.println(filename);
        String res = null;
        try{

            String rootPath = System.getProperty("user.dir");
            String path = rootPath+File.separator+"temp"; //폴더 경로
            File Folder = new File(path);
            // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
            if (!Folder.exists()) {
                try{
                    Folder.mkdir(); //폴더 생성합니다.
                    System.out.println("폴더가 생성되었습니다.");
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
            }

            File lOutFile = new File(path+File.separator+filename);
            FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
            lFileOutputStream.write(byteArray);
            lFileOutputStream.close();

            res = s3Service2.upload(lOutFile, "static");
            try{
                lOutFile.delete();
                System.out.println("삭제성공");

            }catch (Exception e){
                System.out.println("삭제실패");
            }

        }catch(Throwable e){
            e.printStackTrace(System.out);
        }
        return res;
    }
}
