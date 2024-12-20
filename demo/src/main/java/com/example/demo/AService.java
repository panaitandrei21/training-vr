package com.example.demo;

import io.micrometer.core.annotation.Timed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AService { // subclasat de proxy
    private final Config config;
    private final UserRepository userRepository;

    @Timed
    public String metodaSmechera() {
        //if(true) throw new RuntimeException("Intentionat sa vezi proxyul in fata meodei asteia in call stack");
        log.trace("debug pt o problema nereproductibila pe local, ci doar in productie "+config);
        return "hello! " + config.x();
    }

    @Async("exportPool")
    void generate(String uuid) {
        try (FileWriter fileWriter = new FileWriter("export-%s.csv".formatted(uuid))) {
            for (User user : userRepository.findAll()) {
                fileWriter.write(user.getName() + ";" + user.getEmail() + "\n");
            }
            Thread.sleep(3000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void test() {
        testProxyNuMergePtCaApelezDinAceeasiClasa();
    }

    public void testProxyNuMergePtCaApelezDinAceeasiClasa() {
        User user = userRepository.findById(1L).orElseThrow();
        user.setEmail("bossu@gmail.com");
    }


}
