package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.PaginationUtil;
import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.repository.*;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.request.SortType;
import com.vedantu.counselling.data.response.CounsellingData;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.service.mapper.CounsellingDataMapper;
import com.vedantu.counselling.data.view.CityData;
import com.vedantu.counselling.data.view.CounsellingDataMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    private final CityRepository cityRepository;

    private final int maxDistance;

    @Autowired
    public CounsellingDataService(CategoryRepository categoryRepository, GenderRepository genderRepository,
                                  QuotaRepository quotaRepository, CollegeTypeRepository collegeTypeRepository,
                                  CollegeRepository collegeRepository, BranchTagRepository branchTagRepository,
                                  BranchRepository branchrepository, RankRepository rankRepository, CityRepository cityRepository,
                                  @Value("${data.maxDistance}") int maxDistance) {
        this.categoryRepository = categoryRepository;
        this.genderRepository = genderRepository;
        this.quotaRepository = quotaRepository;
        this.collegeTypeRepository = collegeTypeRepository;
        this.collegeRepository = collegeRepository;
        this.branchTagRepository = branchTagRepository;
        this.branchrepository = branchrepository;
        this.rankRepository = rankRepository;
        this.cityRepository = cityRepository;
        this.maxDistance = maxDistance;
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
        List<MaxClosingRankByRankType> maxClosingRankByRankTypes = rankRepository.findMaxClosingRankByRankType();
        return CounsellingDataMapper.mapCounsellingDataMetadata(categories, genders, quotas, collegeTypes, colleges, branchTags, distinctDurations, distinctYears, maxDistance, maxClosingRankByRankTypes);
    }

    @Cacheable(value = {"cities"})
    public CityData getAllCities() {
        List<City> cities = cityRepository.findAll();
        return CounsellingDataMapper.mapCityData(cities);
    }

    public CounsellingDataResponse getCounsellingDataFor(CounsellingDataRequest request) {

        if (request == null) {
            return new CounsellingDataResponse();
        }

        List<CounsellingDbData> allData = rankRepository.findCounsellingDbData();

        List<CounsellingData> finalCounsellingData = getCounsellingData(filterDataForRequest(allData, request));

        Comparator<CounsellingData> comparator = request.getSortType().equals(SortType.ASC) ? request.getCounsellingDataSortBy().getComparator() : request.getCounsellingDataSortBy().getComparator().reversed();
        finalCounsellingData.sort(comparator);

        int size = finalCounsellingData.size();

        return new CounsellingDataResponse(size,
                PaginationUtil.getPaginatedList(finalCounsellingData, size, request.getPageSize(), request.getPageNumber()));
    }

    private List<CounsellingDbData> filterDataForRequest(List<CounsellingDbData> allData, CounsellingDataRequest counsellingDataRequest) {
        List<CounsellingDbData> filteredData = allData;
        if (counsellingDataRequest.getCategoryId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getCategoryId() == counsellingDataRequest.getCategoryId()).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getGenderId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getGenderId() == counsellingDataRequest.getGenderId()).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getQuotaId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getQuotaId() == counsellingDataRequest.getQuotaId()).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getYears() != null && !counsellingDataRequest.getYears().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> counsellingDataRequest.getYears().contains(entry.getYear())).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getDurations() != null && !counsellingDataRequest.getDurations().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> counsellingDataRequest.getDurations().contains(entry.getDuration())).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getBranchTagIds() != null && !counsellingDataRequest.getBranchTagIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> counsellingDataRequest.getBranchTagIds().contains(entry.getBranchTagId())).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getCollegeIds() != null && !counsellingDataRequest.getCollegeIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> counsellingDataRequest.getCollegeIds().contains(entry.getCollegeId())).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getCollegeTagIds() != null && !counsellingDataRequest.getCollegeTagIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> counsellingDataRequest.getCollegeTagIds().contains(entry.getCollegeTypeId())).collect(Collectors.toList());
        }

        if (counsellingDataRequest.getClosingRanks() != null && !counsellingDataRequest.getCollegeTagIds().isEmpty()) {
            List<CounsellingDbData> finalData = new ArrayList<>();
            for (CounsellingDbData counsellingDbData: filteredData) {
                boolean add= true;
                for (TwoTuple<Integer, Integer> rankFilter: counsellingDataRequest.getClosingRanks()) {
                    if(counsellingDbData.getRankTypeId() == rankFilter.getFirst() && counsellingDbData.getCloseRank()<=rankFilter.getSecond()) {
                        add = false;
                    }
                }
                if(add) {
                    finalData.add(counsellingDbData);
                }
            }
            filteredData = finalData;
        }
        return filteredData;
    }

    private List<CounsellingData> getCounsellingData(List<CounsellingDbData> counsellingDbData) {
        return counsellingDbData.stream().map(r -> new CounsellingData(1,
                r.getCollege(),
                r.getCollegeType(),
                r.getBranch(),
                r.getCategory(),
                r.getGender(),
                r.getQuota(),
                r.getYear(),
                new ThreeTuple<>(r.getRankTypeId(), r.getOpenRank(), r.getCloseRank()))).collect(Collectors.toList());
    }
}
