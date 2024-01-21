package com.apulbere.petshop.crop;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;

import com.apulbere.petshop.crop.search.PetRecord;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class BaseSearchTest {

    @Value(value = "${local.server.port}")
    protected int port;

    @Autowired
    private TestRestTemplate restTemplate;


    private final ParameterizedTypeReference<List<PetRecord>> type = new ParameterizedTypeReference<>() {
    };

    protected List<PetRecord> makeRequest(Map<String, Object> queryParams) {
        var uriBuilder = fromHttpUrl("http://localhost")
            .pathSegment("pets")
            .port(port)
            .queryParams(toMultiValMap(queryParams));

        log.info("making request to {}", uriBuilder.toUriString());
        return restTemplate.exchange(uriBuilder.toUriString(), GET, null, type).getBody();
    }

    private MultiValueMap<String, String> toMultiValMap(Map<String, Object> map) {
        var multiValMap = new LinkedMultiValueMap<String, String>();
        for(var entry: map.entrySet()) {
            multiValMap.add(entry.getKey(), entry.getValue().toString());

        }
        return multiValMap;
    }

}
