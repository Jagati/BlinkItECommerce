package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.exceptions.UserNotFoundException;
import com.lldproject.blinkitecommerce.models.Advertisement;
import com.lldproject.blinkitecommerce.repositories.AdvertisementRepository;
import com.lldproject.blinkitecommerce.repositories.PreferencesRepository;

public interface AdsService {

    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException;
}
