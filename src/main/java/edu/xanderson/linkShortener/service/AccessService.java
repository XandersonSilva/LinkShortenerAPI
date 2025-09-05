package edu.xanderson.linkShortener.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.linkShortener.model.AccessEntity;
import edu.xanderson.linkShortener.model.ShortLinksEntity;
import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.AccessSummaryDTO;
import edu.xanderson.linkShortener.model.DTOs.ShortLinksEditDTO;
import edu.xanderson.linkShortener.model.repository.AccessRepository;
import edu.xanderson.linkShortener.model.repository.ShortLinksRepository;

@Service
public class AccessService {
    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private ShortLinksRepository shortLinksRepository;

    public String registerAccess(String link, String password, String ip){
        List<ShortLinksEntity> originalLinkList = shortLinksRepository.findByCode(link);

        System.out.println(link);
        if (originalLinkList.size() == 0) {
            //ERRO: 
            //NF:  Abreviação de not finded
            return "NF";
        }

        ShortLinksEntity originalLink = originalLinkList.getFirst();

        AccessEntity access = new AccessEntity(ip, originalLink, false);

        if (originalLink.isPrivate() == true) {
            String originalPassword = originalLink.getPassword();
            if( !originalPassword.equals(password)){
                access.setWasBlocked(true);
                accessRepository.save(access);
                //ERRO: 
                //WP:  Abreviação de wrong password
                return "WP";

            }
        }

        accessRepository.save(access);

        return originalLink.getOriginalUrl();
    }

    public List<AccessSummaryDTO> getLinkAccess(ShortLinksEditDTO link, UserEntity user){
        boolean DBLinkExists = shortLinksRepository.existsById(link.getId());
        List<AccessSummaryDTO> accessesDTO = new ArrayList<AccessSummaryDTO>();
        
        // Garantindo que o link informado pelo usuário existe no BD
        if (!DBLinkExists) return accessesDTO;
        ShortLinksEntity DBLink = shortLinksRepository.getReferenceById(link.getId());


        // Garantindo que o usuário é dono do link
        if (DBLink.getOwner().getId() != user.getId()) return accessesDTO;

        List<AccessEntity> accesses = accessRepository.findByShortLinkId(link.getId());

        for (AccessEntity access : accesses) {
            accessesDTO.add(new AccessSummaryDTO(access));
        }

        return accessesDTO;



    }
}
