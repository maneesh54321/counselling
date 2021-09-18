package com.vedantu.counselling.data.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vedantu.counselling.data.Constants;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.repository.*;
import com.vedantu.counselling.data.request.CounsellingDataRequest;
import com.vedantu.counselling.data.request.SortType;
import com.vedantu.counselling.data.response.CounsellingData;
import com.vedantu.counselling.data.response.CounsellingDataResponse;
import com.vedantu.counselling.data.service.mapper.CounsellingDataMapper;
import com.vedantu.counselling.data.util.PaginationUtil;
import com.vedantu.counselling.data.response.view.CityData;
import com.vedantu.counselling.data.response.metadata.CounsellingDataMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    private final DistanceMappingRepository distanceMappingRepository;

    private final int maxDistance;

    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    @Autowired
    public CounsellingDataService(CategoryRepository categoryRepository, GenderRepository genderRepository,
                                  QuotaRepository quotaRepository, CollegeTypeRepository collegeTypeRepository,
                                  CollegeRepository collegeRepository, BranchTagRepository branchTagRepository,
                                  BranchRepository branchrepository, RankRepository rankRepository,
                                  CityRepository cityRepository, DistanceMappingRepository distanceMappingRepository,
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
        this.distanceMappingRepository = distanceMappingRepository;
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
        log.debug("Counselling data request for {}", request);

        if (request == null) {
            log.info("Request is null hence no records in response");
            return new CounsellingDataResponse(0, Collections.emptyList());
        }

        List<CounsellingDbData> allData = rankRepository.findCounsellingDbData();

        List<CounsellingData> finalCounsellingData = getCounsellingData(filterDataForRequest(allData, request));

        finalCounsellingData = mapAndApplyDistanceFilter(finalCounsellingData, request);

        Comparator<CounsellingData> comparator = request.getSortType().equals(SortType.ASC) ? request.getCounsellingDataSortBy().getComparator() : request.getCounsellingDataSortBy().getComparator().reversed();
        finalCounsellingData.sort(comparator);

        int size = finalCounsellingData.size();
        log.debug("Total sorted counselling records after filtering data request are {}", size);
        return new CounsellingDataResponse(size,
                PaginationUtil.getPaginatedList(finalCounsellingData, size, request.getPageSize(), request.getPageNumber()));
    }

    private List<CounsellingData>  mapAndApplyDistanceFilter(List<CounsellingData> inputList, CounsellingDataRequest request) {
        List<DistanceMapping> allDistanceFromLocation = distanceMappingRepository.findByCityId(request.getCityId());
        Map<String, Integer> collegeDistance = allDistanceFromLocation.stream().collect(Collectors.toMap(e->e.getCollege().getName(), DistanceMapping::getDistance));

        inputList.forEach(e -> e.setDistance(collegeDistance.get(e.getCollege())));

        if (request.getDistanceLimit() != 0)
            return inputList.stream().filter(entry -> entry.getDistance() <= request.getDistanceLimit()).collect(Collectors.toList());

        return inputList;
    }

    private List<CounsellingDbData> filterDataForRequest(List<CounsellingDbData> allData, CounsellingDataRequest request) {
        List<CounsellingDbData> filteredData = allData;
        if (request.getCategoryId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getCategoryId() == request.getCategoryId()).collect(Collectors.toList());
        }

        if (request.getGenderId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getGenderId() == request.getGenderId()).collect(Collectors.toList());
        }

        if (request.getQuotaId() != 0) {
            filteredData = filteredData.stream().filter(entry -> entry.getQuotaId() == request.getQuotaId()).collect(Collectors.toList());
        }

        if (request.getYears() != null && !request.getYears().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getYears().contains(entry.getYear())).collect(Collectors.toList());
        }

        if (request.getDurations() != null && !request.getDurations().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getDurations().contains(entry.getDuration())).collect(Collectors.toList());
        }

        if (request.getBranchTagIds() != null && !request.getBranchTagIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getBranchTagIds().contains(entry.getBranchTagId())).collect(Collectors.toList());
        }

        if (request.getCollegeIds() != null && !request.getCollegeIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getCollegeIds().contains(entry.getCollegeId())).collect(Collectors.toList());
        }

        if (request.getCollegeTagIds() != null && !request.getCollegeTagIds().isEmpty()) {
            filteredData = filteredData.stream().filter(entry -> request.getCollegeTagIds().contains(entry.getCollegeTypeId())).collect(Collectors.toList());
        }

        if (request.getAdvanceCRStart() != 0 && request.getAdvanceCREnd() != 0) {
            filteredData = filteredData.stream().filter(
                    entry -> (entry.getRankTypeId() != Constants.RANK_TYPE_ADVANCE || (entry.getRankTypeId() == Constants.RANK_TYPE_ADVANCE && entry.getCloseRank() >= request.getAdvanceCRStart() && entry.getCloseRank() <= request.getAdvanceCREnd()))
            ).collect(Collectors.toList());
        }

        if (request.getMainsCRStart() != 0 && request.getMainsCREnd() != 0) {
            filteredData = filteredData.stream().filter(
                    entry -> (entry.getRankTypeId() != Constants.RANK_TYPE_MAINS || (entry.getRankTypeId() == Constants.RANK_TYPE_MAINS && entry.getCloseRank() >= request.getMainsCRStart() && entry.getCloseRank() <= request.getMainsCREnd()))
            ).collect(Collectors.toList());
        }

        if (request.getBArchCRStart() != 0 && request.getBArchCREnd() != 0) {
            filteredData = filteredData.stream().filter(
                    entry -> (entry.getRankTypeId() != Constants.RANK_TYPE_BARCH || (entry.getRankTypeId() == Constants.RANK_TYPE_BARCH && entry.getCloseRank() >= request.getBArchCRStart() && entry.getCloseRank() <= request.getBArchCREnd()))
            ).collect(Collectors.toList());
        }

        return filteredData;
    }

    private List<CounsellingData> getCounsellingData(List<CounsellingDbData> counsellingDbData) {
        List<CounsellingData> returnList = new ArrayList<>(counsellingDbData.size());

        for (CounsellingDbData dbData: counsellingDbData) {
            int openingRankAdvance = -1;
            int openingRankMains = -1;
            int openingRankBArch = -1;
            int closingRankAdvance = -1;
            int closingRankMains = -1;
            int closingRankBArch = -1;

            if(dbData.getRankTypeId() == Constants.RANK_TYPE_ADVANCE) {
                openingRankAdvance = dbData.getOpenRank();
                closingRankAdvance = dbData.getCloseRank();
            } else if (dbData.getRankTypeId() == Constants.RANK_TYPE_MAINS) {
                openingRankMains = dbData.getOpenRank();
                closingRankMains = dbData.getCloseRank();
            } else if (dbData.getRankTypeId() == Constants.RANK_TYPE_BARCH) {
                openingRankBArch = dbData.getOpenRank();
                closingRankBArch = dbData.getCloseRank();
            }
            returnList.add(new CounsellingData(dbData.getId(),1,
                    dbData.getCollege(),
                    dbData.getCollegeType(),
                    dbData.getBranch(),
                    dbData.getCategory(),
                    dbData.getGender(),
                    dbData.getQuota(),
                    dbData.getYear(),
                    openingRankAdvance,
                    openingRankMains,
                    openingRankBArch,
                    closingRankAdvance,
                    closingRankMains,
                    closingRankBArch
            ));
        }
        return returnList;
    }

    public byte[] convertCounsellingDataResponseToPdf(CounsellingDataResponse counsellingDataResponse){
        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document, counsellingDataResponse.getCounsellingData());
            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException e) {
            String errMsg = "Error occurred while creating pdf from counselling data";
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Previous years counselling results");
        document.addSubject("Previous years counselling results");
        document.addAuthor("Vedantu");
        document.addCreator("Vedantu");
    }

    private void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        // Add title
        preface.add(new Paragraph("Counselling results", catFont));

        // Add an empty line
        preface.add(new Paragraph(" "));

        document.add(preface);
    }

    private void addContent(Document document, List<CounsellingData> counsellingData) throws DocumentException {
        // add a table
        PdfPTable table = createTable();

        // add data to the table
        counsellingData.forEach( data -> addRow(table, data));

        // add table to the pdf document
        document.add(table);
    }

    private PdfPTable createTable() throws DocumentException {
        PdfPTable table = new PdfPTable(14);
        // Set table width
        table.setWidthPercentage(100);

        // Add Header
        addHeaderRow(table);

        return table;
    }

    private static void addHeaderRow(PdfPTable table) throws DocumentException {
        // Setting column width of each column from 0 to n-1
        table.setWidths(new int[]{110, 350, 350, 140, 120, 100, 100, 100, 100, 100, 100, 100, 100, 100});

        // Adding headers
        PdfPCell headerCell = new PdfPCell(new Phrase("College\nType"));
        headerCell.setNoWrap(true);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("College"));
        headerCell.setNoWrap(true);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Branch"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Category"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Gender"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Quota"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("OR\n(Advanced)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("CR\n(Advanced)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("OR\n(Mains)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("CR\n(Mains)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("OR\n(B.Arch)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("CR\n(B.Arch)"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Year"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("Distance"));
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell);

        table.setHeaderRows(1);
    }

    private void addRow(PdfPTable table, CounsellingData counsellingData) {
        table.addCell(counsellingData.getCollegeType());
        table.addCell(counsellingData.getCollege());
        table.addCell(counsellingData.getBranch());
        table.addCell(counsellingData.getCategory());
        table.addCell(counsellingData.getGender());
        table.addCell(counsellingData.getQuota());
        table.addCell(String.valueOf(counsellingData.getOpeningRankAdvance()));
        table.addCell(String.valueOf(counsellingData.getClosingRankAdvance()));
        table.addCell(String.valueOf(counsellingData.getOpeningRankMains()));
        table.addCell(String.valueOf(counsellingData.getClosingRankMains()));
        table.addCell(String.valueOf(counsellingData.getOpeningRankBArch()));
        table.addCell(String.valueOf(counsellingData.getClosingRankBArch()));
        table.addCell(String.valueOf(counsellingData.getYear()));
        table.addCell(String.valueOf(counsellingData.getDistance()));
    }
}
