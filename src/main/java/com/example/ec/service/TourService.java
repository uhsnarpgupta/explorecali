package com.example.ec.service;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import com.example.ec.repo.TourPackageRepository;
import com.example.ec.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourPackageRepository tourPackageRepository;
    private TourRepository tourRepository;

    @Autowired
    public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRepository = tourRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, String tourPackagName, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackagName)
                .orElseThrow(() -> new RuntimeException("Tour package does not exists: " + tourPackagName));

        return tourRepository.save(new Tour(title, description, blurb, price, duration,
                bullets, keywords, tourPackage, difficulty, region));
    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public long total() {
        return tourRepository.count();
    }
}
