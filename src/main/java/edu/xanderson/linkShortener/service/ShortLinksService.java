package edu.xanderson.linkShortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.linkShortener.model.ShortLinksEntity;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksCreateDTO;
import edu.xanderson.linkShortener.model.repository.ShortLinksRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

@Service
public class ShortLinksService {
    @Autowired
    private ShortLinksRepository shortLinksRepository;

    private Random random = new Random();

    private String generateRandomCode(){
        String code = "";
        for (int i = 0; i < 8; i++) {
            int typeCaracter = random.nextInt(1, 4);
            switch (typeCaracter) {
                case 1:
                    code += (char) random.nextInt(48, 58);
                    break;
                case 2:
                    code += (char) random.nextInt(65, 90);
                    break;
                case 3:
                    code += (char) random.nextInt(97, 123);
                    break;
                default:
                    break;
            }
        }

        return code;
    }

    private boolean verifyCodeIsUnique(String code){
        boolean isUnique = true;
        List<ShortLinksEntity> links = shortLinksRepository.findByCode(code);
        for (ShortLinksEntity link : links) {
            if(link.getCode() == code){
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }
    private String generateUniqueCode(String code){

        if (!verifyCodeIsUnique(code)) {
            code = generateUniqueCode(generateRandomCode());
        }

        return code;
    }

    private boolean valideLink(String originalLink) throws IOException{
        URL u = new URL(originalLink);
        int CODEResponse = 0;
        HttpURLConnection huc = (HttpURLConnection) u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        CODEResponse = huc.getResponseCode();
        if (CODEResponse != 200) {
            return false;
            
        } else {
            return true;
        }
    }

    // Retorna 
    // 0: Sucesso
    // 1: código do usuário já em uso
    // 2: Url inválida
    public String createShortLink(ShortLinksCreateDTO link) throws IOException{
        if (!valideLink(link.getOriginalUrl())) {
            return "2";
        }


        if (link.getCode() != null && !verifyCodeIsUnique(link.getCode())) {
            return "1";
        }
        if (link.getCode() == null) {
            link.setCode(generateUniqueCode(generateRandomCode()));    
        }

        try {
            shortLinksRepository.save(new ShortLinksEntity(link));
            return link.getCode();
        } catch (Exception e) {
            System.out.println(e);
            return "2";
        }
    }



    
}
