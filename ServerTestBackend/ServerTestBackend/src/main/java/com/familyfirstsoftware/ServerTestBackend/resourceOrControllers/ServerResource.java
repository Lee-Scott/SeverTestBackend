package com.familyfirstsoftware.ServerTestBackend.resourceOrControllers;

import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Response;
import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Server;
import com.familyfirstsoftware.ServerTestBackend.enums.Status;
import com.familyfirstsoftware.ServerTestBackend.service.impl.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() throws InterruptedException {

        TimeUnit.SECONDS.sleep(1);
        // we would need to do exception handling to show to the user
        //throw new RuntimeException("Error Check");

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.list(30)))// TODO just hardcoded you could ask the user
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );


    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable ("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable ("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("Server retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable ("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("Deleted server")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    // TODO fix the long image import to be local
    @GetMapping(
            path = "/image/{fileName}",
            produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable ("fileName") String fileName) throws IOException {
        // C:\Users\lhs49\Desktop\Playspace\reactive-spring\ServerTestBackend\ServerTestBackend\src\main\resources\static\ServerImages
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") +
                "/Desktop/Playspace/reactive-spring/ServerTestBackend/ServerTestBackend/src/main/resources/static/ServerImages/"
                + fileName));
    }


}
