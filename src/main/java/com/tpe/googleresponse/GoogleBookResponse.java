package com.tpe.googleresponse;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//Class to deseilize the json response
public class GoogleBookResponse {

    private List<GoogleBookItem> items ;
}
