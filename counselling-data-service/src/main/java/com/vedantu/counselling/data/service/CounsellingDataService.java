package com.vedantu.counselling.data.service;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.repository.*;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.response.CounsellingData;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.service.mapper.CounsellingDataMapper;
import com.vedantu.counselling.data.view.CityData;
import com.vedantu.counselling.data.view.CounsellingDataMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounsellingDataService {

    @Autowired
    private EntityManager entityManager;

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

    public CounsellingDataResponse getCounsellingDataFor(CounsellingDataRequest counsellingDataRequest) {

        if (counsellingDataRequest == null) {
            return new CounsellingDataResponse();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rank> rankQuery = criteriaBuilder.createQuery(Rank.class);
        Root<Rank> root = rankQuery.from(Rank.class);
        Predicate predicate = convertRequestToPredicate(criteriaBuilder, root, counsellingDataRequest);
        if (predicate != null) {
            CriteriaQuery<Rank> finalQuery = rankQuery.select(root).where(predicate);
            List<Rank> rankList = entityManager.createQuery(finalQuery).getResultList();
            return new CounsellingDataResponse(rankList.size(), getCounsellingData(rankList));
        }
        return new CounsellingDataResponse();
    }

    private Predicate convertRequestToPredicate(CriteriaBuilder criteriaBuilder, Root<Rank> root, CounsellingDataRequest counsellingDataRequest) {

        Predicate predicate = null;
        if (counsellingDataRequest.getCategoryId() != 0) {
            Join<Rank, Category> categoryJoin = root.join("category");
            predicate = categoryJoin.get("categoryId").in(counsellingDataRequest.getCategoryId());
        }

        if (counsellingDataRequest.getGenderId() != 0) {
            Join<Rank, Category> genderJoin = root.join("gender");
            Predicate genderPredicate = genderJoin.get("genderId").in(counsellingDataRequest.getGenderId());
            predicate = predicate == null ? genderPredicate : criteriaBuilder.and(predicate, genderPredicate);
        }

        if (counsellingDataRequest.getQuotaId() != 0) {
            Join<Rank, Category> quotaJoin = root.join("quota");
            Predicate quotaPredicate = quotaJoin.get("quotaId").in(counsellingDataRequest.getQuotaId());
            predicate = predicate == null ? quotaPredicate : criteriaBuilder.and(predicate, quotaPredicate);
        }

        if (counsellingDataRequest.getYears() != null && !counsellingDataRequest.getYears().isEmpty()) {
            Predicate yearPredicate = root.get("year").in(counsellingDataRequest.getYears());
            predicate = predicate == null ? yearPredicate : criteriaBuilder.and(predicate, yearPredicate);
        }
        return predicate;
    }

    private List<CounsellingData> getCounsellingData(List<Rank> ranks) {
        return ranks.stream().filter(r -> r.getCollegeBranch() != null).map(r -> new CounsellingData(1,
                r.getCollegeBranch().getCollege().getName(),
                r.getCollegeBranch().getCollege().getType().getName(),
                r.getCollegeBranch().getBranch().getName(),
                r.getCategory().getName(),
                r.getGender().getName(),
                r.getQuota().getName(),
                r.getYear(),
                new ThreeTuple<>(r.getRankType().getId(), r.getOpenRank(), r.getClosingRank()))).collect(Collectors.toList());
    }
}
