package com.pfeproject.EspritAcademy.Controller;



import com.pfeproject.EspritAcademy.Entity.TestEntity;
import com.pfeproject.EspritAcademy.Repository.TestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/Test")
@AllArgsConstructor
@Slf4j
public class TestController {

    @Autowired
    private TestRepository testRepository;


    @PostMapping("/Add")
    public ResponseEntity<TestEntity> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info(file.getBytes().toString());

            TestEntity entity = new TestEntity();
            entity.setFileName(file.getOriginalFilename());
            entity.setFileContent(file.getBytes());
            TestEntity savedEntity = testRepository.save(entity);
            return ResponseEntity.ok(savedEntity);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/GETAll")
    public List<TestEntity> getAllCourses() {
        return testRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<TestEntity> optionalEntity = testRepository.findById(id);
        if (optionalEntity.isPresent()) {
            TestEntity entity = optionalEntity.get();
            String encodedFileName = UriUtils.encode(entity.getFileName(), StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(encodedFileName).build());
            return ResponseEntity.ok().headers(headers).body(entity.getFileContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        try {
            testRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }


}


