package uz.mh.webscrapping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private String activity;
    private String nameOfTheOrganization;
    private String dateOfRegistration;
    private String TIN;
    private String addressCompany;
    private String organizationStatus;
    private String STIR;
    private String THSHT;
    private String DBIBT;
    private String IFUT;
    private String CharterFundOfTheOrganization;
    private String phoneNumberCompany;
}
