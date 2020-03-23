package com.wck;

import com.wck.produce.BootActiveMqProduce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SpringBootActivemqProduceApplicationTests {


    @Autowired
    private BootActiveMqProduce bootActiveMqProduce;

    @Test
    public void contextLoads() {
        bootActiveMqProduce.queueMsg();
    }

}
