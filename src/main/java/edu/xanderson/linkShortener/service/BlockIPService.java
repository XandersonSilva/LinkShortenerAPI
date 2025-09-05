package edu.xanderson.linkShortener.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.xanderson.linkShortener.model.BlockIPEntity;
import edu.xanderson.linkShortener.model.ShortLinksEntity;
import edu.xanderson.linkShortener.model.UserEntity;
import edu.xanderson.linkShortener.model.DTOs.BlockIPCreateDTO;
import edu.xanderson.linkShortener.model.repository.BlockIPRepository;
import edu.xanderson.linkShortener.model.repository.ShortLinksRepository;

@Service
public class BlockIPService {
    @Autowired
    private ShortLinksRepository shortLinksRepository;

    @Autowired
    private BlockIPRepository blockIPRepository;

    

    public boolean blockIPAccessLink(BlockIPCreateDTO block, UserEntity user){
        // Garantindo que o IP informado pelo usuário é válido
        if (!isValidIP(block.getIp())) return false;
        
        boolean DBLinkExists = shortLinksRepository.existsById(block.getLink_id());
        
        // Garantindo que o link informado pelo usuário existe no BD
        if (!DBLinkExists) return false;
        ShortLinksEntity DBLink = shortLinksRepository.getReferenceById(block.getLink_id());


        // Garantindo que o usuário é dono do link
        if (DBLink.getOwner().getId() != user.getId()) return false;

        blockIPRepository.save(new BlockIPEntity(block));

        return true;
    }

    // Regex para IPv4
    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)"
          + "(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$";

    // Regex para IPv6
    private static final String IPV6_REGEX =
            "^(?:[\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,7}:$"
          + "|^:(?::[\\da-fA-F]{1,4}){1,7}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,6}:[\\da-fA-F]{1,4}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,5}(?::[\\da-fA-F]{1,4}){1,2}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,4}(?::[\\da-fA-F]{1,4}){1,3}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,3}(?::[\\da-fA-F]{1,4}){1,4}$"
          + "|^(?:[\\da-fA-F]{1,4}:){1,2}(?::[\\da-fA-F]{1,4}){1,5}$"
          + "|^[\\da-fA-F]{1,4}:(?::[\\da-fA-F]{1,4}){1,6}$"
          + "|^:(?::[\\da-fA-F]{1,4}){1,7}:?$";

    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    private static final Pattern IPV6_PATTERN = Pattern.compile(IPV6_REGEX);

    private static boolean isValidIP(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return IPV4_PATTERN.matcher(ip).matches() || IPV6_PATTERN.matcher(ip).matches();
    }
}
