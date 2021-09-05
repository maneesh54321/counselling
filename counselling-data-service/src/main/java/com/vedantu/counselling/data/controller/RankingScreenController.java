package com.vedantu.counselling.data.controller;

import com.vedantu.counselling.data.model.*;
import com.vedantu.counselling.data.response.CityDataResponse;
import com.vedantu.counselling.data.response.RankingScreenMetadata;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;

@RestController
public class RankingScreenController {

    @GetMapping(value = "/counsellingapp/rank-screen-metadata", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<RankingScreenMetadata> getRankingScreenMetadata() {
        return new Response<>("Success", getDummyMetaData());
    }

    private RankingScreenMetadata getDummyMetaData() {
        RankingScreenMetadata rankingScreenMetadata = new RankingScreenMetadata();
        List<Category> categories = new LinkedList<>();
        categories.add(new Category(1,"OPEN"));
        categories.add(new Category(2,"OBC-NCL"));
        categories.add(new Category(3,"SC"));
        categories.add(new Category(4,"ST"));
        categories.add(new Category(5,"OPEN (PwD)"));
        categories.add(new Category(6,"OBC-NCL (PwD)"));
        categories.add(new Category(7,"SC (PwD)"));
        categories.add(new Category(8,"ST (PwD)"));
        categories.add(new Category(9,"EWS"));
        categories.add(new Category(10,"EWS (PwD)"));
        rankingScreenMetadata.setCategories(categories);

        List<Gender> genders = new LinkedList<>();
        genders.add(new Gender(1, "Female-only (including Supernumerary)"));
        genders.add(new Gender(2, "Gender-Neutral"));
        rankingScreenMetadata.setGenders(genders);

        List<Quota> quotas = new LinkedList<>();
        quotas.add(new Quota(1, "AI/OS"));
        quotas.add(new Quota(2, "HS"));
        quotas.add(new Quota(3, "GO"));
        quotas.add(new Quota(4, "AP"));
        quotas.add(new Quota(5, "JK"));
        quotas.add(new Quota(6, "LA"));
        rankingScreenMetadata.setQuotas(quotas);

        Map<RankType, Integer> maxRanks = new LinkedHashMap<>();
        maxRanks.put(new RankType(1, "Advance"), 10000);
        maxRanks.put(new RankType(2, "JEE-Main"), 10000);
        maxRanks.put(new RankType(3, "Arch"), 2500);
        rankingScreenMetadata.setMaxRanks(maxRanks);

        rankingScreenMetadata.setColleges(Arrays.asList(
                new College(1, "Indian Institute of Technology Bhubaneswar", new CollegeType(1,"IIT")),
                new College(2, "Indian Institute of Technology Bombay", new CollegeType(1,"IIT")),
                new College(3, "National Institute of Technology Raipur", new CollegeType(1,"IIT")),
                new College(4, "Indian Institute of Technology Mandi", new CollegeType(1,"IIT")),
                new College(5, "Indian Institute of Technology Delhi", new CollegeType(1,"IIT")),
                new College(6, "Indian Institute of Technology Indore", new CollegeType(1,"IIT")),
                new College(7, "Indian Institute of Technology Kharagpur", new CollegeType(1,"IIT")),
                new College(8, "Indian Institute of Technology Hyderabad", new CollegeType(1,"IIT")),
                new College(9, "Indian Institute of Technology Jodhpur", new CollegeType(1,"IIT")),
                new College(10, "Indian Institute of Technology Kanpur", new CollegeType(1,"IIT")),
                new College(11, "Indian Institute of Technology Madras", new CollegeType(1,"IIT")),
                new College(12, "Indian Institute of Technology Gandhinagar", new CollegeType(1,"IIT")),
                new College(13, "Indian Institute of Technology Patna", new CollegeType(1,"IIT")),
                new College(14, "Indian Institute of Technology Roorkee", new CollegeType(1,"IIT")),
                new College(15, "Indian Institute of Technology (ISM) Dhanbad", new CollegeType(1,"IIT")),
                new College(16, "Indian Institute of Technology Ropar", new CollegeType(1,"IIT")),
                new College(17, "Indian Institute of Technology (BHU) Varanasi", new CollegeType(1,"IIT")),
                new College(18, "Indian Institute of Technology Guwahati", new CollegeType(1,"IIT")),
                new College(19, "Indian Institute of Technology Bhilai", new CollegeType(1,"IIT")),
                new College(20, "Indian Institute of Technology Goa", new CollegeType(1,"IIT")),
                new College(21, "Indian Institute of Technology Palakkad", new CollegeType(1,"IIT")),
                new College(22, "Indian Institute of Technology Tirupati", new CollegeType(1,"IIT")),
                new College(23, "Indian Institute of Technology Jammu", new CollegeType(1,"IIT")),
                new College(24, "Indian Institute of Technology Dharwad", new CollegeType(1,"IIT")),
                new College(25, "Dr. B R Ambedkar National Institute of Technology, Jalandhar", new CollegeType(3,"NIT")),
                new College(26, "Malaviya National Institute of Technology Jaipur", new CollegeType(3,"NIT")),
                new College(27, "Maulana Azad National Institute of Technology Bhopal", new CollegeType(3,"NIT")),
                new College(28, "Motilal Nehru National Institute of Technology Allahabad", new CollegeType(3,"NIT")),
                new College(29, "National Institute of Technology Agartala", new CollegeType(3,"NIT")),
                new College(30, "National Institute of Technology Calicut", new CollegeType(3,"NIT")),
                new College(31, "National Institute of Technology Delhi", new CollegeType(3,"NIT")),
                new College(32, "National Institute of Technology Durgapur", new CollegeType(3,"NIT")),
                new College(33, "National Institute of Technology Goa", new CollegeType(3,"NIT")),
                new College(34, "National Institute of Technology Hamirpur", new CollegeType(3,"NIT")),
                new College(35, "National Institute of Technology Karnataka, Surathkal", new CollegeType(3,"NIT")),
                new College(36, "National Institute of Technology Meghalaya", new CollegeType(3,"NIT")),
                new College(37, "National Institute of Technology Nagaland", new CollegeType(3,"NIT")),
                new College(38, "National Institute of Technology Patna", new CollegeType(3,"NIT")),
                new College(39, "National Institute of Technology Puducherry", new CollegeType(3,"NIT")),
                new College(40, "National Institute of Technology Sikkim", new CollegeType(3,"NIT")),
                new College(41, "National Institute of Technology Arunachal Pradesh", new CollegeType(3,"NIT")),
                new College(42, "National Institute of Technology, Jamshedpur", new CollegeType(3,"NIT")),
                new College(43, "National Institute of Technology, Kurukshetra", new CollegeType(3,"NIT")),
                new College(44, "National Institute of Technology, Manipur", new CollegeType(3,"NIT")),
                new College(45, "National Institute of Technology, Mizoram", new CollegeType(3,"NIT")),
                new College(46, "National Institute of Technology, Rourkela", new CollegeType(3,"NIT")),
                new College(47, "National Institute of Technology, Silchar", new CollegeType(3,"NIT")),
                new College(48, "National Institute of Technology, Srinagar", new CollegeType(3,"NIT")),
                new College(49, "National Institute of Technology, Tiruchirappalli", new CollegeType(3,"NIT")),
                new College(50, "National Institute of Technology, Uttarakhand", new CollegeType(3,"NIT")),
                new College(51, "National Institute of Technology, Warangal", new CollegeType(3,"NIT")),
                new College(52, "Sardar Vallabhbhai National Institute of Technology, Surat", new CollegeType(3,"NIT")),
                new College(53, "Visvesvaraya National Institute of Technology, Nagpur", new CollegeType(3,"NIT")),
                new College(54, "National Institute of Technology, Andhra Pradesh", new CollegeType(3,"NIT")),
                new College(55, "Indian Institute of Engineering Science and Technology, Shibpur", new CollegeType(4,"GFTI")),
                new College(56, "Atal Bihari Vajpayee Indian Institute of Information Technology & Management Gwalior", new CollegeType(2,"IIIT")),
                new College(57, "Indian Institute of Information Technology (IIIT)Kota, Rajasthan", new CollegeType(2,"IIIT")),
                new College(58, "Indian Institute of Information Technology Guwahati", new CollegeType(2,"IIIT")),
                new College(59, "Indian Institute of Information Technology(IIIT) Kalyani, West Bengal", new CollegeType(2,"IIIT")),
                new College(60, "Indian Institute of Information Technology(IIIT) Kilohrad, Sonepat, Haryana", new CollegeType(2,"IIIT")),
                new College(61, "Indian Institute of Information Technology(IIIT) Una, Himachal Pradesh", new CollegeType(2,"IIIT")),
                new College(62, "Indian Institute of Information Technology (IIIT), Sri City, Chittoor", new CollegeType(2,"IIIT")),
                new College(63, "Indian Institute of Information Technology(IIIT), Vadodara, Gujrat", new CollegeType(2,"IIIT")),
                new College(64, "Indian Institute of Information Technology, Allahabad", new CollegeType(2,"IIIT")),
                new College(65, "Indian Institute of Information Technology, Design & Manufacturing, Kancheepuram", new CollegeType(2,"IIIT")),
                new College(66, "Pt. Dwarka Prasad Mishra Indian Institute of Information Technology, Design & Manufacture Jabalpur", new CollegeType(2,"IIIT")),
                new College(67, "Indian Institute of Information Technology Manipur", new CollegeType(2,"IIIT")),
                new College(68, "Indian Institute of Information Technology Srirangam, Tiruchirappalli", new CollegeType(2,"IIIT")),
                new College(69, "Indian Institute of Information Technology Lucknow", new CollegeType(2,"IIIT")),
                new College(70, "Indian Institute of Information Technology(IIIT) Dharwad", new CollegeType(2,"IIIT")),
                new College(71, "Indian Institute of Information Technology Design & Manufacturing Kurnool, Andhra Pradesh", new CollegeType(2,"IIIT")),
                new College(72, "Indian Institute of Information Technology(IIIT) Kottayam", new CollegeType(2,"IIIT")),
                new College(73, "Indian Institute of Information Technology (IIIT) Ranchi", new CollegeType(2,"IIIT")),
                new College(74, "Indian Institute of Information Technology (IIIT) Nagpur", new CollegeType(2,"IIIT")),
                new College(75, "Indian Institute of Information Technology (IIIT) Pune", new CollegeType(2,"IIIT")),
                new College(76, "Indian Institute of Information Technology Bhagalpur", new CollegeType(2,"IIIT")),
                new College(77, "Indian Institute of Information Technology Bhopal", new CollegeType(2,"IIIT")),
                new College(78, "Indian Institute of Information Technology Surat", new CollegeType(2,"IIIT")),
                new College(79, "Assam University, Silchar", new CollegeType(4,"GFTI")),
                new College(80, "Birla Institute of Technology, Mesra, Ranchi", new CollegeType(4,"GFTI")),
                new College(81, "Gurukula Kangri Vishwavidyalaya, Haridwar", new CollegeType(4,"GFTI")),
                new College(82, "Indian Institute of Carpet Technology, Bhadohi", new CollegeType(4,"GFTI")),
                new College(83, "Institute of Infrastructure, Technology, Research and Management-Ahmedabad", new CollegeType(4,"GFTI")),
                new College(84, "Institute of Technology, Guru Ghasidas Vishwavidyalaya (A Central University), Bilaspur, (C.G.)", new CollegeType(4,"GFTI")),
                new College(85, "J.K. Institute of Applied Physics & Technology, Department of Electronics & Communication, University of Allahabad- Allahabad", new CollegeType(4,"GFTI")),
                new College(86, "National Institute of Electronics and Information Technology, Aurangabad (Maharashtra)", new CollegeType(4,"GFTI")),
                new College(87, "National Institute of Foundry & Forge Technology, Hatia, Ranchi", new CollegeType(4,"GFTI")),
                new College(88, "Sant Longowal Institute of Engineering and Technology", new CollegeType(4,"GFTI")),
                new College(89, "Mizoram University, Aizawl", new CollegeType(4,"GFTI")),
                new College(90, "School of Engineering, Tezpur University, Napaam, Tezpur", new CollegeType(4,"GFTI")),
                new College(91, "School of Planning & Architecture, Bhopal", new CollegeType(4,"GFTI")),
                new College(92, "School of Planning & Architecture, New Delhi", new CollegeType(4,"GFTI")),
                new College(93, "School of Planning & Architecture: Vijayawada", new CollegeType(4,"GFTI")),
                new College(94, "Shri Mata Vaishno Devi University, Katra, Jammu & Kashmir", new CollegeType(4,"GFTI")),
                new College(95, "HNB Garhwal University Srinagar (Garhwal)", new CollegeType(4,"GFTI")),
                new College(96, "International Institute of Information Technology, Naya Raipur", new CollegeType(4,"GFTI")),
                new College(97, "University of Hyderabad", new CollegeType(4,"GFTI")),
                new College(98, "Punjab Engineering College, Chandigarh", new CollegeType(4,"GFTI")),
                new College(99, "Jawaharlal Nehru University, Delhi", new CollegeType(4,"GFTI")),
                new College(100, "International Institute of Information Technology, Bhubaneswar", new CollegeType(4,"GFTI")),
                new College(101, "Indian Institute of Information Technology, Agartala", new CollegeType(2,"IIIT")),
                new College(102, "Indian institute of information technology, Raichur, Karnataka", new CollegeType(2,"IIIT")),
                new College(103, "Central institute of Technology Kokrajar, Assam", new CollegeType(4,"GFTI")),
                new College(104, "Pondicherry Engineering College, Puducherry", new CollegeType(4,"GFTI")),
                new College(105, "Ghani Khan Choudhary Institute of Engineering and Technology, Malda, West Bengal", new CollegeType(4,"GFTI")),
                new College(106, "Central University of Rajasthan, Rajasthan", new CollegeType(4,"GFTI")),
                new College(107, "National Institute of Food Technology Entrepreneurship and Management, Sonepat, Haryana", new CollegeType(4,"GFTI")),
                new College(108, "Indian Institute of Information Technology, Vadodara International Campus Diu (IIITVICD)", new CollegeType(2,"IIIT")),
                new College(109, "lndian Institute of Food Processing Technology, Thanjavur, Tamil Naidu.", new CollegeType(4,"GFTI")),
                new College(110, "North Eastern Regional Institute of Science and Technology, Nirjuli-791109 (Itanagar),Arunachal Pradesh", new CollegeType(4,"GFTI"))
        ));

        rankingScreenMetadata.setBranchTags(Arrays.asList(
                new BranchTag(1,"Civil"),
                new BranchTag(2,"CSE"),
                new BranchTag(3,"EEE"),
                new BranchTag(4,"ECE"),
                new BranchTag(5,"ME"),
                new BranchTag(6,"Metallurgy"),
                new BranchTag(7,"Aerospace"),
                new BranchTag(8,"Maths(Bsc/Msc)"),
                new BranchTag(9,"Chemical"),
                new BranchTag(10,"Chemistry(Bsc/Msc)"),
                new BranchTag(11,"Economics(Bsc/Msc)"),
                new BranchTag(12,"Engineering Physics"),
                new BranchTag(13,"Environmental Engineering"),
                new BranchTag(14,"Biotechnology"),
                new BranchTag(15,"Mathematics and Computing"),
                new BranchTag(16,"Industrial production"),
                new BranchTag(17,"Textile Technology"),
                new BranchTag(18,"Agricultural"),
                new BranchTag(19,"Geology"),
                new BranchTag(20,"Architecture"),
                new BranchTag(21,"Instrumentation"),
                new BranchTag(22,"Manufacturing"),
                new BranchTag(23,"Mining"),
                new BranchTag(24,"Ocean Engineering"),
                new BranchTag(25,"Physics(Bsc/Msc)"),
                new BranchTag(26,"Quality Engineering"),
                new BranchTag(27,"Engineering Science"),
                new BranchTag(28,"Material science"),
                new BranchTag(29,"Engineering Design"),
                new BranchTag(30,"Polymer Science"),
                new BranchTag(31,"Mineral"),
                new BranchTag(32,"Petroleum"),
                new BranchTag(33,"Ceramic"),
                new BranchTag(34,"Industrial Chemistry"),
                new BranchTag(35,"Pharmaceutical"),
                new BranchTag(36,"IT"),
                new BranchTag(37,"Planning"),
                new BranchTag(38,"Food Process Enginerring"),
                new BranchTag(39,"Life Science"),
                new BranchTag(40,"IT(Derivative)"),
                new BranchTag(41,"Mechatronics"),
                new BranchTag(42,"CSE(Derivative)"),
                new BranchTag(43,"AI"),
                new BranchTag(44,"ME(Derivative)")));

        rankingScreenMetadata.setDurations(Arrays.asList(4, 5));

        rankingScreenMetadata.setYears(Arrays.asList(2018,2019,2020));

        rankingScreenMetadata.setCollegeTypes(Arrays.asList(
                new CollegeType(1, "IIT"),
                new CollegeType(2, "IIIT"),
                new CollegeType(3, "NIT"),
                new CollegeType(4, "GFTI")));

        rankingScreenMetadata.setMaxDistance(2900);
        return rankingScreenMetadata;
    }
}
