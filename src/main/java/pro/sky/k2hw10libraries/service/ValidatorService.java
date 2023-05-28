package pro.sky.k2hw10libraries.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.k2hw10libraries.exception.IncorrectNameException;

@Service
public class ValidatorService {

    public String validateName(String name){

        if(!StringUtils.isAlpha(name)){
    throw new IncorrectNameException(name);
}
return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname){
String[]surnames = surname.split("-");
for(int i = 0;i<surnames.length;i++){
   if(!StringUtils.isAlpha(surnames[i])){

        throw new IncorrectNameException(surname);
    }
    surnames[i] = StringUtils.capitalize(surnames[i].toLowerCase());
}
return String.join("-", surnames);
    }
}
