package com.tpe.googleresponse;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VolumeInfo {

    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;



    private  List<IndustryIdentifiers> industryIdentifiers ;





}
