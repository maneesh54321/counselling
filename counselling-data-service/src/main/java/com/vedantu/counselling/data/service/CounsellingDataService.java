package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.repository.*;
import com.vedantu.counselling.data.response.CounsellingDataMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CounsellingDataService {

    private final CategoryRepository categoryRepository;

    private final GenderRepository genderRepository;

    private final QuotaRepository quotaRepository;

    private final CollegeTypeRepository collegeTypeRepository;

    private final CollegeRepository collegeRepository;

    private final BranchTagRepository branchTagRepository;

    private final BranchRepository branchrepository;

    private final RankRepository rankRepository;

    @Autowired
    public CounsellingDataService(CategoryRepository categoryRepository, GenderRepository genderRepository,
                                  QuotaRepository quotaRepository, CollegeTypeRepository collegeTypeRepository,
                                  CollegeRepository collegeRepository, BranchTagRepository branchTagRepository,
                                  BranchRepository branchrepository, RankRepository rankRepository) {
        this.categoryRepository = categoryRepository;
        this.genderRepository = genderRepository;
        this.quotaRepository = quotaRepository;
        this.collegeTypeRepository = collegeTypeRepository;
        this.collegeRepository = collegeRepository;
        this.branchTagRepository = branchTagRepository;
        this.branchrepository = branchrepository;
        this.rankRepository = rankRepository;
    }

    @Cacheable(value = {"counsellingDataMetadata"})
    public CounsellingDataMetadata getCounsellingDataMetadata() {
        List<Category> categories = categoryRepository.findAll();
        List<Gender> genders = genderRepository.findAll();
        List<Quota> quotas = quotaRepository.findAll();
        List<CollegeType> collegeTypes = collegeTypeRepository.findAll();
        List<College> colleges = collegeRepository.findAll();
        List<BranchTag> branchTags = branchTagRepository.findAll();
        List<Integer> distinctDurations = branchrepository.findDistinctDurations();
        List<Integer> distinctYears = rankRepository.findDistinctYears();
//        TODO: Add logic to fetch maxRanks by rank type
//        TODO: Add maxDistance fetching logic
        return new CounsellingDataMetadata(
                categories, genders, quotas, collegeTypes, colleges, branchTags,
                distinctDurations, distinctYears, 3000, Collections.emptyMap()
        );
    }

}
