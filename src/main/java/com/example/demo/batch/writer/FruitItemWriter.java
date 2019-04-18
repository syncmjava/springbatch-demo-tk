package com.example.demo.batch.writer;

import com.example.demo.batch.domain.Fruit;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FruitItemWriter implements ItemWriter<Fruit> {
  //  @Autowired private RestTemplate restTemplate;

  @Override
  public void write(List<? extends Fruit> items) throws Exception {
    System.out.println("writer chunk size is :" + items.size());
    for (Fruit item : items) {
      System.out.println("writer data is:" + item.getName());

      String apiURL = "http://localhost:8081/swagger/user/info/" + item.getName();
      //      restTemplate.getForObject(apiURL, Fruit.class);
    }
  }
}
