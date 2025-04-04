package intuitivecare.desafio_richard.data_transform.controller;

import intuitivecare.desafio_richard.data_transform.service.DataBaseTest;
import intuitivecare.desafio_richard.data_transform.service.TransformData;
import intuitivecare.desafio_richard.data_transform.service.WebScrapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping
public class InitwebScrapping {
    private final WebScrapping webScrapping;
    private final TransformData transformData;
    private final DataBaseTest dataBaseTest;

    public InitwebScrapping(WebScrapping webScrapping, TransformData transformData,DataBaseTest dataBaseTest) {
        this.webScrapping = webScrapping;
        this.transformData = transformData;
        this.dataBaseTest = dataBaseTest;
    }

    @GetMapping("webScrapping")
    public void init() {
        webScrapping.init();


    }

    @GetMapping("transformData")
    public void init02() {
        transformData.init();
    }


    @GetMapping("dataBaseTest")
    public void init03(){
        dataBaseTest.downloadArquivos();
    }
}
