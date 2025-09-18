package com.lldproject.blinkitecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.exceptions.UserNotFoundException;
import com.lldproject.blinkitecommerce.models.Advertisement;
import com.lldproject.blinkitecommerce.models.Preference;
import com.lldproject.blinkitecommerce.models.User;
import com.lldproject.blinkitecommerce.repositories.AdvertisementRepository;
import com.lldproject.blinkitecommerce.repositories.PreferencesRepository;
import com.lldproject.blinkitecommerce.repositories.UserRepository;

@Service
public class AdsServiceImpl implements AdsService {
    private AdvertisementRepository advertisementRepository;
    private PreferencesRepository preferencesRepository;
    private UserRepository userRepository;
    @Autowired
    public AdsServiceImpl(AdvertisementRepository advertisementRepository, PreferencesRepository preferencesRepository, UserRepository userRepository){
        this.advertisementRepository = advertisementRepository;
        this.preferencesRepository = preferencesRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException{
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        List<Preference> userPreferences = user.getPreferences();
        for(Preference preference: userPreferences){
            Optional<Advertisement> advertisementOp = advertisementRepository.findByPreference(preference);
            if(advertisementOp.isPresent()){
                return advertisementOp.get();
            }
        }
        List<Advertisement> allAds = advertisementRepository.findAll();
        return allAds.isEmpty()?null:allAds.get(0);

    }
}
